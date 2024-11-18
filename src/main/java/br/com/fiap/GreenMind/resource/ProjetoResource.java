package br.com.fiap.GreenMind.resource;

import br.com.fiap.GreenMind.dto.ProjetoDto;
import br.com.fiap.GreenMind.model.Projeto;
import br.com.fiap.GreenMind.service.ProjetoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/projetos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjetoResource {

    private ProjetoService projetoService;
    private ModelMapper modelMapper;

    public ProjetoResource() {
        this.projetoService = new ProjetoService();
        this.modelMapper = new ModelMapper();
    }

    @POST
    public Response cadastrarProjeto(ProjetoDto dto, @Context UriInfo uriInfo) {
        try {
            Projeto projeto = modelMapper.map(dto, Projeto.class);
            projetoService.salvarProjeto(projeto, dto.getCategoriaId());

            UriBuilder uri = uriInfo.getAbsolutePathBuilder();
            uri.path(String.valueOf(projeto.getIdProj()));

            return Response.created(uri.build()).entity(projeto).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao salvar projeto: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarProjetos() throws SQLException {
        List<Projeto> projetos = projetoService.listarProjetos();
        return Response.ok(projetos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarProjetoPorId(@PathParam("id") Long id) throws SQLException {
        Optional<Projeto> projetoOpt = projetoService.buscarProjetoPorId(id);
        return projetoOpt.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Projeto não encontrado"))
                .build();
    }
}
