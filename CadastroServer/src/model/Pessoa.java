/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author rafae
 */
@Entity
@Table(name = "Pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByPessoaID", query = "SELECT p FROM Pessoa p WHERE p.pessoaID = :pessoaID"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByLogradouro", query = "SELECT p FROM Pessoa p WHERE p.logradouro = :logradouro"),
    @NamedQuery(name = "Pessoa.findByCidade", query = "SELECT p FROM Pessoa p WHERE p.cidade = :cidade"),
    @NamedQuery(name = "Pessoa.findByEstado", query = "SELECT p FROM Pessoa p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pessoa.findByTelefone", query = "SELECT p FROM Pessoa p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PessoaID")
    private Integer pessoaID;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "Telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private PessoasJuridicas pessoasJuridicas;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private PessoasFisicas pessoasFisicas;

    public Pessoa() {
    }

    public Pessoa(Integer pessoaID) {
        this.pessoaID = pessoaID;
    }

    public Integer getPessoaID() {
        return pessoaID;
    }

    public void setPessoaID(Integer pessoaID) {
        this.pessoaID = pessoaID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PessoasJuridicas getPessoasJuridicas() {
        return pessoasJuridicas;
    }

    public void setPessoasJuridicas(PessoasJuridicas pessoasJuridicas) {
        this.pessoasJuridicas = pessoasJuridicas;
    }

    public PessoasFisicas getPessoasFisicas() {
        return pessoasFisicas;
    }

    public void setPessoasFisicas(PessoasFisicas pessoasFisicas) {
        this.pessoasFisicas = pessoasFisicas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaID != null ? pessoaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.pessoaID == null && other.pessoaID != null) || (this.pessoaID != null && !this.pessoaID.equals(other.pessoaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pessoa[ pessoaID=" + pessoaID + " ]";
    }
    
}
