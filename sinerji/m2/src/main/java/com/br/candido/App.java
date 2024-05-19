package com.br.candido;

import com.br.candido.model.Funcionario;
import com.br.candido.model.FuncionarioFactory;
import com.br.candido.model.FuncionarioTipo;
import com.br.candido.model.Vendedor;
import com.br.candido.service.FuncionarioService;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();

        List<Funcionario> funcionarios = Arrays.asList(
                FuncionarioFactory.criarFuncionario(FuncionarioTipo.SECRETARIO, "Jorge Carvalho", YearMonth.of(2018, 1), null),
                FuncionarioFactory.criarFuncionario(FuncionarioTipo.SECRETARIO, "Maria Souza", YearMonth.of(2015, 12), null),
                FuncionarioFactory.criarFuncionario(FuncionarioTipo.VENDEDOR, "Ana Silva", YearMonth.of(2021, 12), criarVendasAna()),
                FuncionarioFactory.criarFuncionario(FuncionarioTipo.VENDEDOR, "João Mendes", YearMonth.of(2021, 12), criarVendasJoao()),
                FuncionarioFactory.criarFuncionario(FuncionarioTipo.GERENTE, "Juliana Alves", YearMonth.of(2017, 7), null),
                FuncionarioFactory.criarFuncionario(FuncionarioTipo.GERENTE, "Bento Albino", YearMonth.of(2014, 3), null)
        );

        YearMonth mesAno = YearMonth.of(2022, 1);

        double totalPago = service.calcularTotalPago(funcionarios, mesAno);
        double totalSalarios = service.calcularTotalSalarios(funcionarios, mesAno);
        double totalBeneficios = service.calcularTotalBeneficios(funcionarios, mesAno);
        Funcionario maiorSalario = service.obterMaiorSalario(funcionarios, mesAno);
        Funcionario maiorBeneficio = service.obterMaiorBeneficio(funcionarios, mesAno);

        List<Funcionario> list = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Vendedor) {
                list.add(funcionario);
            }
        }
        Vendedor maiorVenda = service.obterMaiorVenda((List<Vendedor>) (List<?>) list, mesAno);

        System.out.println("Total Pago: " + totalPago);
        System.out.println("Total Salários: " + totalSalarios);
        System.out.println("Total Benefícios: " + totalBeneficios);
        System.out.println("Maior Salário: " + maiorSalario.getNome());
        System.out.println("Maior Benefício: " + maiorBeneficio.getNome());
        System.out.println("Maior Venda: " + maiorVenda.getNome());
    }

    private static Map<YearMonth, Double> criarVendasAna() {
        Map<YearMonth, Double> vendas = new HashMap<>();
        vendas.put(YearMonth.of(2021, 12), 5200.0);
        vendas.put(YearMonth.of(2022, 1), 4000.0);
        vendas.put(YearMonth.of(2022, 2), 4200.0);
        vendas.put(YearMonth.of(2022, 3), 5850.0);
        vendas.put(YearMonth.of(2022, 4), 7000.0);
        return vendas;
    }

    private static Map<YearMonth, Double> criarVendasJoao() {
        Map<YearMonth, Double> vendas = new HashMap<>();
        vendas.put(YearMonth.of(2021, 12), 3400.0);
        vendas.put(YearMonth.of(2022, 1), 7700.0);
        vendas.put(YearMonth.of(2022, 2), 5000.0);
        vendas.put(YearMonth.of(2022, 3), 5900.0);
        vendas.put(YearMonth.of(2022, 4), 6500.0);
        return vendas;
    }
}
