package herotozero.app;

import herotozero.dao.EmissionDAO;
import herotozero.model.Emission;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import java.util.List;

@Path("/emissions")
public class EmissionResource {

    private EmissionDAO dao = new EmissionDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Emission> getAllEmissions() {
        return dao.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmission(@Valid Emission emission) {
        dao.save(emission);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Emission getEmissionById(@PathParam("id") Long id) {
        Emission emission = dao.findById(id);
        if (emission == null) {
            throw new NotFoundException("emission ID " + id + " not found");
        }
        return emission;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmission(@PathParam("id") Long id, @Valid Emission updatedEmission) {
        Emission existing = dao.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.setCountry(updatedEmission.getCountry());
        existing.setCompany(updatedEmission.getCompany());
        existing.setSector(updatedEmission.getSector());
        existing.setEmissionsMt(updatedEmission.getEmissionsMt());  
        existing.setYear(updatedEmission.getYear());

        dao.update(existing);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmission(@PathParam("id") Long id) {
        Emission existing = dao.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.delete(id);
        return Response.noContent().build();
    }
}