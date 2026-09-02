/*
 * Recurso REST de solo lectura de los usuarios registrados.
 *
 * Devuelve unicamente el perfil publico ({@link UsuarioDto}); nunca la
 * contrasena.
 */
package com.RecetarioWeb.Api;

import com.RecetarioWeb.Api.dto.UsuarioDto;
import com.RecetarioWeb.Beans.PersonaBeanRemote;
import com.RecetarioWeb.Entitys.Persona;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.RecetarioWeb.Controller.support.EjbLocator;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Usuarios")
public class UsuariosResource {

    private final PersonaBeanRemote personaBean = EjbLocator.lookup(PersonaBeanRemote.class);

    @GET
    @Operation(summary = "Listar usuarios",
            description = "Devuelve el perfil publico de los usuarios registrados. Con el "
                    + "parametro `rol` (1 = administrador, 2 = usuario) se filtra por rol.")
    @APIResponse(responseCode = "200", description = "Listado de usuarios",
            content = @Content(schema = @Schema(implementation = UsuarioDto[].class)))
    public List<UsuarioDto> listar(
            @Parameter(description = "Rol por el que filtrar (1 o 2)", example = "2")
            @QueryParam("rol") Integer rol) {

        @SuppressWarnings("unchecked")
        List<Persona> personas = personaBean.findAll();
        if (personas == null) {
            personas = new ArrayList<Persona>();
        }
        return personas.stream()
                .filter(p -> rol == null || rol.equals(p.getRol()))
                .map(UsuarioDto::from)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{codigo}")
    @Operation(summary = "Obtener un usuario por su codigo")
    @APIResponse(responseCode = "200", description = "El usuario solicitado",
            content = @Content(schema = @Schema(implementation = UsuarioDto.class)))
    @APIResponse(responseCode = "404", description = "No existe un usuario con ese codigo")
    public UsuarioDto porCodigo(
            @Parameter(description = "Codigo del usuario", required = true, example = "laura")
            @PathParam("codigo") String codigo) {

        Persona persona = personaBean.findByCodigo(codigo);
        if (persona == null) {
            throw new NotFoundException("No existe un usuario con el codigo: " + codigo);
        }
        return UsuarioDto.from(persona);
    }
}
