/*
 * Representacion REST de una categoria del catalogo.
 */
package com.RecetarioWeb.Api.dto;

import com.RecetarioWeb.Entitys.Categoria;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Categoria", description = "Categoria para agrupar recetas")
public class CategoriaDto {

    @Schema(description = "Identificador numerico interno de la categoria", example = "2")
    public int id;

    @Schema(description = "Nombre de la categoria (clave natural)", example = "Platos fuertes")
    public String nombre;

    @Schema(description = "Descripcion de la categoria")
    public String descripcion;

    @Schema(description = "Fecha de alta (ISO-8601)", example = "2024-01-10")
    public String fecha;

    public CategoriaDto() {
    }

    public CategoriaDto(int id, String nombre, String descripcion, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public static CategoriaDto from(Categoria c) {
        return new CategoriaDto(c.getIdcat(), c.getNombrecat(), c.getDescripcion(), Dates.iso(c.getFechacat()));
    }
}
