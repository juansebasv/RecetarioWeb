/*
 * Representacion REST de un tip de cocina.
 */
package com.RecetarioWeb.Api.dto;

import com.RecetarioWeb.Entitys.Tip;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Tip", description = "Consejo de tecnica de cocina redactado por la comunidad")
public class TipDto {

    @Schema(description = "Identificador numerico interno", example = "1")
    public int id;

    @Schema(description = "Titulo del tip (clave natural)", example = "El sofrito, la base invisible de casi todo")
    public String nombre;

    @Schema(description = "Codigo del usuario que lo escribio", example = "admin")
    public String usuarioCodigo;

    @Schema(description = "Nombre visible del autor", example = "Cocina RecetarioWeb")
    public String autor;

    @Schema(description = "Contenido del tip")
    public String descripcion;

    @Schema(description = "Fecha de publicacion (ISO-8601)", example = "2024-02-04")
    public String fecha;

    public TipDto() {
    }

    public TipDto(int id, String nombre, String usuarioCodigo, String autor, String descripcion, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.usuarioCodigo = usuarioCodigo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public static TipDto from(Tip t) {
        return new TipDto(t.getIdtip(), t.getNombretip(), t.getIdusertip(),
                t.getAutortip(), t.getDescripciontip(), Dates.iso(t.getFechatip()));
    }
}
