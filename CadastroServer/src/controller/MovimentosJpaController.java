/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import model.Movimentos;
import model.Usuarios;

/**
 *
 * @author rafae
 */
public class MovimentosJpaController implements Serializable {
      private final EntityManagerFactory emf;

    public MovimentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Movimentos mov) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mov);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (em != null && em.isOpen())
                em.close();
        }
            
    }
}
