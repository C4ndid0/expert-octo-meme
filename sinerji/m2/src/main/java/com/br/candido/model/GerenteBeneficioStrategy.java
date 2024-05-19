package com.br.candido.model;

import java.time.YearMonth;

public class GerenteBeneficioStrategy implements BeneficioStrategy{
    @Override
    public double calcularBeneficio(Funcionario funcionario, YearMonth mesAno) {
        int anosServico = mesAno.getYear() - funcionario.getContratacao().getYear();
        if (mesAno.getMonthValue() < funcionario.getContratacao().getMonthValue()) {
            anosServico--;
        }

        return anosServico * 3000.00;
    }
}
