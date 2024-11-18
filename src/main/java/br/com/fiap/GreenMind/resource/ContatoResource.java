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

    @GET
    public Response listarContatos() throws SQLException {
        List<Contato> contatos = contatoService.listarContatos();
        return Response.ok(contatos).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirContato(@PathParam("id") Long id) throws SQLException {
        contatoService.excluirContato(id);
        return Response.ok("Contato excluído com sucesso!").build();
    }
}
