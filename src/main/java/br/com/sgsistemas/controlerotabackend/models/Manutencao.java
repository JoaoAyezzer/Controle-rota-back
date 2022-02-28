package br.com.sgsistemas.controlerotabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "MANUTENCOES")
public class Manutencao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String descricao;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    @Column
    private Long kilometragem;
    @ManyToOne
    @JoinColumn(name = "SOLICITANTE_ID")
    private Tecnico solicitante;
    @ManyToOne
    @JoinColumn(name = "VEICULO_ID")
    private Veiculo veiculo;
    @Column(nullable = false)
    private Double valor;

    public Manutencao() {
    }

    public Manutencao(String descricao, Date data, Tecnico solicitante, Long kilometragem, Veiculo veiculo, Double valor) {
        this.descricao = descricao;
        this.data = data;
        this.solicitante = solicitante;
        this.veiculo = veiculo;
        this.kilometragem = kilometragem;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Long kilometragem) {
        this.kilometragem = kilometragem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Tecnico getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Tecnico solicitante) {
        this.solicitante = solicitante;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manutencao)) return false;
        Manutencao that = (Manutencao) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", solicitante=" + solicitante +
                ", veiculo=" + veiculo +
                ", valor=" + valor +
                '}';
    }
}
