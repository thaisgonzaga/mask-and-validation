package com.ifcoder.projetodacc_lps.model;

public class Professor extends Pessoa {

    private String cpf;

    public Professor() {
        super();
        this.cpf = "000.000.000-00";
    }

    public Professor(String nome, char sexo, int idade, String cpf) {
        super(nome, sexo, idade);
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String txt
                = "---------- Professor -----------\n";
        txt += super.toString();
        txt += "CPF: " + this.cpf + "\n"
                + "-------------------------------------\n";
        return txt;
    }

    @Override
    public boolean equals(Object obj) {
        Professor outro = (Professor) obj;
        if (!super.equals(obj)) {
            return false;
        } else if (this.cpf.equals(outro.getCpf())) {
            return false;
        }

        return true;
    }

    public void copiar(Professor outro) {
        this.nome = outro.getNome();
        this.sexo = outro.getSexo();
        this.idade = outro.getIdade();
        this.cpf = outro.getCpf();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
