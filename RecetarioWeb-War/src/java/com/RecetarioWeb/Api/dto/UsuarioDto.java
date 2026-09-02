/*
 * Representacion REST de un usuario registrado.
 *
 * IMPORTANTE: nunca incluye la contrasena (persona.pass) ni ningun otro dato
 * sensible; solo informacion de perfil publica.
 */
package com.RecetarioWeb.Api.dto;

import com.RecetarioWeb.Entitys.Persona;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(name = "Usuario", description = "Usuario registrado (perfil publico, sin credenciales)")
public class UsuarioDto {

    @Schema(description = "Codigo del usuario (clave natural)", example = "laura")
    public String codigo;

    @Schema(description = "Nombre completo", example = "Laura Gomez Restrepo")
    public String nombre;

    @Schema(description = "Nombre de usuario para iniciar sesion", example = "laura")
    public String username;

    @Schema(description = "Correo electronico", example = "laura.gomez@example.com")
    public String email;

    @Schema(description = "Pais de residencia", example = "Colombia")
    public String pais;

    @Schema(description = "Ciudad de residencia", example = "Medellin")
    public String ciudad;

    @Schema(description = "Rol: 1 = administrador, 2 = usuario estandar", example = "2", enumeration = {"1", "2"})
    public Integer rol;

    @Schema(description = "Si el usuario tiene una sesion activa", example = "false")
    public Boolean activo;

    @Schema(description = "Fecha de nacimiento (ISO-8601)", example = "1995-06-20")
    public String fechaNacimiento;

    public UsuarioDto() {
    }

    public UsuarioDto(String codigo, String nombre, String username, String email,
                      String pais, String ciudad, Integer rol, Boolean activo, String fechaNacimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.pais = pais;
        this.ciudad = ciudad;
        this.rol = rol;
        this.activo = activo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public static UsuarioDto from(Persona p) {
        return new UsuarioDto(p.getCodigo(), p.getNombre(), p.getUsername(), p.getEmail(),
                p.getPais(), p.getCiudad(), p.getRol(), p.getActivo(), Dates.iso(p.getFechanacimeinto()));
    }
}
