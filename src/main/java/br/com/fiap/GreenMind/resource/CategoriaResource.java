package br.com.fiap.GreenMind.resource;

import br.com.fiap.GreenMind.dto.CategoriaDto;
import br.com.fiap.GreenMind.model.Categoria;
import br.com.fiap.GreenMind.service.CategoriaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/categorias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    private CategoriaService categoriaService;
    private ModelMapper modelMapper;

    public CategoriaResource() {
        this.categoriaService = new CategoriaService();
        this.modelMapper = new ModelMapper();
    }

    @POST
    public Response cadastrarCategoria(CategoriaDto dto, @Context UriInfo uriInfo) throws SQLException {
        try {
            Categoria categoria = modelMapper.map(dto, Categoria.class);
            categoriaService.salvarCategoria(categoria);
            return Response.status(Response.Status.CREATED).entity("Categoria adicionada com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar categoria: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarCategorias() throws SQLException {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return Response.ok(categorias).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarCategoriaPorId(@PathParam("id") Long id) {
        try {
            Optional<Categoria> categoriaOpt = categoriaService.buscarCategoriaPorId(id);
            if (categoriaOpt.isPresent()) {
                return Response.ok(categoriaOpt.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Categoria não encontrada").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao acessar o banco de dados").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response alterarCategoria(CategoriaDto dto, @PathParam("id") Long id) {
        try {
            Categoria categoria = modelMapper.map(dto, Categoria.class);
            categoria.setId(id);
            categoriaService.alterarCategoria(categoria);
            return Response.ok("Categoria atualizada com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar categoria").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirCategoria(@PathParam("id") Long id) throws SQLException {
        categoriaService.excluirCategoria(id);
        return Response.ok("Categoria excluída com sucesso!").build();
    }
}

