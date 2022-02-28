package br.com.sgsistemas.controlerotabackend.models.enums;

public enum TipoTecnico {
    TECNICO_ROTA(1, "Tecnico de rota"),
    TECNICO_IMPLANTACAO(2, "Tecnico de implantacão"),
    TECNICO_SUPORTE(3, "Tecnico de suporte imterno"),
    SUPERVISOR(4, "Supervisor"),
    GERENTE(5, "Gerente");

    private int cod;
    private String descricao;

    TipoTecnico(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoTecnico toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (TipoTecnico x : TipoTecnico.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Invalido para o codigo: " + cod);
    }
}
