/*
 * Recurso REST de solo lectura de los tips de cocina.
 */
package com.RecetarioWeb.Api;

import com.RecetarioWeb.Api.dto.TipDto;
import com.RecetarioWeb.Beans.TipBeanRemote;
import com.RecetarioWeb.Entitys.Tip;
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

@Path("/tips")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Tips")
public class TipsResource {

    private final TipBeanRemote tipBean = EjbLocator.lookup(TipBeanRemote.class);

    @GET
    @Operation(summary = "Listar tips", description = "Devuelve todos los tips de tecnica de cocina.")
    @APIResponse(responseCode = "200", description = "Listado de tips",
            content = @Content(schema = @Schema(implementation = TipDto[].class)))
    public List<TipDto> listar() {
        @SuppressWarnings("unchecked")
        List<Tip> tips = tipBean.findAll();
        if (tips == null) {
            tips = new ArrayList<Tip>();
        }
        return tips.stream().map(TipDto::from).collect(Collectors.toList());
    }

    @GET
    @Path("/{nombre}")
    @Operation(summary = "Obtener un tip por su titulo")
    @APIResponse(responseCode = "200", description = "El tip solicitado",
            content = @Content(schema = @Schema(implementation = TipDto.class)))
    @APIResponse(responseCode = "404", description = "No existe un tip con ese titulo")
    public TipDto porNombre(
            @Parameter(description = "Titulo exacto del tip", required = true,
                    example = "El sofrito, la base invisible de casi todo")
            @PathParam("nombre") String nombre) {

        Tip tip = tipBean.findByName(nombre);
        if (tip == null) {
            throw new NotFoundException("No existe un tip con el titulo: " + nombre);
        }
        return TipDto.from(tip);
    }
}
