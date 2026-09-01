-- ===========================================================================
-- Esquema de RecetarioWeb (PostgreSQL).
--
-- Se crea a mano para tener un entorno local reproducible. Los nombres de
-- columna coinciden EXACTAMENTE con las anotaciones @Column de las entidades
-- JPA (com.RecetarioWeb.Entitys.*). PostgreSQL pasa a minusculas los
-- identificadores sin comillas, igual que EclipseLink al generar el SQL.
--
-- Peculiaridades del modelo original:
--   * La PK de casi todas las tablas es el "nombre" (varchar), no un id.
--   * El campo id* (idcat, idemp, idreceta, ...) es un entero NO clave que la
--     app rellena con COUNT(*) al insertar (ver *Facade.registrar*).
--   * Solo "comentario" tiene id autogenerado (idcomen, IDENTITY).
--   * No hay claves foraneas: las relaciones son por convencion.
-- ===========================================================================

CREATE TABLE persona (
    codigo          varchar(100) PRIMARY KEY,
    idpersona       integer      NOT NULL DEFAULT 0,
    nombre          varchar(100),
    username        varchar(100),
    pass            varchar(100),
    fechanacimeinto date,
    email           varchar(100),
    direccion       varchar(100),
    pais            varchar(100),
    ciudad          varchar(100),
    rol             integer,
    activo          boolean
);

CREATE TABLE categoria (
    nombrecat   varchar(100) PRIMARY KEY,
    idcat       integer      NOT NULL DEFAULT 0,
    fechacat    date,
    descripcion varchar(1000)
);

CREATE TABLE empresa (
    nombreemp      varchar(100) PRIMARY KEY,
    idemp          integer      NOT NULL DEFAULT 0,
    descripcionemp varchar(1000),
    imagenemp      varchar(1000)
);

CREATE TABLE receta (
    nombrereceta      varchar(100) PRIMARY KEY,
    idreceta          integer      NOT NULL DEFAULT 0,
    iduserreceta      varchar(100),
    idcatreceta       integer,
    descripcionreceta varchar(1000),
    autorreceta       varchar(100),
    fechareceta       date,
    imagenreceta      varchar(1000),
    ingredientes      varchar(1000)
);

CREATE TABLE tip (
    nombretip      varchar(100) PRIMARY KEY,
    idtip          integer      NOT NULL DEFAULT 0,
    idusertip      varchar(100),
    descripciontip varchar(1000),
    fechatip       date,
    autortip       varchar(100)
);

CREATE TABLE membrecia (
    idusermem varchar(100) PRIMARY KEY,
    idmem     integer      NOT NULL DEFAULT 0,
    puntos    integer,
    fechamem  date,
    activamem boolean
);

CREATE TABLE comentario (
    idcomen       serial PRIMARY KEY,
    textocomen    varchar(2000),
    idusercomen   varchar(100),
    idrecetacomen integer,
    fechacomen    date
);
