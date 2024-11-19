/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rafae
 */
@Entity
@Table(name = "Produtos")
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p"),
    @NamedQuery(name = "Produtos.findByProdutoID", query = "SELECT p FROM Produtos p WHERE p.produtoID = :produtoID"),
    @NamedQuery(name = "Produtos.findByNome", query = "SELECT p FROM Produtos p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produtos.findByQuantidade", query = "SELECT p FROM Produtos p WHERE p.quantidade = :quantidade"),
    @NamedQuery(name = "Produtos.findByPrecoVenda", query = "SELECT p FROM Produtos p WHERE p.precoVenda = :precoVenda")})
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProdutoID")
    private Integer produtoID;
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "Quantidade")
    private int quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PrecoVenda")
    private BigDecimal precoVenda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoID")
    private Collection<Movimentos> movimentosCollection;

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

    public Collection<Movimentos> getMovimentosCollection() {
        return movimentosCollection;
    }

    public void setMovimentosCollection(Collection<Movimentos> movimentosCollection) {
        this.movimentosCollection = movimentosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produtoID != null ? produtoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.produtoID == null && other.produtoID != null) || (this.produtoID != null && !this.produtoID.equals(other.produtoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Produtos[ produtoID=" + produtoID + " ]";
    }
    
}
