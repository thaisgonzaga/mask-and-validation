package com.ifcoder.projetodacc_jpa.model;

public class Aluno extends Pessoa {

    private int id;
    private String matricula;
    private int anoIngresso;

    public Aluno() {
        super();
        this.matricula = "00000";
        this.anoIngresso = 1900;
    }

    public Aluno(int id, String nome, char sexo, int idade, String matricula, int anoIngresso) {
        super(nome, sexo, idade);
        this.id = id;
        this.matricula = matricula;
        this.anoIngresso = anoIngresso;
    }

    public void copiar(Aluno outro) {
        this.id = outro.getId();
        this.nome = outro.getNome();
        this.sexo = outro.getSexo();
        this.idade = outro.getIdade();
        this.matricula = outro.getMatricula();
        this.anoIngresso = outro.getAnoIngresso();
    }

    @Override
    public String toString() {
        String txt = "---- Dados do aluno ------\n"
                + "id: " + this.id + "\n"
                + super.toString()
                + " Matricula: " + this.matricula + "\n"
                + " Ano de ingresso: " + this.anoIngresso + "\n"
                + "-------------------------------------\n";

        return txt;
    }
      
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

}
