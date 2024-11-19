/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastroclient;

import model.Produtos;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author rafae
 */
public class CadastroClient {

   public static void main(String[] args) {
        String host = "localhost";
        int port = 4321;

        try (
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            System.out.println("Conexão estabelecida com o servidor em " + host + ":" + port);

            out.writeObject("op1"); // Login
            out.writeObject("op1"); // Senha
            out.flush();

            String response = (String) in.readObject();
            System.out.println("Resposta do servidor: " + response);

            if (!response.startsWith("Login bem-sucedido")) {
                System.out.println("Não foi possível autenticar. Encerrando cliente.");
                return;
            }

            out.writeObject("L");
            out.flush();

            Object obj = in.readObject();
            
            if (!response.startsWith("Login bem-sucedido")) {
                System.out.println("Não foi possível autenticar. Encerrando cliente.");
                return;
            }
            
            if (obj instanceof List) {
                List<?> produtos = (List<?>) obj;

                System.out.println("Produtos recebidos:");
                for (Object item : produtos) {
                    if (item instanceof Produtos) {
                        Produtos produto = (Produtos) item;
                        System.out.println("Produto: " + produto.getNome());
                    }
                }
            } else {
                System.out.println("Erro ao receber lista de produtos.");
            }

            out.writeObject("S");
            out.flush();

            System.out.println("Conexão encerrada.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
    
}
