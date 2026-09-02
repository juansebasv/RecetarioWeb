/*
 * Acceso tipado a application.properties (WEB-INF/classes). Centraliza los
 * parametros de negocio para que no haya "numeros magicos" repartidos por el
 * codigo. Todo valor tiene un defecto razonable si la propiedad falta.
 */
package com.RecetarioWeb.Controller.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AppConfig {

    private static final Logger LOG = Logger.getLogger(AppConfig.class.getName());
    private static final String RESOURCE = "/application.properties";

    private static final int DEFAULT_POINTS_PER_RECIPE = 10;
    private static final int DEFAULT_RECIPES_THRESHOLD = 10;
    private static final int DEFAULT_COOKIE_MAX_AGE_SECONDS = 1800;
    private static final int DEFAULT_SUMMARY_MAX_WORDS = 20;
    private static final int DEFAULT_SUMMARY_WORDS_PER_LINE = 5;
    private static final String DEFAULT_EMPRESA_UPLOAD_DIR = "recetario-empresas";
    private static final int DEFAULT_EMPRESA_UPLOAD_THRESHOLD = 1024;

    private static final Properties PROPS = load();

    private AppConfig() {
    }

    public static int membershipPointsPerRecipe() {
        return intProp("membership.points-per-recipe", DEFAULT_POINTS_PER_RECIPE);
    }

    public static int membershipRecipesThreshold() {
        return intProp("membership.recipes-threshold", DEFAULT_RECIPES_THRESHOLD);
    }

    public static int sessionCookieMaxAgeSeconds() {
        return intProp("session.cookie.max-age-seconds", DEFAULT_COOKIE_MAX_AGE_SECONDS);
    }

    public static int summaryMaxWords() {
        return intProp("text.summary.max-words", DEFAULT_SUMMARY_MAX_WORDS);
    }

    public static int summaryWordsPerLine() {
        return intProp("text.summary.words-per-line", DEFAULT_SUMMARY_WORDS_PER_LINE);
    }

    public static String empresaUploadDirName() {
        return prop("upload.empresa.dir-name", DEFAULT_EMPRESA_UPLOAD_DIR);
    }

    public static int empresaUploadSizeThreshold() {
        return intProp("upload.empresa.size-threshold-bytes", DEFAULT_EMPRESA_UPLOAD_THRESHOLD);
    }

    private static Properties load() {
        Properties p = new Properties();
        try (InputStream in = AppConfig.class.getResourceAsStream(RESOURCE)) {
            if (in == null) {
                LOG.log(Level.WARNING,
                        "No se encontro {0} en el classpath; se usaran los valores por defecto.", RESOURCE);
            } else {
                p.load(in);
                LOG.log(Level.INFO, "Configuracion cargada desde {0} ({1} propiedades).",
                        new Object[]{RESOURCE, p.size()});
            }
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error leyendo " + RESOURCE + "; se usaran los valores por defecto.", e);
        }
        return p;
    }

    private static String prop(String key, String fallback) {
        String value = PROPS.getProperty(key);
        return (value == null || value.trim().isEmpty()) ? fallback : value.trim();
    }

    private static int intProp(String key, int fallback) {
        String value = PROPS.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            LOG.log(Level.WARNING, "La propiedad {0}=''{1}'' no es un entero valido; se usa {2}.",
                    new Object[]{key, value, fallback});
            return fallback;
        }
    }
}
