package br.com.fiap.GreenMind.resource;

import br.com.fiap.GreenMind.dto.ProjetoDto;
import br.com.fiap.GreenMind.model.Projeto;
import br.com.fiap.GreenMind.service.ProjetoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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

    // MÉTODO POST (Cadastrar Projeto)
    @POST
    public Response cadastrarProjeto(ProjetoDto dto, @Context UriInfo uriInfo) {
        try {
            Projeto projeto = modelMapper.map(dto, Projeto.class);
            projetoService.salvarProjeto(projeto, dto.getCategoriaId());

            UriBuilder uri = uriInfo.getAbsolutePathBuilder();
            uri.path(String.valueOf(projeto.getIdProj()));

            return Response.created(uri.build()).entity(projeto).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    // MÉTODO GET (Listar Projetos)
    @GET
    public Response listarProjetos() throws SQLException {
        List<Projeto> projetos = projetoService.listarProjetos();
        return Response.ok(projetos).build();
    }

    // MÉTODO GET WITH ID (Buscar Projeto por ID)
    @GET
    @Path("/{id}")
    public Response buscarProjetoPorId(@PathParam("id") Long id) {
        try {
            Optional<Projeto> projetoOpt = projetoService.buscarProjetoPorId(id);
            if (projetoOpt.isPresent()) {
                return Response.ok(projetoOpt.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Projeto não encontrado").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar projeto: " + e.getMessage()).build();
        }
    }

    // MÉTODO PUT (Alterar Projeto)
    @PUT
    @Path("/{id}")
    public Response alterarProjeto(ProjetoDto dto, @PathParam("id") Long id) {
        try {
            projetoService.alterarProjeto(dto, id);
            return Response.ok("Projeto atualizado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar projeto: " + e.getMessage()).build();
        }
    }

    // MÉTODO DELETE (Excluir Projeto)
    @DELETE
    @Path("/{id}")
    public Response excluirProjeto(@PathParam("id") Long id) {
        try {
            projetoService.excluirProjeto(id);
            return Response.ok("Projeto excluído com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir projeto: " + e.getMessage()).build();
        }
    }

    // MÉTODO GET (Listar Projetos por Categoria)
    @GET
    @Path("/categoria/{nomeCategoria}")
    public Response listarProjetosPorCategoria(@PathParam("nomeCategoria") String nomeCategoria) {
        try {
            List<Projeto> projetos = projetoService.listarProjetosPorCategoria(nomeCategoria);
            if (projetos.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum projeto encontrado para a categoria: " + nomeCategoria).build();
            }
            return Response.ok(projetos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar projetos por categoria: " + e.getMessage()).build();
        }
    }
}