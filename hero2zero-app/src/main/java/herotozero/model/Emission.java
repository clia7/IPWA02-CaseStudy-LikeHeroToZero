package herotozero.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "emissions")
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Land darf nicht leer sein")
    @Column(nullable = false)
    private String country;

    @NotBlank(message = "Firma darf nicht leer sein")
    @Column(nullable = false)
    private String company;

    @NotBlank(message = "Sektor darf nicht leer sein")
    @Column(nullable = false)
    private String sector;

    @Column(name = "emissions_mt", nullable = false)
    @Positive(message = "Emissionen müssen positiv sein")
    private double emissionsMt;

    @Column(nullable = false)
    @Positive(message = "Jahr muss positiv sein")
    private int year;

    public Emission() {
    }

    public Emission(String country, String company, String sector, double emissionsMt, int year) {
        this.country = country;
        this.company = company;
        this.sector = sector;
        this.emissionsMt = emissionsMt;
        this.year = year;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }

    public double getEmissionsMt() {
        return emissionsMt;
    }
    public void setEmissionsMt(double emissionsMt) {
        this.emissionsMt = emissionsMt;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}