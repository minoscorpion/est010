/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver;

import controller.MovimentosJpaController;
import controller.PessoaJpaController;
import java.io.*;
import java.net.*;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.ProdutosJpaController;
import controller.UsuariosJpaController;
import java.math.BigDecimal;
import model.Movimentos;
import model.Pessoa;
import model.Usuarios;
import model.Produtos;

/**
 *
 * @author rafae
 */
public class CadastroThread extends Thread {
    private final ProdutosJpaController ctrl;
    private final UsuariosJpaController ctrlUsu;
    private final MovimentosJpaController ctrlMov;
    private final Socket s1;
    private final ProdutosJpaController ctrlProd;
    private final PessoaJpaController ctrlPessoa;
    
    public CadastroThread(ProdutosJpaController ctrl, UsuariosJpaController ctrlUsu, MovimentosJpaController ctrlMov, ProdutosJpaController ctrlProd, PessoaJpaController ctrlPessoa, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
        this.ctrlMov = ctrlMov;
        this.ctrlProd = ctrlProd;
        this.ctrlPessoa = ctrlPessoa;
    }

    @Override
    public void run() {
        try (
            ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s1.getInputStream());
        ) {
            
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            Usuarios usuario = ctrlUsu.findUsuarioByLoginAndSenha(login, senha);
            if (usuario == null) {
                out.writeObject("Acesso negado! Login ou senha inválidos.");
                s1.close();
                return;
            }
            
            out.writeObject("Login bem-sucedido! Bem-vindo, " + usuario.getLogin());
            //out.writeChars("RTeste");
            //out.flush();
            boolean ativo = true;

            while (ativo) {
                // Obter o comando do cliente
                String comando = (String) in.readObject();

                switch (comando) {
                    case "L": // Listar produtos
                        List<Produtos> produtos = ctrl.findProdutoEntities();
                        //System.out.println(produtos.toString());
                        out.writeObject(produtos); 
                        break;
                    case "S":
                    case "E":
                        Integer idPessoa = in.readInt();
                        System.out.println(idPessoa);
                        Integer idProduto = in.readInt();
                        System.out.println(idProduto);
                        Integer quantidade = in.readInt();
                        System.out.println(quantidade);
                        BigDecimal valorUnitario = (BigDecimal) in.readObject();
                        System.out.println(valorUnitario);
                        
                        System.out.println(comando);
                        
                         //out.writeObject("Produto não encontrado");

                        Produtos produto = ctrlProd.findProdutoById(idProduto);
                        if (produto == null) {
                            out.writeObject("Produto não encontrado");
                            continue;
                        }
                        
                        Pessoa pessoa = ctrlPessoa.findPessoaById(idPessoa);
                        if (pessoa == null) {
                            out.writeObject("Pessoa não encontrado");
                            continue;
                        }

                        // Criar o objeto Movimento
                        Movimentos movimento = new Movimentos();
                        movimento.setTipo(comando); 
                        movimento.setUsuarioID(usuario);
                        movimento.setPessoaID(idPessoa);
                        movimento.setProdutoID(produto); 
                        movimento.setQuantidade(quantidade); 
                        movimento.setPrecoUnitario(valorUnitario); 

                        System.out.println("Gravando movimento");
                        ctrlMov.create(movimento);

                        if ("E".equals(comando)) {
                            produto.setQuantidade(produto.getQuantidade() + quantidade);
                        } else if ("S".equals(comando)) {
                            if (produto.getQuantidade() >= quantidade) {
                                produto.setQuantidade(produto.getQuantidade() - quantidade);
                            } else {
                                out.writeObject("Quantidade insuficiente para saída");
                                continue;
                            }
                        }
                        ctrlProd.edit(produto); 
                        out.writeObject("Movimento realizado com sucesso");
                    case "X": // Sair
                        ativo = false;
                        out.writeObject("Conexão encerrada.");
                        break;

                    default:
                        out.writeObject("Comando inválido2.");
                        break;
                }
            }

            s1.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
        }
    }
}
