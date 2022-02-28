package br.com.sgsistemas.controlerotabackend.dto;

import java.io.Serializable;

public class FinalizarVisitaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long kilometragemFinal;

    public FinalizarVisitaDTO() {
    }

    public FinalizarVisitaDTO(Long kilometragemFinal) {
        this.kilometragemFinal = kilometragemFinal;
    }

    public Long getKilometragemFinal() {
        return kilometragemFinal;
    }

    public void setKilometragemFinal(Long kilometragemFinal) {
        this.kilometragemFinal = kilometragemFinal;
    }
}
