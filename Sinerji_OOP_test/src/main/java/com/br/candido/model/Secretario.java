package com.br.candido.model;

import com.br.candido.beneficios.TaxaBeneficio;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class Secretario extends  Funcionario{

    public Secretario(String nome, YearMonth dataContratacao) {
        super(nome, dataContratacao);
    }

    @Override
    public double calcularSalario(int ano, int mes) {
        YearMonth dataAtual = YearMonth.of(ano,mes);
        long anosDeServico = ChronoUnit.YEARS.between(getDataContratacao(),dataAtual);
        long l = (7000 + 1000 * anosDeServico).TaxaBeneficio.SECRETARIO;
        return l;
    }

    @Override
    public double calcularBeneficio(int ano, int mes) {
        return calcularBeneficio(ano, mes) * 0.20;
    }
}

