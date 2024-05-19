package com.br.candido.model;

import java.time.YearMonth;

public class Gerente extends Funcionario{
    public Gerente(String nome, YearMonth contratacao) {
        super(nome, contratacao, FuncionarioTipo.GERENTE);
    }
}
