package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Visita;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoVisita;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VisitaNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Preenchimento obrigatorio")
    private Long veiculoId;
    @NotNull(message = "Preenchimento obrigatorio")
    private Long tecnicoId;
    @NotNull(message = "Preenchimento obrigatorio")
    private Long clienteId;
    @NotNull(message = "Preenchimento obrigatorio")
    private Integer tipoVisita;

    public VisitaNewDTO() {
    }

    public VisitaNewDTO(Visita visita) {
        this.veiculoId = visita.getVeiculo().getId();
        this.clienteId = visita.getCliente().getId();
        this.tipoVisita = visita.getTiṕoVisita().getCod();
        this.tecnicoId = visita.getTecnico().getId();
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public TipoVisita getTipoVisita() {
        return TipoVisita.toEnum(tipoVisita);
    }

    public void setTipoVisita(Integer tipoVisita) {
        this.tipoVisita = tipoVisita;
    }
}
