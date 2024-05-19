package com.br.candido.model;

import java.time.YearMonth;

public class SecreatarioBeneficioStrategy implements BeneficioStrategy {
    @Override
    public double calcularBeneficio(Funcionario funcionario, YearMonth mesAno) {
        int anosServico = mesAno.getYear() - funcionario.getContratacao().getYear();
        if (mesAno.getMonthValue() < funcionario.getContratacao().getMonthValue()) {
            anosServico--;
        }

        double beneficioAnual = anosServico * 1000.00;
        double bonusSalario = funcionario.getSalarioBase() * 0.20;
        return beneficioAnual + bonusSalario;
    }
}
