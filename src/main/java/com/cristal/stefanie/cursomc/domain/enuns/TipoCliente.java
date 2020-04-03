package com.cristal.stefanie.cursomc.domain.enuns;

public enum TipoCliente {

    PESSOAFISICA(0, "Pessoa fisica"),
    PESSOAJURIDICA(1, "Pessoa Juridica");

    private Integer cod;
    private String descricao;

    TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod){
        if (cod==null){
            return null;
        }
        for (TipoCliente x : TipoCliente.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod );
    }
}
