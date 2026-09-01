# ============================================================================
# Build multi-etapa: Maven (JDK 8) construye los 3 artefactos y luego se
# hornean en una imagen de Payara 5 (Java EE 7, namespace javax.*).
# No hace falta instalar Java / Maven / Payara en la maquina anfitriona.
# ============================================================================

# ---- Etapa 1: compilar library.jar + ejb.jar + war ----------------------
FROM maven:3.8-eclipse-temurin-8 AS build
WORKDIR /src

# POMs primero -> se cachea la resolucion de dependencias
COPY pom.xml .
COPY RecetarioWeb-Library/pom.xml   RecetarioWeb-Library/
COPY Recetario-Gestion-ejb/pom.xml  Recetario-Gestion-ejb/
COPY RecetarioWeb-War/pom.xml       RecetarioWeb-War/
RUN mvn -B -q -DskipTests dependency:go-offline || true

# Codigo fuente
COPY RecetarioWeb-Library/src   RecetarioWeb-Library/src
COPY Recetario-Gestion-ejb/src  Recetario-Gestion-ejb/src
COPY RecetarioWeb-War/src       RecetarioWeb-War/src
COPY RecetarioWeb-War/web       RecetarioWeb-War/web

RUN mvn -B -DskipTests package
# Driver JDBC de PostgreSQL para el pool de Payara
RUN mvn -B -q dependency:copy -Dartifact=org.postgresql:postgresql:42.5.6 \
        -Dmdep.stripVersion=true -DoutputDirectory=/src/drivers

# ---- Etapa 2: runtime Payara 5 -----------------------------------------
FROM payara/server-full:5.2022.5
USER root

ENV DOMAIN_LIB=${PAYARA_DIR}/glassfish/domains/${DOMAIN_NAME}/lib

# Driver JDBC + libreria compartida en el classloader comun del dominio
COPY --from=build /src/drivers/postgresql.jar                                 ${DOMAIN_LIB}/postgresql.jar
COPY --from=build /src/RecetarioWeb-Library/target/recetario-library.jar      ${DOMAIN_LIB}/recetario-library.jar

# Artefactos desplegables
COPY --from=build /src/Recetario-Gestion-ejb/target/Recetario-Gestion-ejb.jar /opt/app/Recetario-Gestion-ejb.jar
COPY --from=build /src/RecetarioWeb-War/target/RecetarioWeb-War.war           /opt/app/RecetarioWeb-War.war

# Pool JDBC + despliegue ordenado (ejb antes que war)
COPY docker/payara/post-boot-commands.asadmin /opt/payara/config/post-boot-commands.asadmin

RUN chown -R payara:payara "${DOMAIN_LIB}" /opt/app /opt/payara/config

USER payara
EXPOSE 8080 4848
