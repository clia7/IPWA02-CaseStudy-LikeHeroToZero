package herotozero.app;

import herotozero.dao.EmissionDAO;
import herotozero.model.Emission;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("emissionBean")
@RequestScoped
public class EmissionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EmissionDAO emissionDAO = new EmissionDAO();
    private Emission emission = new Emission();
    private boolean saved;

    // Getter and Setter
    public Emission getEmission() {
        return emission;
    }

    public void setEmission(Emission emission) {
        this.emission = emission;
    }

    public boolean isSaved() {
        return saved;
    }

    public List<Emission> getAllEmissions() {
        return emissionDAO.findAll();
    }

    public String save() {
        try {
            emissionDAO.save(emission);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Emission erfolgreich gespeichert."));
            emission = new Emission(); 
            saved = true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Fehler beim Speichern: " + e.getMessage()));
            saved = false;
        }
        return null; 
    }

    public void delete(Long id) {
        try {
            emissionDAO.delete(id);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gelöscht", "Emission wurde gelöscht."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Löschen fehlgeschlagen: " + e.getMessage()));
        }
    }
}