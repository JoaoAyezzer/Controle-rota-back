package br.com.sgsistemas.controlerotabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity(name = "VEICULOS")
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String placa;
    @Column
    private String modelo;
    @Column(nullable = false)
    private Long kilometragem;
    @JsonIgnore
    @OneToMany(mappedBy = "veiculo")
    private List<Visita> visitas = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "veiculo")
    private List<Limpeza> limpezas = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "veiculo")
    private List<Manutencao> manutencoes = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "veiculo")
    private List<Abastecimento> abastecimentos = new ArrayList<>();

    public Veiculo() {
    }

    public Veiculo(String placa, String modelo, Long kilometragem) {
        this.placa = placa;
        this.modelo = modelo;
        this.kilometragem = kilometragem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Long kilometragem) {
        this.kilometragem = kilometragem;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    public List<Limpeza> getLimpezas() {
        return limpezas;
    }

    public void setLimpezas(List<Limpeza> limpezas) {
        this.limpezas = limpezas;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }

    public List<Abastecimento> getAbastecimentos() {
        return abastecimentos;
    }

    public void setAbastecimentos(List<Abastecimento> abastecimentos) {
        this.abastecimentos = abastecimentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return getId().equals(veiculo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", kilometragem=" + kilometragem +
                ", visitas=" + visitas +
                ", limpezas=" + limpezas +
                ", manutencoes=" + manutencoes +
                ", abastecimentos=" + abastecimentos +
                '}';
    }
}
