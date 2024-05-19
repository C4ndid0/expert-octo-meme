package com.br.candido.model;

import java.time.YearMonth;
import java.util.Map;

public class FuncionarioFactory {
    public static Funcionario criarFuncionario(FuncionarioTipo tipo, String nome, YearMonth contratacao, Map<YearMonth, Double> vendas) {
        switch (tipo) {
            case SECRETARIO:
                return new Secretario(nome, contratacao);
            case VENDEDOR:
                return new Vendedor(nome, contratacao, vendas);
            case GERENTE:
                return new Gerente(nome, contratacao);
            default:
                throw new IllegalArgumentException("Tipo de funcionário desconhecido: " + tipo);
        }
    }
}
