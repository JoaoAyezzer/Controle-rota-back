package br.com.sgsistemas.controlerotabackend.models.enums;

public enum TipoTecnico {
    TECNICO_ROTA(1, "ROLE_TECNICO_ROTA"),
    TECNICO_IMPLANTACAO(2, "ROLE_TECNICO_IMPLANTACAO"),
    TECNICO_SUPORTE(3, "ROLE_TECNICO_SUPORTE"),
    SUPERVISOR(4, "ROLE_ADMIN"),
    GERENTE(5, "ROLE_ADMIN");

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
