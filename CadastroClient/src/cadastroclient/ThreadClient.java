/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroclient;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.List;
import model.Produtos;

public class ThreadClient extends Thread {
    private ObjectInputStream entrada;
    private ObjectOutputStream saida;
    private SaidaFrame saidaFrame;
    private final String usuario = "op1"; // Usuário fixo
    private final String senha = "op1";   // Senha fixa

    public ThreadClient(SaidaFrame saidaFrame) {
        this.saidaFrame = saidaFrame;
    }

    @Override
    public void run() {
        try {
            // Conecta ao servidor
            Socket socket = new Socket("localhost", 4321);
            saidaFrame.texto.append("Conectado ao servidor.\n");

            // Inicializa canais de entrada e saída
            saida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            // Envia usuário e senha ao servidor
            saida.writeObject(usuario);
            saida.writeObject(senha);
            saida.flush();
            saidaFrame.texto.append("Usuário e senha enviados.\n");

            // Loop contínuo para leitura do servidor
            while (true) {
                Object obj = entrada.readObject();

                // Atualiza o JTextArea com a mensagem recebida
                SwingUtilities.invokeLater(() -> {
                    if (obj instanceof String) {
                        saidaFrame.texto.append("Mensagem: " + obj + "\n");
                    } else if (obj instanceof List) {
                        List<?> listaProdutos = (List<?>) obj;
                        for (Object item : listaProdutos) {
                            if (item instanceof Produtos) {
                                Produtos produto = (Produtos) item;
                                saidaFrame.texto.append(
                                    "Produto: " + produto.getNome() +
                                    ", Quantidade: " + produto.getQuantidade() + "\n"
                                );
                            }
                        }
                    } else {
                        saidaFrame.texto.append("Objeto desconhecido recebido.\n");
                    }

                    // Atualiza o caret para o final do texto
                    saidaFrame.texto.setCaretPosition(saidaFrame.texto.getDocument().getLength());
                });
            }
        } catch (IOException | ClassNotFoundException e) {
            SwingUtilities.invokeLater(() -> {
                saidaFrame.texto.append("Erro: " + e.getMessage() + "\n");
            });
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Cria o frame e inicializa a thread cliente
        JFrame parentFrame = new JFrame("Cliente");
        SaidaFrame saidaFrame = new SaidaFrame(parentFrame);
        saidaFrame.setVisible(true);

        ThreadClient threadClient = new ThreadClient(saidaFrame);
        threadClient.start();
    }
}
