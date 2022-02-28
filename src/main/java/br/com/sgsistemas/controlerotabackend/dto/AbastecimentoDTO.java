package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Abastecimento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AbastecimentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    private Long kilometragem;
    private Float litros;
    private Double valor;
    private Long veiculo_id;
    private String veiculo_modelo;
    private String veiculo_placa;
    private Long veiculo_km;
    private Long tecnico_id;
    private String tecnico_nome;
    private String tecnico_tipo;


    public AbastecimentoDTO(Abastecimento abastecimento) {
        this.id = abastecimento.getId();
        this.data = abastecimento.getData();
        this.kilometragem = abastecimento.getKilometragem();
        this.litros = abastecimento.getLitrosCombustivel();
        this.valor = abastecimento.getValor();
        this.veiculo_id = abastecimento.getVeiculo().getId();
        this.veiculo_modelo = abastecimento.getVeiculo().getModelo();
        this.veiculo_placa = abastecimento.getVeiculo().getPlaca();
        this.veiculo_km = abastecimento.getVeiculo().getKilometragem();
        this.tecnico_id = abastecimento.getTecnico().getId();
        this.tecnico_nome = abastecimento.getTecnico().getNome();
        this.tecnico_tipo = abastecimento.getTecnico().getTipoTecnico().getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVeiculo_id() {
        return veiculo_id;
    }

    public String getVeiculo_modelo() {
        return veiculo_modelo;
    }

    public void setVeiculo_modelo(String veiculo_modelo) {
        this.veiculo_modelo = veiculo_modelo;
    }

    public String getVeiculo_placa() {
        return veiculo_placa;
    }

    public void setVeiculo_placa(String veiculo_placa) {
        this.veiculo_placa = veiculo_placa;
    }

    public Long getVeiculo_km() {
        return veiculo_km;
    }

    public void setVeiculo_km(Long veiculo_km) {
        this.veiculo_km = veiculo_km;
    }

    public Long getTecnico_id() {
        return tecnico_id;
    }

    public void setTecnico_id(Long tecnico_id) {
        this.tecnico_id = tecnico_id;
    }

    public String getTecnico_nome() {
        return tecnico_nome;
    }

    public void setTecnico_nome(String tecnico_nome) {
        this.tecnico_nome = tecnico_nome;
    }

    public String getTecnico_tipo() {
        return tecnico_tipo;
    }

    public void setTecnico_tipo(String tecnico_tipo) {
        this.tecnico_tipo = tecnico_tipo;
    }

    public void setVeiculo_id(Long veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Float getLitros() {
        return litros;
    }

    public void setLitros(Float litros) {
        this.litros = litros;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Long kilometragem) {
        this.kilometragem = kilometragem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
