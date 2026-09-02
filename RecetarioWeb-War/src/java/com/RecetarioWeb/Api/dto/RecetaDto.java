/*
 * Representacion REST de una receta.
 */
package com.RecetarioWeb.Api.dto;

import com.RecetarioWeb.Entitys.Receta;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Receta", description = "Receta publicada por un usuario")
public class RecetaDto {

    @Schema(description = "Identificador numerico interno", example = "1")
    public int id;

    @Schema(description = "Nombre de la receta (clave natural)", example = "Tiramisu clasico con mascarpone y espresso")
    public String nombre;

    @Schema(description = "Codigo del usuario que la publico", example = "laura")
    public String usuarioCodigo;

    @Schema(description = "Nombre visible del autor", example = "Laura Gomez")
    public String autor;

    @Schema(description = "Id de la categoria a la que pertenece", example = "0", nullable = true)
    public Integer categoriaId;

    @Schema(description = "Descripcion y preparacion de la receta")
    public String descripcion;

    @Schema(description = "Ingredientes (texto libre, separados por guion)")
    public String ingredientes;

    @Schema(description = "Fecha de publicacion (ISO-8601)", example = "2024-02-03")
    public String fecha;

    public RecetaDto() {
    }

    public RecetaDto(int id, String nombre, String usuarioCodigo, String autor,
                     Integer categoriaId, String descripcion, String ingredientes, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.usuarioCodigo = usuarioCodigo;
        this.autor = autor;
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.fecha = fecha;
    }

    public static RecetaDto from(Receta r) {
        return new RecetaDto(r.getIdreceta(), r.getNombrereceta(), r.getIduserreceta(),
                r.getAutorreceta(), r.getIdcatreceta(), r.getDescripcionreceta(),
                r.getIngredientes(), Dates.iso(r.getFechareceta()));
    }
}
