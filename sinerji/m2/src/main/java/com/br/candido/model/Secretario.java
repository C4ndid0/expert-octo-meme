package com.br.candido.model;

import java.time.YearMonth;

public class Secretario extends Funcionario{
    public Secretario(String nome, YearMonth contratacao) {
        super(nome, contratacao, FuncionarioTipo.SECRETARIO);
    }
}
