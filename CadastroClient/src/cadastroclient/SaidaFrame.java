/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroclient;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author rafae
 */
public class SaidaFrame extends JDialog {
    public JTextArea texto;
    
    public SaidaFrame(JFrame parent) {
        super(parent, "Saída de Mensagens", false); 
        
        setBounds(100, 100, 400, 300); 

        texto = new JTextArea();
        texto.setEditable(false); 
        texto.setFont(new Font("Arial", Font.PLAIN, 14)); 
        texto.setLineWrap(true); 
        texto.setWrapStyleWord(true); 

        JScrollPane scrollPane = new JScrollPane(texto);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void adicionarMensagem(String mensagem) {
        texto.append(mensagem + "\n");
        texto.setCaretPosition(texto.getDocument().getLength() - 1); 
        texto.update(texto.getGraphics()); 
    }
    
    // Método para exibir a janela
    public void exibir() {
        setVisible(true);
    }
}
