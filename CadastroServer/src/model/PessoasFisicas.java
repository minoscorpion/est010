/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author rafae
 */
@Entity
@Table(name = "PessoasFisicas")
@NamedQueries({
    @NamedQuery(name = "PessoasFisicas.findAll", query = "SELECT p FROM PessoasFisicas p"),
    @NamedQuery(name = "PessoasFisicas.findByPessoaID", query = "SELECT p FROM PessoasFisicas p WHERE p.pessoaID = :pessoaID"),
    @NamedQuery(name = "PessoasFisicas.findByCpf", query = "SELECT p FROM PessoasFisicas p WHERE p.cpf = :cpf")})
public class PessoasFisicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PessoaID")
    private Integer pessoaID;
    @Basic(optional = false)
    @Column(name = "CPF")
    private String cpf;
    @JoinColumn(name = "PessoaID", referencedColumnName = "PessoaID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public PessoasFisicas() {
    }

    public PessoasFisicas(Integer pessoaID) {
        this.pessoaID = pessoaID;
    }

    public PessoasFisicas(Integer pessoaID, String cpf) {
        this.pessoaID = pessoaID;
        this.cpf = cpf;
    }

    public Integer getPessoaID() {
        return pessoaID;
    }

    public void setPessoaID(Integer pessoaID) {
        this.pessoaID = pessoaID;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        if (!(object instanceof PessoasFisicas)) {
            return false;
        }
        PessoasFisicas other = (PessoasFisicas) object;
        if ((this.pessoaID == null && other.pessoaID != null) || (this.pessoaID != null && !this.pessoaID.equals(other.pessoaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PessoasFisicas[ pessoaID=" + pessoaID + " ]";
    }
    
}
