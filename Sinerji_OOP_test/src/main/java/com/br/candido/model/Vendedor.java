package com.br.candido.model;

import java.time.YearMonth;

public class Vendedor extends Funcionario{
    public Vendedor(String nome, YearMonth dataContratacao) {
        super(nome, dataContratacao);
    }

    @Override
    public double calcularSalario(int ano, int mes) {
        return 0;
    }

    @Override
    public double calcularBeneficio(int ano, int mes) {
        return 0;
    }


}
