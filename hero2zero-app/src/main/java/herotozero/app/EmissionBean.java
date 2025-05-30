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
                System.out.println("no emissionen for country " + selectedCountry + " found.");
            } else {
                System.out.println(
                        "emissionen load for country " + selectedCountry + ": " + latestCountryEmissions.size());
                for (Emission e : latestCountryEmissions) {
                    System.out.println("company: " + e.getCompany() + ", year: " + e.getYear() + ", emissionen: "
                            + e.getEmissionsMt());
                }
            }
        } else {
            latestCountryEmissions = null;
            System.out.println("no country selected");
        }
    }

    public String save() {
        try {
            emissionDAO.save(emission);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "success", "emission successfully saved"));
            emission = new Emission();
            saved = true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "failed",
                            "error while saving: " + e.getMessage()));
            saved = false;
        }
        return null;
    }

    public void delete(Long id) {
        try {
            emissionDAO.delete(id);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted", "emission deleted"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "failed",
                            "deleting failed: " + e.getMessage()));
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