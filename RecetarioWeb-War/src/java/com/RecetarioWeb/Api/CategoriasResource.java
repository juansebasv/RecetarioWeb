/*
 * Recurso REST de solo lectura de las categorias del catalogo.
 */
package com.RecetarioWeb.Api;

import com.RecetarioWeb.Api.dto.CategoriaDto;
import com.RecetarioWeb.Beans.CetagoriaBeanRemote;
import com.RecetarioWeb.Entitys.Categoria;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.RecetarioWeb.Controller.support.EjbLocator;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Categorias")
public class CategoriasResource {

    private final CetagoriaBeanRemote categoriaBean = EjbLocator.lookup(CetagoriaBeanRemote.class);

    @GET
    @Operation(summary = "Listar categorias", description = "Devuelve todas las categorias del catalogo.")
    @APIResponse(responseCode = "200", description = "Listado de categorias",
            content = @Content(schema = @Schema(implementation = CategoriaDto[].class)))
    public List<CategoriaDto> listar() {
        @SuppressWarnings("unchecked")
        List<Categoria> categorias = categoriaBean.findAll();
        if (categorias == null) {
            categorias = new ArrayList<Categoria>();
        }
        return categorias.stream().map(CategoriaDto::from).collect(Collectors.toList());
    }

    @GET
    @Path("/{nombre}")
    @Operation(summary = "Obtener una categoria por su nombre")
    @APIResponse(responseCode = "200", description = "La categoria solicitada",
            content = @Content(schema = @Schema(implementation = CategoriaDto.class)))
    @APIResponse(responseCode = "404", description = "No existe una categoria con ese nombre")
    public CategoriaDto porNombre(
            @Parameter(description = "Nombre exacto de la categoria", required = true,
                    example = "Platos fuertes")
            @PathParam("nombre") String nombre) {

        Categoria categoria = categoriaBean.findByName(nombre);
        if (categoria == null) {
            throw new NotFoundException("No existe una categoria con el nombre: " + nombre);
        }
        return CategoriaDto.from(categoria);
    }
}
