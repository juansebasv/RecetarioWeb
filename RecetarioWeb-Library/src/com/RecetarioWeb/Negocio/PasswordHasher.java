/*
 * Utilidad de hashing de contrasenas compartida por el WAR (registro / login /
 * cambio de clave) y por el modulo EJB. Vive en la libreria comun para que WAR
 * y EJB carguen exactamente la misma clase desde domain1/lib.
 *
 * Usa unicamente APIs del JDK (javax.crypto PBKDF2WithHmacSHA256), sin librerias
 * externas. Formato almacenado:  pbkdf2$<iteraciones>$<saltBase64>$<hashBase64>
 */
package com.RecetarioWeb.Negocio;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordHasher {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String PREFIX = "pbkdf2$";
    private static final int ITERATIONS = 120_000;
    private static final int SALT_BYTES = 16;
    private static final int KEY_BITS = 256;

    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordHasher() {
    }

    /** Genera el hash con sal aleatoria para una contrasena en claro. */
    public static String hash(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("La contrasena no puede ser nula");
        }
        byte[] salt = new byte[SALT_BYTES];
        RANDOM.nextBytes(salt);
        byte[] hash = pbkdf2(rawPassword.toCharArray(), salt, ITERATIONS);
        return PREFIX + ITERATIONS + "$"
                + Base64.getEncoder().encodeToString(salt) + "$"
                + Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Compara una contrasena en claro contra el valor almacenado. Acepta tanto
     * los hashes en formato PBKDF2 como valores heredados en texto plano (para
     * no invalidar la semilla historica); en ese caso conviene re-hashear.
     */
    public static boolean matches(String rawPassword, String stored) {
        if (rawPassword == null || stored == null) {
            return false;
        }
        if (!stored.startsWith(PREFIX)) {
            return constantTimeEquals(rawPassword, stored); // valor heredado en claro
        }
        String[] parts = stored.split("\\$");
        if (parts.length != 4) {
            return false;
        }
        try {
            int iterations = Integer.parseInt(parts[1]);
            byte[] salt = Base64.getDecoder().decode(parts[2]);
            byte[] expected = Base64.getDecoder().decode(parts[3]);
            byte[] actual = pbkdf2(rawPassword.toCharArray(), salt, iterations);
            return Arrays.equals(expected, actual);
        } catch (RuntimeException ex) {
            return false;
        }
    }

    /** Indica si el valor almacenado deberia migrarse al formato con hash. */
    public static boolean needsRehash(String stored) {
        return stored == null || !stored.startsWith(PREFIX);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, KEY_BITS);
            return SecretKeyFactory.getInstance(ALGORITHM).generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new IllegalStateException("No se pudo derivar el hash de la contrasena", ex);
        }
    }

    private static boolean constantTimeEquals(String a, String b) {
        byte[] x = a.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        byte[] y = b.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        int result = x.length ^ y.length;
        for (int i = 0; i < x.length && i < y.length; i++) {
            result |= x[i] ^ y[i];
        }
        return result == 0;
    }
}
