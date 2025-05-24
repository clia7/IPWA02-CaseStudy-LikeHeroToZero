package herotozero.app;

import herotozero.model.Emission;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;

@Named
@RequestScoped
public class EmissionViewBean implements Serializable {
    private List<Emission> emissions;

    @PostConstruct
    public void init() {
        Client client = ClientBuilder.newClient();
        emissions = client
            .target("http://localhost:8080/hero2zero-app/api/emissions")
            .request()
            .get(new GenericType<List<Emission>>() {});
    }

    public List<Emission> getEmissions() {
        return emissions;
    }
}