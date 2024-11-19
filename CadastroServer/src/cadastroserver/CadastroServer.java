/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastroserver;

import controller.MovimentosJpaController;
import controller.PessoaJpaController;
import controller.ProdutosJpaController;
import controller.UsuariosJpaController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class CadastroServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 4321;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");

        ProdutosJpaController ctrl = new ProdutosJpaController(emf);
        UsuariosJpaController ctrlUsu = new UsuariosJpaController(emf);
        MovimentosJpaController ctrlMov = new MovimentosJpaController(emf);
        PessoaJpaController ctrlPessoa = new PessoaJpaController(emf);
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado na porta " + port);

            while (true) {
                System.out.println("Aguardando conexões...");
                Socket clientSocket = serverSocket.accept(); 
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                CadastroThread thread = new CadastroThread(ctrl, ctrlUsu, ctrlMov, ctrl, ctrlPessoa, clientSocket);
                thread.start();

                System.out.println("Thread criada para o cliente " + clientSocket.getInetAddress());
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        } finally {
            emf.close();
            System.out.println("EntityManagerFactory fechado.");
        }
    }
    
}
