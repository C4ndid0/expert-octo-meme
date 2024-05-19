package com.br.candido.service;

import com.br.candido.model.Funcionario;
import com.br.candido.model.Vendedor;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;

public class FuncionarioService {
    public double calcularTotalPago(List<Funcionario> funcionarios, YearMonth mesAno) {
        return funcionarios.stream()
                .mapToDouble(funcionario -> funcionario.calcularSalario(mesAno))
                .sum();
    }

    public double calcularTotalSalarios(List<Funcionario> funcionarios, YearMonth mesAno) {
        return funcionarios.stream()
                .mapToDouble(Funcionario::getSalarioBase)
                .sum();
    }

    public double calcularTotalBeneficios(List<Funcionario> funcionarios, YearMonth mesAno) {
        return funcionarios.stream()
                .mapToDouble(funcionario -> funcionario.calcularBeneficio(mesAno))
                .sum();
    }

    public Funcionario obterMaiorSalario(List<Funcionario> funcionarios, YearMonth mesAno) {
        return funcionarios.stream()
                .max(Comparator.comparingDouble(funcionario -> funcionario.calcularSalario(mesAno)))
                .orElseThrow(() -> new IllegalArgumentException("A lista de funcionários está vazia"));
    }

    public Funcionario obterMaiorBeneficio(List<Funcionario> funcionarios, YearMonth mesAno) {
        return funcionarios.stream()
                .max(Comparator.comparingDouble(funcionario -> funcionario.calcularBeneficio(mesAno)))
                .orElseThrow(() -> new IllegalArgumentException("A lista de funcionários está vazia"));
    }

    public Vendedor obterMaiorVenda(List<Vendedor> vendedores, YearMonth mesAno) {
        return vendedores.stream()
                .max(Comparator.comparingDouble(vendedor -> vendedor.getVendas().getOrDefault(mesAno, 0.0)))
                .orElseThrow(() -> new IllegalArgumentException("A lista de vendedores está vazia"));
    }
}
