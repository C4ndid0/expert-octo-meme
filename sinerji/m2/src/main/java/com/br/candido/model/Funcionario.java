package com.br.candido.model;

import java.time.YearMonth;

public abstract class Funcionario {
    private String nome;
    private YearMonth contratacao;
    private FuncionarioTipo tipo;

    public Funcionario(String nome, YearMonth contratacao, FuncionarioTipo tipo) {
        this.nome = nome;
        this.contratacao = contratacao;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public YearMonth getContratacao() {
        return contratacao;
    }

    public FuncionarioTipo getTipo() {
        return tipo;
    }

    public double getSalarioBase() {
        return tipo.getSalarioBase();
    }

    public double calcularSalario(YearMonth mesAno) {
        return getSalarioBase() + calcularBeneficio(mesAno);
    }

    public double calcularBeneficio(YearMonth mesAno) {
        return tipo.getBeneficioStrategy().calcularBeneficio(this, mesAno);
    }
}
