package br.com.fiap.GreenMind.resource;

import br.com.fiap.GreenMind.dto.ContatoDto;
import br.com.fiap.GreenMind.model.Contato;
import br.com.fiap.GreenMind.service.ContatoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/contatos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContatoResource {

    private ContatoService contatoService;
    private ModelMapper modelMapper;

    public ContatoResource() {
        this.contatoService = new ContatoService();
        this.modelMapper = new ModelMapper();
    }

    // MÉTODO POST (Cadastrar Contato)
    @POST
    public Response salvarContato(@Valid ContatoDto dto, @Context UriInfo uriInfo) {
        try {
            Contato contato = modelMapper.map(dto, Contato.class);
            contatoService.salvarContato(contato);

            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(contato.getIdContato()));

            return Response.created(uriBuilder.build()).entity(contato).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar contato: " + e.getMessage()).build();
        }
    }

    // MÉTODO GET (Listar Contatos)
    @GET
    public Response listarContatos() throws SQLException {
        List<Contato> contatos = contatoService.listarContatos();
        return Response.ok(contatos).build();
    }


    // MÉTODO DELETE (Excluir Contato)
    @DELETE
    @Path("/{id}")
    public Response excluirContato(@PathParam("id") Long id) {
        try {
            contatoService.excluirContato(id);
            return Response.ok("Contato excluído com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir contato: " + e.getMessage()).build();
        }
    }
}
