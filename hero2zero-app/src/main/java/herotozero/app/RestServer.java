package herotozero.app;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

import org.glassfish.jersey.jackson.JacksonFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.validation.ValidationFeature;

@ApplicationPath("/api")
public class RestServer extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(EmissionResource.class);
        resources.add(JacksonFeature.class);
        resources.add(ValidationExceptionMapper.class);
        resources.add(ValidationFeature.class); 
        return resources;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);

        singletons.add(provider);
        return singletons;
    }
}