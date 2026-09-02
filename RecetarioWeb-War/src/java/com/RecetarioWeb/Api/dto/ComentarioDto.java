/*
 * Representacion REST de un comentario sobre una receta.
 */
package com.RecetarioWeb.Api.dto;

import com.RecetarioWeb.Entitys.Comentario;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Comentario", description = "Comentario publicado por un usuario sobre una receta")
public class ComentarioDto {

    @Schema(description = "Identificador autogenerado del comentario", example = "5")
    public Integer id;

    @Schema(description = "Texto del comentario")
    public String texto;

    @Schema(description = "Codigo del usuario que comento", example = "carlos")
    public String usuarioCodigo;

    @Schema(description = "Id de la receta comentada", example = "0")
    public Integer recetaId;

    @Schema(description = "Fecha del comentario (ISO-8601)", example = "2024-02-20")
    public String fecha;

    public ComentarioDto() {
    }

    public ComentarioDto(Integer id, String texto, String usuarioCodigo, Integer recetaId, String fecha) {
        this.id = id;
        this.texto = texto;
        this.usuarioCodigo = usuarioCodigo;
        this.recetaId = recetaId;
        this.fecha = fecha;
    }

    public static ComentarioDto from(Comentario c) {
        return new ComentarioDto(c.getIdcomen(), c.getTextocomen(), c.getIdusercomen(),
                c.getIdrecetacomen(), Dates.iso(c.getFechacomen()));
    }
}
