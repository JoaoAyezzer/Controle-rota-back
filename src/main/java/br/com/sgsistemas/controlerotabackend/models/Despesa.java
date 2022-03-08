package br.com.sgsistemas.controlerotabackend.models;

import br.com.sgsistemas.controlerotabackend.models.enums.TipoDespesa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity(name = "DESPESA")
public class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer tipoDespesa;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    @Column(name = "URL_COMPRVANTE")
    private String imageUrl;
    @Column(nullable = false)
    private Double valor;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "TECNICO_ID")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name = "VISITA_ID")
    private Visita visita;

    public Despesa() {
    }

    public Despesa(TipoDespesa tipoDespesa, Date data, Double valor, Tecnico tecnico, Visita visita) {
        this.tipoDespesa = tipoDespesa.getCod();
        this.data = data;
        this.valor = valor;
        this.tecnico = tecnico;
        this.visita = visita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDespesa getTipoDespesa() {
        return TipoDespesa.toEnum(this.tipoDespesa);
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa.getCod();
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

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Despesa)) return false;
        Despesa despesa = (Despesa) o;
        return getId().equals(despesa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "id=" + id +
                ", tipoDespesa=" + tipoDespesa +
                ", data=" + data +
                ", valor=" + valor +
                ", tecnico=" + tecnico +
                ", visita=" + visita +
                '}';
    }
}
