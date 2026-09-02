/*
 * Representacion REST de una empresa proveedora.
 */
package com.RecetarioWeb.Api.dto;

import com.RecetarioWeb.Entitys.Empresa;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Empresa", description = "Empresa proveedora destacada")
public class EmpresaDto {

    @Schema(description = "Identificador numerico interno", example = "1")
    public int id;

    @Schema(description = "Nombre de la empresa (clave natural)", example = "Lacteos del Valle")
    public String nombre;

    @Schema(description = "Descripcion de la empresa")
    public String descripcion;

    @Schema(description = "Ruta o URL de la imagen asociada", example = "img/logo.png", nullable = true)
    public String imagen;

    public EmpresaDto() {
    }

    public EmpresaDto(int id, String nombre, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public static EmpresaDto from(Empresa e) {
        return new EmpresaDto(e.getIdemp(), e.getNombreemp(), e.getDescripcionemp(), e.getImagenemp());
    }
}
