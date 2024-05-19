package com.br.candido.beneficios;

public enum TaxaBeneficio {
    SECRETARIO(0.20),
    VENDEDOR(0.30),
    GERENTE(0.0);

    private final double taxa;

    TaxaBeneficio(double taxa) {

        this.taxa = taxa;
    }

    public double getTaxa() {
        return taxa;
    }
}
