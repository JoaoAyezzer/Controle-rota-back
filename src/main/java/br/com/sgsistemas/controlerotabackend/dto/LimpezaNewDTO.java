package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Limpeza;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class LimpezaNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Preenchimento Obrigatorio!")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    @NotNull(message = "Preenchimento Obrigatorio!")
    private Long solicitante_id;
    @NotNull(message = "Preenchimento Obrigatorio!")
    private Long veiculo_id;

    public LimpezaNewDTO() {
    }

    public LimpezaNewDTO(Limpeza limpeza) {
        this.data = limpeza.getData();
        this.solicitante_id = limpeza.getSolicitante().getId();
        this.veiculo_id = limpeza.getVeiculo().getId();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getSolicitante_id() {
        return solicitante_id;
    }

    public void setSolicitante_id(Long solicitante_id) {
        this.solicitante_id = solicitante_id;
    }

    public Long getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Long veiculo_id) {
        this.veiculo_id = veiculo_id;
    }
}
