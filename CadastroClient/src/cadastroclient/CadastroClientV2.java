/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.List;
import model.Produtos;

/**
 *
 * @author rafae
 */
public class CadastroClientV2 {
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            // Conectar ao servidor na porta 4321
            socket = new Socket("localhost", 4321);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Login e senha
            System.out.print("Login: ");
            String login = reader.readLine();
            System.out.print("Senha: ");
            String senha = reader.readLine();

            // Enviar login e senha para o servidor
            out.writeObject(login);
            out.writeObject(senha);

            // Lê a resposta do servidor (validação do login)
            String resposta = (String) in.readObject();
            System.out.println(resposta);
            if (resposta.startsWith("Login bem-sucedido")) {
                System.out.println("Login bem-sucedido!");

                // Menu - inicio
                while (true) {
                    exibirMenu();
                    String comando = reader.readLine().toUpperCase();
                    if ("L".equals(comando)) {
                        out.writeObject("L"); // Listar produtos
                        out.flush();
                        Object obj = in.readObject();
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
                    } else if ("E".equals(comando) || "S".equals(comando)) {
                        processarMovimento(comando);
                        Object obj = in.readObject();
                        System.out.println(obj);
                    } else if ("X".equals(comando)) {
                        System.out.println("Finalizando o cliente.");
                        break;
                    } else {
                        System.out.println("Comando inválido. Tente novamente.");
                    }
                }
            } else {
                System.out.println("Login ou senha inválidos.");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("L - Listar produtos");
        System.out.println("E - Entrada de produto");
        System.out.println("S - Saída de produto");
        System.out.println("X - Finalizar");
        System.out.print("Escolha um comando: ");
    }

    private static void processarMovimento(String tipo) throws IOException {
        // Enviar tipo de movimento (E ou S)
        System.out.println("Enviando comando para o server: " + tipo);
        out.writeObject(tipo);
        
        // Obter ID da pessoa
        System.out.print("Digite o ID da pessoa: ");
        Integer pessoaId = Integer.parseInt(reader.readLine());
        out.writeInt(pessoaId);

        // Obter ID do produto
        System.out.print("Digite o ID do produto: ");
        Integer produtoId = Integer.parseInt(reader.readLine());
        out.writeInt(produtoId);

        // Obter quantidade
        System.out.print("Digite a quantidade: ");
        Integer quantidade = Integer.parseInt(reader.readLine());
        out.writeInt(quantidade);

        // Obter valor unitário
        System.out.print("Digite o valor unitário: ");
        BigDecimal valorUnitario = new BigDecimal(reader.readLine());
        out.writeObject(valorUnitario);
        
        //;out.flush();
    }

}
