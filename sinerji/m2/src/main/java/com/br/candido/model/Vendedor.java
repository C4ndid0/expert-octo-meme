package com.br.candido.model;

import java.time.YearMonth;
import java.util.Map;

public class Vendedor extends Funcionario{
    private Map<YearMonth,Double> vendas;

    public Vendedor(String nome, YearMonth contratacao, Map<YearMonth, Double> vendas) {
        super(nome, contratacao, FuncionarioTipo.VENDEDOR);
        this.vendas = vendas;
    }

    public Map<YearMonth, Double> getVendas(){
        return vendas;
    }
}
