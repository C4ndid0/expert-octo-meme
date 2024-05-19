package com.br.candido.service;

import com.br.candido.beneficios.TaxaBeneficio;

public class BeneficioService {
    public double calcularBeneficio(double salario, TaxaBeneficio taxaBeneficio){
        return salario * taxaBeneficio.getTaxa();
    }

}
