/*
 * Recurso REST de solo lectura de las empresas proveedoras.
 */
package com.RecetarioWeb.Api;

import com.RecetarioWeb.Api.dto.EmpresaDto;
import com.RecetarioWeb.Beans.EmpresaBeanRemote;
import com.RecetarioWeb.Entitys.Empresa;
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

@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Empresas")
public class EmpresasResource {

    private final EmpresaBeanRemote empresaBean = EjbLocator.lookup(EmpresaBeanRemote.class);

    @GET
    @Operation(summary = "Listar empresas", description = "Devuelve todas las empresas proveedoras destacadas.")
    @APIResponse(responseCode = "200", description = "Listado de empresas",
            content = @Content(schema = @Schema(implementation = EmpresaDto[].class)))
    public List<EmpresaDto> listar() {
        @SuppressWarnings("unchecked")
        List<Empresa> empresas = empresaBean.findAll();
        if (empresas == null) {
            empresas = new ArrayList<Empresa>();
        }
        return empresas.stream().map(EmpresaDto::from).collect(Collectors.toList());
    }

    @GET
    @Path("/{nombre}")
    @Operation(summary = "Obtener una empresa por su nombre")
    @APIResponse(responseCode = "200", description = "La empresa solicitada",
            content = @Content(schema = @Schema(implementation = EmpresaDto.class)))
    @APIResponse(responseCode = "404", description = "No existe una empresa con ese nombre")
    public EmpresaDto porNombre(
            @Parameter(description = "Nombre exacto de la empresa", required = true,
                    example = "Lacteos del Valle")
            @PathParam("nombre") String nombre) {

        Empresa empresa = empresaBean.findByName(nombre);
        if (empresa == null) {
            throw new NotFoundException("No existe una empresa con el nombre: " + nombre);
        }
        return EmpresaDto.from(empresa);
    }
}
