package br.com.sgsistemas.controlerotabackend.models.enums;

public enum TipoVisita {
    IMPLANTACAO(1, "Visita relacionada a implantação"),
    ROTINA(2, "Visita de rotina"),
    SOLICITACAO_CLIENTE(3, "Visita solicitada pelo cliente");

    private int cod;
    private String descricao;

    TipoVisita(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoVisita toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (TipoVisita x : TipoVisita.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Invalido para o codigo: " + cod);
    }
}
