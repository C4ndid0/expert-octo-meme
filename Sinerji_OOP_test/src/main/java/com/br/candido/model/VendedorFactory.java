package com.br.candido.model;

import java.time.YearMonth;

public class VendedorFactory implements FuncionarioFactory{


    @Override
    public Funcionario criarFuncionario(String nome, YearMonth dataDeContratacao) {
        return new Vendedor(nome,dataDeContratacao);
    }
}
