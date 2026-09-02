/*
 * Recurso REST de solo lectura de los comentarios de las recetas.
 */
package com.RecetarioWeb.Api;

import com.RecetarioWeb.Api.dto.ComentarioDto;
import com.RecetarioWeb.Beans.ComentarioBeanRemote;
import com.RecetarioWeb.Entitys.Comentario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.RecetarioWeb.Controller.support.EjbLocator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/comentarios")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Comentarios")
public class ComentariosResource {

    private final ComentarioBeanRemote comentarioBean = EjbLocator.lookup(ComentarioBeanRemote.class);

    @GET
    @Operation(summary = "Listar comentarios",
            description = "Devuelve todos los comentarios. Con el parametro `recetaId` se "
                    + "limita a los de una receta concreta.")
    @APIResponse(responseCode = "200", description = "Listado de comentarios",
            content = @Content(schema = @Schema(implementation = ComentarioDto[].class)))
    public List<ComentarioDto> listar(
            @Parameter(description = "Id de la receta por la que filtrar", example = "0")
            @QueryParam("recetaId") Integer recetaId) {

        @SuppressWarnings("unchecked")
        List<Comentario> comentarios = comentarioBean.findAll();
        if (comentarios == null) {
            comentarios = new ArrayList<Comentario>();
        }
        return comentarios.stream()
                .filter(c -> recetaId == null || recetaId.equals(c.getIdrecetacomen()))
                .map(ComentarioDto::from)
                .collect(Collectors.toList());
    }
}
