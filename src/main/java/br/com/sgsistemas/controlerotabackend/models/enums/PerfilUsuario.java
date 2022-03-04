package br.com.sgsistemas.controlerotabackend.models.enums;

public enum PerfilUsuario {
    ADMIN(1, "ROLE_ADMIN"),
    TECNICO(2, "ROLE_TECNICO");

    private int cod;
    private String descricao;

    PerfilUsuario(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PerfilUsuario toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (PerfilUsuario x : PerfilUsuario.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Invalido para o codigo: " + cod);
    }
}

