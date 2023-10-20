
package com.ifcoder.projetodacc_lps.model;

import java.util.Scanner;

public class Disciplina {
    private String nome;
    private int semestre;
    private String horario;
    private Professor ministrante;
    private Aluno[] lstAlunos;

    public Disciplina(){
        this.nome = "";
        this.semestre = -1;
        this.horario = "0h";
        this.ministrante = new Professor();
        this.lstAlunos = new Aluno[4];
        
        for(int i=0; i<=3; i++){
            lstAlunos[i] = new Aluno();
        }
    }
    
    @Override
    public String toString(){
        String txt = "---- Dados da Disciplina ----\n"
        +"Nome: " + this.nome + "\n"
        +"Semestre: "+ this.semestre + "\n"
        +"Horario: " + this.horario;
        
        txt += ministrante.toString();
        for(int i=0; i<=3; i++){
            txt += lstAlunos[i].toString();
        }                
        return txt;
    }
    
    
    public void copiar(Disciplina outra){
        this.nome = outra.getNome();
        this.semestre = outra.getSemestre();
        this.horario = outra.getHorario();
        
        this.ministrante.copiar(outra.getMinistrante());
        
        for(int i=0; i<=3; i++){
            this.lstAlunos[i].copiar(outra.getLstAlunos()[i]);
        }
        
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Professor getMinistrante() {
        return ministrante;
    }

    public void setMinistrante(Professor ministrante) {
        this.ministrante = ministrante;
    }

    public Aluno[] getLstAlunos() {
        return lstAlunos;
    }

    public void setLstAlunos(Aluno[] lstAlunos) {
        this.lstAlunos = lstAlunos;
    }        
    
}
