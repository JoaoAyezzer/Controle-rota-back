package br.com.sgsistemas.controlerotabackend.models.enums;

public enum TipoDespesa {
    CAFE_DA_MANHA(1, "Despesa relacionada a cafe da manhã"),
    ALMOCO(2, "Despesa relacionada a almoço"),
    CAFE_DA_TARDE(3, "Despesa relacionada a Cafe da tarde"),
    JANTAR(4, "Despesa relacionada a Jantar"),
    HOSPEDAGEM(5, "Despesa relacionada a Hospedagem");

    private int cod;
    private String descricao;

    TipoDespesa(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoDespesa toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (TipoDespesa x : TipoDespesa.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Invalido para o codigo: " + cod);
    }
}

