/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import model.Usuarios;

/**
 *
 * @author rafae
 */
public class UsuariosJpaController {
    private final EntityManagerFactory emf;

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Usuarios findUsuarioByLoginAndSenha(String login, String senha) {
        EntityManager em = getEntityManager();
        try {
            return (Usuarios) em.createQuery("SELECT u FROM Usuarios u WHERE u.login = :login AND u.senha = :senha")
                               .setParameter("login", login)
                               .setParameter("senha", senha)
                               .getSingleResult();
        } catch (NoResultException e) {
            return null; // Usuário não encontrado
        }
    }
    
}
