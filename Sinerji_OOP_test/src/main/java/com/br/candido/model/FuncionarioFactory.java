package com.br.candido.model;

import java.time.YearMonth;

public interface FuncionarioFactory {
    Funcionario criarFuncionario(String nome, YearMonth dataDeContratacao);
}
