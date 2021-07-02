package com.example.infanciaemalertaapp;

public class User {

    public String nome;
    public String email;
    public String telefone;
    public String senha;
    public String endereco;
    public String cep;
    public String oab;
    public String rg;
    public String matricula;
    public String cidade;
    public String estado;

    //String nome, String email, String telefone, String senha
    public User(){

    }

    public User(String nome, String email, String telefone, String senha, String endereco,
                String cep, String oab, String rg, String matricula, String cidade, String estado){
        this.nome= nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.endereco = endereco;
        this.cep = cep;
        this.oab = oab;
        this.oab = rg;
        this.matricula = matricula;
        this.cidade = cidade;
        this.estado = estado;

    }
    @Override
    public String toString() {
        return this.nome + "\n" + this.email + "\n" + this.oab +"\n" + this.telefone +"\n" + this.endereco +"\n" + this.cep;}

}
