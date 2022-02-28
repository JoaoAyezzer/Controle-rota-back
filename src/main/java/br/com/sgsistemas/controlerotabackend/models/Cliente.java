package br.com.sgsistemas.controlerotabackend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "CLIENTES")
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = "cnpj" ) } )
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nomeFantasia;
    @Column
    private String cnpj;
    @Column
    private String senhaDeRoot;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TI_LOCAL")
    private TiCliente tiLocal;
    @ManyToOne
    @JoinColumn(name = "TECNICO_ID")
    private Tecnico tecnicoResponsavel;
    @ElementCollection
    @CollectionTable(name = "TELEFONES_CLIENTES")
    private List<String> telefones = new ArrayList<>();
    @OneToMany(mappedBy = "cliente")
    private List<Visita> visitas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nomeFantasia, String cnpj, String senhaDeRoot, Tecnico tecnicoResponsavel, TiCliente tiLocal) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.tiLocal = (tiLocal == null) ? null : tiLocal;
        this.senhaDeRoot = senhaDeRoot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenhaDeRoot() {
        return senhaDeRoot;
    }

    public void setSenhaDeRoot(String senhaDeRoot) {
        this.senhaDeRoot = senhaDeRoot;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Tecnico getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(Tecnico tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public TiCliente getTiLocal() {
        return tiLocal;
    }

    public void setTiLocal(TiCliente tiLocal) {
        this.tiLocal = tiLocal;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getId().equals(cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", tecnicoResponsavel=" + tecnicoResponsavel +
                ", telefones=" + telefones +
                ", visitas=" + visitas +
                '}';
    }
}
