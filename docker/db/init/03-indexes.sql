-- ===========================================================================
-- Indices de apoyo para RecetarioWeb (PostgreSQL).
--
-- El modelo no declara claves foraneas: las relaciones son por convencion de
-- columnas (id*). Estos indices cubren los accesos reales de la aplicacion:
--   * login            -> persona.username
--   * panel admin      -> persona.rol
--   * filtro por cat.  -> receta.idcatreceta  (y join logico receta.idreceta)
--   * comentarios      -> comentario.idrecetacomen / idusercomen
--   * recetas/tips     -> receta.iduserreceta / tip.idusertip
--
-- Se ejecuta una sola vez, en el primer arranque del contenedor de BD
-- (docker-entrypoint-initdb.d), despues de 01-schema.sql y 02-seed.sql.
-- Para reaplicarlo en un volumen existente:  docker compose down -v
-- ===========================================================================

CREATE INDEX IF NOT EXISTS idx_persona_username     ON persona   (username);
CREATE INDEX IF NOT EXISTS idx_persona_rol          ON persona   (rol);

CREATE INDEX IF NOT EXISTS idx_receta_idcatreceta   ON receta    (idcatreceta);
CREATE INDEX IF NOT EXISTS idx_receta_iduserreceta  ON receta    (iduserreceta);
CREATE INDEX IF NOT EXISTS idx_receta_idreceta      ON receta    (idreceta);

CREATE INDEX IF NOT EXISTS idx_comentario_receta    ON comentario (idrecetacomen);
CREATE INDEX IF NOT EXISTS idx_comentario_user      ON comentario (idusercomen);

CREATE INDEX IF NOT EXISTS idx_tip_idusertip        ON tip       (idusertip);

CREATE INDEX IF NOT EXISTS idx_categoria_idcat      ON categoria (idcat);
CREATE INDEX IF NOT EXISTS idx_membrecia_idmem      ON membrecia (idmem);

ANALYZE;
