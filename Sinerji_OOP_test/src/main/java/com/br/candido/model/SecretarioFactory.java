package com.br.candido.model;

import java.time.YearMonth;

public class SecretarioFactory implements FuncionarioFactory{
    @Override
    public Funcionario criarFuncionario(String nome, YearMonth dataDeContratacao) {
        return new Secretario(nome,dataDeContratacao);
    }
}
