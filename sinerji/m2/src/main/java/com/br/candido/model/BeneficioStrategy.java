package com.br.candido.model;

import java.time.YearMonth;

public interface BeneficioStrategy {
    double calcularBeneficio(Funcionario funcionario, YearMonth mesAno);
}
