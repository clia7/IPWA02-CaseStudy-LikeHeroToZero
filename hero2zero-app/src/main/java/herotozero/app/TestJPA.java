package herotozero.app;

import herotozero.model.Emission;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hero2zeroPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Emission e1 = new Emission("Germany", "AutoTech AG", "Automotive", 800.5, 2020);
        Emission e2 = new Emission("France", "GreenEnergy", "Energy", 600.3, 2020);

        em.persist(e1);
        em.persist(e2);

        em.getTransaction().commit();

        List<Emission> emissions = em.createQuery("SELECT e FROM Emission e", Emission.class).getResultList();

        System.out.println("Emissions in DB:");
        for (Emission e : emissions) {
            System.out.println(e.getYear() + " | " 
                + e.getCountry() + " | " 
                + e.getCompany() + " | " 
                + e.getSector() + " | " 
                + e.getEmissionsMt());  
        }

        em.close();
        emf.close();
    }
}