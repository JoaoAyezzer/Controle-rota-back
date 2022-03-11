package br.com.sgsistemas.controlerotabackend.models;


import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity(name = "TECNICOS")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Tecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    @JsonIgnore
    private String senha;
    @Column
    private String imageUrl;
    @Column
    private Integer tipoTecnico;
    @ElementCollection
    @CollectionTable(name = "TELEFONES_TECNICOS")
    private List<String> telefones = new ArrayList<>(2);
    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Visita> visitas = new ArrayList<>();
    @JsonBackReference
    @OneToMany(mappedBy = "tecnico")
    private List<Despesa> despesa = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "tecnicoResponsavel")
    private List<Cliente> clientes = new ArrayList<>();

    public Tecnico() {
//        addPerfil(PerfilUsuario.TECNICO);
    }

    public Tecnico(String nome, String email, String senha, TipoTecnico tipoTecnico) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoTecnico = (tipoTecnico == null) ? null : tipoTecnico.getCod();
//        addPerfil(PerfilUsuario.TECNICO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoTecnico getTipoTecnico() { return TipoTecnico.toEnum(this.tipoTecnico); }

    public void setTipoTecnico(TipoTecnico tipoTecnico) { this.tipoTecnico = tipoTecnico.getCod(); }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    public List<Despesa> getDespesas() {
        return despesa;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesa = despesas;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tecnico)) return false;
        Tecnico tecnico = (Tecnico) o;
        return getId().equals(tecnico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tipoTecnico=" + tipoTecnico +
                ", telefones=" + telefones +
                ", visitas=" + visitas +
                ", despesas=" + despesa +
                '}';
    }
}
