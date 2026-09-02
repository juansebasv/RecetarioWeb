/*
 * Localizador de EJB remotos (patron Service Locator).
 *
 * Las Struts Actions y los scriptlets JSP no son componentes gestionados, por lo
 * que no pueden usar @EJB. Antes cada clase repetia el mismo bloque de
 * InitialContext + lookup + manejo de NamingException. Aqui se centraliza en un
 * unico punto, con cache por-JVM del stub remoto (los @Stateless son
 * reutilizables) para evitar un lookup JNDI por cada peticion.
 */
package com.RecetarioWeb.Controller.support;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class EjbLocator {

    private static final Logger LOG = Logger.getLogger(EjbLocator.class.getName());

    /** Nombre del modulo EJB tal y como se despliega en Payara. */
    private static final String EJB_MODULE = "Recetario-Gestion-ejb";
    /** Sufijo de las interfaces @Remote (PersonaBeanRemote -> PersonaBean). */
    private static final String REMOTE_SUFFIX = "Remote";
    /** Plantilla del nombre portable global de un EJB: java:global/<modulo>/<bean>!<interfaz>. */
    private static final String JNDI_TEMPLATE = "java:global/%s/%s!%s";

    private static final ConcurrentMap<Class<?>, Object> CACHE = new ConcurrentHashMap<Class<?>, Object>();

    private EjbLocator() {
    }

    /**
     * Devuelve (y cachea) el stub remoto de la interfaz indicada.
     *
     * @throws IllegalStateException si el EJB no se puede resolver
     */
    @SuppressWarnings("unchecked")
    public static <T> T lookup(Class<T> remoteInterface) {
        Object cached = CACHE.get(remoteInterface);
        if (cached == null) {
            cached = doLookup(remoteInterface);
            CACHE.put(remoteInterface, cached);
        }
        return (T) cached;
    }

    private static Object doLookup(Class<?> remoteInterface) {
        String simpleName = remoteInterface.getSimpleName();
        String beanName = simpleName.endsWith(REMOTE_SUFFIX)
                ? simpleName.substring(0, simpleName.length() - REMOTE_SUFFIX.length())
                : simpleName;
        String jndiName = String.format(JNDI_TEMPLATE, EJB_MODULE, beanName, remoteInterface.getName());
        try {
            Object stub = new InitialContext().lookup(jndiName);
            LOG.log(Level.FINE, "EJB resuelto: {0}", jndiName);
            return stub;
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, "No se pudo resolver el EJB remoto: " + jndiName, e);
            throw new IllegalStateException("EJB remoto no disponible: " + jndiName, e);
        }
    }
}
