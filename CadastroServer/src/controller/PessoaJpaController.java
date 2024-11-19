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
import model.Pessoa;
import model.Produtos;

/**
 *
 * @author rafae
 */
public class PessoaJpaController implements Serializable {
      private final EntityManagerFactory emf;

    public PessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Pessoa findPessoaById(int id) 
    {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT p FROM Pessoa p WHERE p.pessoaID = :pessoaID", Pessoa.class)
                               .setParameter("pessoaID", id)
                               .getSingleResult();
    }
}
