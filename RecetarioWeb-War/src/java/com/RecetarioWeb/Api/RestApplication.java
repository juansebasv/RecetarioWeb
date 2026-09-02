/*
 * Punto de entrada JAX-RS de la API REST de solo lectura de RecetarioWeb y
 * metadatos globales del documento OpenAPI.
 *
 * La API queda bajo:  /RecetarioWeb-War/api/...
 * El documento OpenAPI lo genera SmallRye (incluido en Payara 5) en:
 *     /openapi            (YAML)
 *     /openapi?format=JSON
 * y se explora visualmente con Swagger UI en:
 *     /RecetarioWeb-War/api-docs.html
 */
package com.RecetarioWeb.Api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/api")
@OpenAPIDefinition(
        info = @Info(
                title = "RecetarioWeb API",
                version = "1.0.0",
                description = "API REST de **solo lectura** sobre el catalogo de RecetarioWeb "
                        + "(recetas, tips, categorias, empresas, comentarios y usuarios). "
                        + "Expone el mismo dominio que la aplicacion web, sin operaciones de "
                        + "escritura ni datos sensibles (nunca se devuelve la contrasena).",
                contact = @Contact(name = "Equipo RecetarioWeb", email = "admin@recetarioweb.com"),
                license = @License(name = "Uso academico - Universidad Central")
        ),
        servers = {
            @Server(url = "/RecetarioWeb-War", description = "Instancia local (docker compose)")
        },
        tags = {
            @Tag(name = "Recetas", description = "Catalogo de recetas y su detalle"),
            @Tag(name = "Tips", description = "Consejos de tecnica de cocina de la comunidad"),
            @Tag(name = "Categorias", description = "Categorias para navegar el catalogo"),
            @Tag(name = "Empresas", description = "Empresas proveedoras destacadas"),
            @Tag(name = "Comentarios", description = "Comentarios publicados sobre las recetas"),
            @Tag(name = "Usuarios", description = "Usuarios registrados (sin credenciales)")
        }
)
public class RestApplication extends Application {
    // Sin configuracion adicional: Jersey descubre los @Path por escaneo.
}
