package herotozero.app;

import herotozero.dao.EmissionDAO;
import herotozero.model.Emission;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("emissionBean")
@ViewScoped
public class EmissionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EmissionDAO emissionDAO = new EmissionDAO();
    private Emission emission = new Emission();
    private boolean saved;

    private String selectedCountry;
    private List<Emission> latestCountryEmissions;

    // Getter und Setter
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

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<Emission> getLatestCountryEmissions() {
        return latestCountryEmissions;
    }

    public void loadLatestEmissionsForCountry() {
        if (selectedCountry != null && !selectedCountry.isEmpty()) {
            latestCountryEmissions = emissionDAO.findLatestByCountry(selectedCountry);

            if (latestCountryEmissions == null) {
                System.out.println("DAO liefert null zurück!");
            } else if (latestCountryEmissions.isEmpty()) {
                System.out.println("Keine Emissionen für Land " + selectedCountry + " gefunden.");
            } else {
                System.out.println(
                        "Emissionen geladen für Land " + selectedCountry + ": " + latestCountryEmissions.size());
                for (Emission e : latestCountryEmissions) {
                    System.out.println("Firma: " + e.getCompany() + ", Jahr: " + e.getYear() + ", Emissionen: "
                            + e.getEmissionsMt());
                }
            }
        } else {
            latestCountryEmissions = null;
            System.out.println("Kein Land ausgewählt.");
        }
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
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler",
                            "Fehler beim Speichern: " + e.getMessage()));
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
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler",
                            "Löschen fehlgeschlagen: " + e.getMessage()));
        }
    }

    public List<SelectItem> getAvailableCountries() {
        List<String> countries = emissionDAO.findAllCountries();
        List<SelectItem> items = new ArrayList<>();
        for (String country : countries) {
            items.add(new SelectItem(country, country));
        }
        return items;
    }

    public double getTotalEmissions() {
        if (latestCountryEmissions == null)
            return 0.0;
        double sum = 0.0;
        for (Emission e : latestCountryEmissions) {
            sum += e.getEmissionsMt();
        }
        return Math.round(sum * 100.0) / 100.0;
    }
}