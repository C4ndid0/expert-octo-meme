package com.br.candido.model;

import java.time.YearMonth;

public class VendedorBeneficioStrategy implements BeneficioStrategy{
    @Override
    public double calcularBeneficio(Funcionario funcionario, YearMonth mesAno) {
        Vendedor vendedor = (Vendedor) funcionario;
        int anosServico = mesAno.getYear() - vendedor.getContratacao().getYear();
        if (mesAno.getMonthValue() < vendedor.getContratacao().getMonthValue()) {
            anosServico--;
        }

        double beneficioAnual = anosServico * 1800.00;
        double vendaMes = vendedor.getVendas().getOrDefault(mesAno, 0.0);
        double comissao = vendaMes * 0.30;
        return beneficioAnual + comissao;
    }
}
