package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer produtoID;
    private String nome;
    private int quantidade;
    private BigDecimal precoVenda;
    //private Collection<Movimentos> movimentosCollection; // Se necessário, manter o relacionamento com Movimentos

    // Construtores
    public Produtos() {
    }

    public Produtos(Integer produtoID) {
        this.produtoID = produtoID;
    }

    public Produtos(Integer produtoID, String nome, int quantidade, BigDecimal precoVenda) {
        this.produtoID = produtoID;
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    // Getters e Setters
    public Integer getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(Integer produtoID) {
        this.produtoID = produtoID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    //public Collection<Movimentos> getMovimentosCollection() {
        //return movimentosCollection;
    //}

    //public void setMovimentosCollection(Collection<Movimentos> movimentosCollection) {
        //this.movimentosCollection = movimentosCollection;
    //}

    // hashCode e equals
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produtoID != null ? produtoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.produtoID == null && other.produtoID != null) || 
            (this.produtoID != null && !this.produtoID.equals(other.produtoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produtos[ produtoID=" + produtoID + " ]";
    }
}
