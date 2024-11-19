/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Produtos;

/**
 *
 * @author rafae
 */
public class ProdutosJpaController {
    private final EntityManagerFactory emf;

    public ProdutosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Produtos> findProdutoEntities() 
    {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT p FROM Produtos p", Produtos.class).getResultList();
    }
    
    public Produtos findProdutoById(int id) 
    {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT p FROM Produtos p WHERE p.produtoID = :produtoID", Produtos.class)
                               .setParameter("produtoID", id)
                               .getSingleResult();
    }
    
    public void edit(Produtos produto) 
    {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
    }
}
