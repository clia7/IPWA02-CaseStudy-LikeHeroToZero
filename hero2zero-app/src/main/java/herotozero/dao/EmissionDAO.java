package herotozero.dao;

import herotozero.model.Emission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EmissionDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hero2zeroPU");

    public void save(Emission emission) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emission);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Emission findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Emission.class, id);
        } finally {
            em.close();
        }
    }

    public List<Emission> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Emission e ORDER BY e.year DESC", Emission.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Emission emission) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(emission);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Emission emission = em.find(Emission.class, id);
            if (emission != null) {
                em.getTransaction().begin();
                em.remove(emission);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}