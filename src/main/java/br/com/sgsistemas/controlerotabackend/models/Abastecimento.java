package br.com.sgsistemas.controlerotabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "ABASTECIMENTOS")
public class Abastecimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "VEICULO_ID", nullable = false)
    private Veiculo veiculo;
    @Column(nullable = false)
    private Float litrosCombustivel;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    @Column(nullable = false)
    private Long kilometragem;
    @ManyToOne
    @JoinColumn(name = "TECNICO_ID")
    private Tecnico tecnico;
    @Column(nullable = false)
    private Double valor;

    public Abastecimento() {
    }

    public Abastecimento(Veiculo veiculo, Float litrosCombustivel, Long kilometragem, Date data, Tecnico tecnico, Double valor) {
        this.veiculo = veiculo;
        this.litrosCombustivel = litrosCombustivel;
        this.kilometragem = kilometragem;
        this.tecnico = tecnico;
        this.valor = valor;
        this.data = data;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Float getLitrosCombustivel() {
        return litrosCombustivel;
    }

    public void setLitrosCombustivel(Float litrosCombustivel) {
        this.litrosCombustivel = litrosCombustivel;
    }

    public Long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Long kilometragem) {
        this.kilometragem = kilometragem;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }



    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
        if (!(o instanceof Abastecimento)) return false;
        Abastecimento that = (Abastecimento) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Abastecimento{" +
                "id=" + id +
                ", veiculo=" + veiculo +
                ", litrosCombustivel=" + litrosCombustivel +
                ", kilometragem=" + kilometragem +
                ", tecnico=" + tecnico +
                ", valor=" + valor +
                '}';
    }
}
