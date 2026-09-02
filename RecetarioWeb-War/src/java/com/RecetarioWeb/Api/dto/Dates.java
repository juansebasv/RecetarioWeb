/*
 * Formateo de fechas para los DTO de la API.
 *
 * Las entidades usan @Temporal(DATE) (fecha sin hora), asi que la API las expone
 * como cadena ISO "aaaa-mm-dd" en lugar de un timestamp con medianoche ficticia.
 */
package com.RecetarioWeb.Api.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

final class Dates {

    private static final String ISO_DATE = "yyyy-MM-dd";

    private Dates() {
    }

    static String iso(Date date) {
        return date == null ? null : new SimpleDateFormat(ISO_DATE).format(date);
    }
}
