/*
 * Recurso REST de solo lectura del catalogo de recetas.
 */
package com.RecetarioWeb.Api;

import com.RecetarioWeb.Api.dto.RecetaDto;
import com.RecetarioWeb.Beans.RecetaBeanRemote;
import com.RecetarioWeb.Entitys.Receta;
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

@Path("/recetas")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Recetas")
public class RecetasResource {

    private final RecetaBeanRemote recetaBean = EjbLocator.lookup(RecetaBeanRemote.class);

    @GET
    @Operation(summary = "Listar recetas",
            description = "Devuelve todas las recetas del catalogo. Con el parametro "
                    + "`categoriaId` se filtra por categoria.")
    @APIResponse(responseCode = "200", description = "Listado de recetas",
            content = @Content(schema = @Schema(implementation = RecetaDto[].class)))
    public List<RecetaDto> listar(
            @Parameter(description = "Id de categoria por el que filtrar", example = "0")
            @QueryParam("categoriaId") Integer categoriaId) {

        @SuppressWarnings("unchecked")
        List<Receta> recetas = recetaBean.findAll();
        if (recetas == null) {
            recetas = new ArrayList<Receta>();
        }
        return recetas.stream()
                .filter(r -> categoriaId == null || categoriaId.equals(r.getIdcatreceta()))
                .map(RecetaDto::from)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{nombre}")
    @Operation(summary = "Obtener una receta por su nombre")
    @APIResponse(responseCode = "200", description = "La receta solicitada",
            content = @Content(schema = @Schema(implementation = RecetaDto.class)))
    @APIResponse(responseCode = "404", description = "No existe una receta con ese nombre")
    public RecetaDto porNombre(
            @Parameter(description = "Nombre exacto de la receta", required = true,
                    example = "Tiramisu clasico con mascarpone y espresso")
            @PathParam("nombre") String nombre) {

        Receta receta = recetaBean.findByName(nombre);
        if (receta == null) {
            throw new NotFoundException("No existe una receta con el nombre: " + nombre);
        }
        return RecetaDto.from(receta);
    }
}
