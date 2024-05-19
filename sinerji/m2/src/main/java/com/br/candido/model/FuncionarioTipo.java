package com.br.candido.model;

public enum FuncionarioTipo {
    SECRETARIO {
        @Override
        public BeneficioStrategy getBeneficioStrategy() {
            return new SecreatarioBeneficioStrategy();
        }

        @Override
        public double getSalarioBase() {
            return 7000.00;
        }
    },
    VENDEDOR {
        @Override
        public BeneficioStrategy getBeneficioStrategy() {
            return new VendedorBeneficioStrategy();
        }

        @Override
        public double getSalarioBase() {
            return 12000.00;
        }
    },
    GERENTE {
        @Override
        public BeneficioStrategy getBeneficioStrategy() {
            return new GerenteBeneficioStrategy();
        }

        @Override
        public double getSalarioBase() {
            return 20000.00;
        }
    };

    public abstract BeneficioStrategy getBeneficioStrategy();
    public abstract double getSalarioBase();
}
