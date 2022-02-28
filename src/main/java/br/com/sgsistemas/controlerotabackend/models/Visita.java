package br.com.sgsistemas.controlerotabackend.models;

import br.com.sgsistemas.controlerotabackend.dto.FinalizarVisitaDTO;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoVisita;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "VISITAS")
public class Visita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer tiṕoVisita;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "TECNICO_ID")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name = "VEICULO_ID")
    private Veiculo veiculo;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataIncial;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataFinal;
    @Column
    private Long kilometragemInicial;
    @Column
    private Long kilometragemFinal;

    public Visita() {
    }

    public Visita(TipoVisita tiṕoVisita, Cliente cliente, Veiculo veiculo, Tecnico tecnico, Date dataIncial, Date dataFinal, Long kilometragemInicial, Long kilometragemFinal) {
        this.tiṕoVisita = (tiṕoVisita == null) ? null : tiṕoVisita.getCod();
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataIncial = dataIncial;
        this.dataFinal = ( dataFinal == null ) ? null : dataFinal;
        this.kilometragemInicial = kilometragemInicial;
        this.kilometragemFinal = ( kilometragemFinal == null ) ? null : kilometragemFinal;
        this.tecnico = tecnico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public TipoVisita getTiṕoVisita() {
        return TipoVisita.toEnum(this.tiṕoVisita);
    }

    public void setTiṕoVisita(TipoVisita tiṕoVisita) {
        this.tiṕoVisita = tiṕoVisita.getCod();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnicos) {
        this.tecnico = tecnicos;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataIncial() {
        return dataIncial;
    }

    public void setDataIncial(Date dataIncial) {
        this.dataIncial = dataIncial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long getKilometragemInicial() {
        return kilometragemInicial;
    }

    public void setKilometragemInicial(Long kilometragemInicial) {
        this.kilometragemInicial = kilometragemInicial;
    }

    public Long getKilometragemFinal() {
        return kilometragemFinal;
    }

    public void setKilometragemFinal(Long kilometragemFinal) {
        this.kilometragemFinal = kilometragemFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visita)) return false;
        Visita visita = (Visita) o;
        return getId().equals(visita.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Visita{" +
                "id=" + id +
                ", tiṕoVisita=" + tiṕoVisita +
                ", cliente=" + cliente +
                ", tecnico=" + tecnico +
                ", veiculo=" + veiculo +
                ", dataIncial=" + dataIncial +
                ", dataFinal=" + dataFinal +
                ", kilometragemInicial=" + kilometragemInicial +
                ", kilometragemFinal=" + kilometragemFinal +
                '}';
    }
}
