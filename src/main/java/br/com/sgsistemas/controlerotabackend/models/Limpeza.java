package br.com.sgsistemas.controlerotabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Entity(name = "LIMPEZAS")
public class Limpeza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "SOLICITANTE_ID")
    private Tecnico solicitante;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    @ManyToOne
    @JoinColumn(name = "VEICULO_ID")
    private Veiculo veiculo;

    public Limpeza() {
    }
    public Limpeza(Tecnico solicitante, Date data, Veiculo veiculo) {
        this.solicitante = solicitante;
        this.data = data;
        this.veiculo = veiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tecnico getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Tecnico solicitante) {
        this.solicitante = solicitante;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Limpeza)) return false;
        Limpeza limpeza = (Limpeza) o;
        return getId().equals(limpeza.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Limpeza{" +
                "id=" + id +
                ", solicitante=" + solicitante +
                ", data=" + data +
                ", veiculo=" + veiculo +
                '}';
    }
}
