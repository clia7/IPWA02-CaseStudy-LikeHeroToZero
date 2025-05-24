package herotozero.app;

import herotozero.dao.EmissionDAO;
import herotozero.model.Emission;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class EmissionService {

    private final EmissionDAO emissionDAO = new EmissionDAO();

    public List<Emission> getLatestByCountry(String country) {
        return emissionDAO.findLatestByCountry(country);
    }
}