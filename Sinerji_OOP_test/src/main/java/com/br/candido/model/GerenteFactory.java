package com.br.candido.model;

import java.time.YearMonth;

public class GerenteFactory implements FuncionarioFactory{
    @Override
    public Funcionario criarFuncionario(String nome, YearMonth dataDeContratacao) {
        return new Gerente(nome,dataDeContratacao);
    }
}
