package com.ifcoder.projetodacc_jpa.model;

import com.ifcoder.projetodacc_jpa.model.dao.file.Csv;

public class Professor extends Pessoa implements Csv {

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

    public String cabecalho() {
        return "Nome;sexo;idade;cpf\n";
    }

    @Override
    public String atributoToCSV() {
        String aux = this.nome + ";" + this.sexo + ";" + this.idade + ";" + this.cpf + "\n";
        return aux;
    }

    @Override
    public Object CSVToAtributo(String linhaCSV) {
        String vetor[] = linhaCSV.split(";");

        Professor p = new Professor(vetor[0],
                vetor[1].charAt(0),
                Integer.parseInt(vetor[2]),
                vetor[3]);
        return p;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
