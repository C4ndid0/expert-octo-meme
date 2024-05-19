package com.br.candido.service;

import com.br.candido.beneficios.TaxaBeneficio;
import com.br.candido.model.Funcionario;
import com.br.candido.model.FuncionarioFactory;
import com.br.candido.model.Secretario;
import com.br.candido.model.Vendedor;

import java.util.Comparator;
import java.util.List;

public class CalculadoraService {
    private final BeneficioService beneficioService;
    private final FuncionarioFactory funcionarioFactory;

    public CalculadoraService(FuncionarioFactory funcionarioFactory) {
        this.beneficioService =  new BeneficioService();
        this.funcionarioFactory = funcionarioFactory;
    }


    public double calcularSalarioComBeneficio(List<Funcionario> funcionarios, int ano, int mes){
        return funcionarios.stream()
                .mapToDouble( funcionario -> {
                    double salario = funcionario.calcularSalario(ano,mes);
                    if (funcionario instanceof Vendedor || funcionario instanceof Secretario){
                        TaxaBeneficio taxaBeneficio = (funcionario instanceof Vendedor ) ? TaxaBeneficio.VENDEDOR : TaxaBeneficio.SECRETARIO;
                        double beneficio = beneficioService.calcularBeneficio(salario, taxaBeneficio);
                        return  salario + beneficio;
                    }
                    return  salario;
                }).sum();
    }

    public double calcularSalarioSemBeneficio(List<Funcionario> funcionarios,int ano, int mes){
        return funcionarios.stream()
                .mapToDouble(funcionario -> funcionario.calcularSalario(ano,mes))
                .sum();
    }

    public double calcularTotalBeneficios(List<Funcionario> funcionarios, int ano, int mes) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario instanceof Vendedor || funcionario instanceof Secretario)
                .mapToDouble(funcionario -> {
                    double salario = funcionario.calcularSalario(ano, mes);
                    TaxaBeneficio taxaBeneficio = (funcionario instanceof Vendedor) ? TaxaBeneficio.VENDEDOR : TaxaBeneficio.SECRETARIO;
                    return beneficioService.calcularBeneficio(salario, taxaBeneficio);
                })
                .sum();
    }

    public Funcionario encontrarMaiorRecebedor(List<Funcionario> funcionarios, int ano, int mes) {
        return funcionarios.stream()
                .max(Comparator.comparingDouble(funcionario -> funcionario.calcularSalario(ano, mes)))
                .orElse(null);
    }

    public String encontrarMaiorRecebedorBeneficios(List<Funcionario> funcionarios, int ano, int mes) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario instanceof Vendedor || funcionario instanceof Secretario)
                .max(Comparator.comparingDouble(funcionario -> {
                    double salario = funcionario.calcularSalario(ano, mes);
                    TaxaBeneficio taxaBeneficio = (funcionario instanceof Vendedor) ? TaxaBeneficio.VENDEDOR : TaxaBeneficio.SECRETARIO;
                    return beneficioService.calcularBeneficio(salario, taxaBeneficio);
                }))
                .map(Funcionario::getNome)
                .orElse(null);
    }

    public Funcionario encotrarMelhorVendedor(List<Funcionario> funcionarios,int ano,int mes){
        return funcionarios.stream()
                .max(Comparator.comparingDouble(vendedor -> {
                    return vendedor.calcularSalario(ano, mes);
                })).orElse(null);
    }




}
