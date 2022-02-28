package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Manutencao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ManutencaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    private Long manutencao_km;
    private Double valor;
    private Long solicitante_id;
    private String solicitante;
    private String tipoSolicitante;
    private Long veiculo_id;
    private String veiculo_modelo;
    private String veiculo_placa;
    private Long veiculo_km;

    public ManutencaoDTO(Manutencao manutencao) {
        this.id = manutencao.getId();
        this.descricao = manutencao.getDescricao();
        this.data = manutencao.getData();
        this.manutencao_km = manutencao.getKilometragem();
        this.solicitante_id = manutencao.getSolicitante().getId();
        this.solicitante = manutencao.getSolicitante().getNome();
        this.tipoSolicitante = manutencao.getSolicitante().getTipoTecnico().getDescricao();
        this.veiculo_id = manutencao.getVeiculo().getId();
        this.veiculo_modelo = manutencao.getVeiculo().getModelo();
        this.veiculo_placa = manutencao.getVeiculo().getPlaca();
        this.veiculo_km = manutencao.getVeiculo().getKilometragem();
        this.valor = manutencao.getValor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSolicitante_id() {
        return solicitante_id;
    }

    public void setSolicitante_id(Long solicitante_id) {
        this.solicitante_id = solicitante_id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getManutencao_km() {
        return manutencao_km;
    }

    public void setManutencao_km(Long manutencao_km) {
        this.manutencao_km = manutencao_km;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getVeiculo_placa() {
        return veiculo_placa;
    }

    public void setVeiculo_placa(String veiculo_placa) {
        this.veiculo_placa = veiculo_placa;
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

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(String tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public Long getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Long veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public String getVeiculo_modelo() {
        return veiculo_modelo;
    }

    public void setVeiculo_modelo(String veiculo_modelo) {
        this.veiculo_modelo = veiculo_modelo;
    }

    public Long getVeiculo_km() {
        return veiculo_km;
    }

    public void setVeiculo_km(Long veiculo_km) {
        this.veiculo_km = veiculo_km;
    }
}
