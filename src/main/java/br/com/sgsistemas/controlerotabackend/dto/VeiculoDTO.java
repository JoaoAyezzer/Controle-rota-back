package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Veiculo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class VeiculoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Valid
    @NotEmpty(message = "preenchimento obrigatório")
    @Size(min = 7, max = 8)
    private String placa;
    @Valid
    @NotEmpty(message = "preenchimento obrigatório")
    private String modelo;
    @Valid
    @NotEmpty(message = "preenchimento obrigatório")
    private Long kilometragem;

    public VeiculoDTO() {
    }
    public VeiculoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.placa = veiculo.getPlaca();
        this.modelo = veiculo.getModelo();
        this.kilometragem = veiculo.getKilometragem();
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
}
