package com.br.candido.model;

import java.time.YearMonth;

public abstract class Funcionario {
    private String nome;
    private YearMonth dataContratacao;

    public Funcionario(String nome, YearMonth dataContratacao) {
        this.nome = nome;
        this.dataContratacao = dataContratacao;
    }

    public String getNome() {
        return nome;
    }

    public YearMonth getDataContratacao() {
        return dataContratacao;
    }

    public abstract double calcularSalario(int ano,int mes);
    public abstract double calcularBeneficio(int ano, int mes);
}
