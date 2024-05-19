package com.br.candido;

import com.br.candido.model.*;
import com.br.candido.service.CalculadoraService;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class CalculadoraServiceTest {
        @Test
        public void testCalcularTotalSalarios() {
            FuncionarioFactory secretarioFactory = new SecretarioFactory();

            List<Funcionario> funcionarios = List.of(
                    secretarioFactory.criarFuncionario("Jorge Carvalho", YearMonth.of(2021, 12)),
                    secretarioFactory.criarFuncionario("Maria Souza", YearMonth.of(2020, 7))
            );

            CalculadoraService calculadoraService = new CalculadoraService(secretarioFactory);
            double totalSalarios = calculadoraService.calcularSalarioSemBeneficio(funcionarios, 2022, 5);

            assertEquals(17800, totalSalarios);
        }

        @Test
        public void testCalcularTotalBeneficios() {
            FuncionarioFactory vendedorFactory = new VendedorFactory();

            List<Funcionario> funcionarios = List.of(
                    vendedorFactory.criarFuncionario("Ana Silva", YearMonth.of(2021, 12)),
                    vendedorFactory.criarFuncionario("João Mendes", YearMonth.of(2017, 7))
            );

            CalculadoraService calculadoraService = new CalculadoraService(vendedorFactory);
            double totalBeneficios = calculadoraService.calcularTotalBeneficios(funcionarios, 2022, 1);

            assertEquals(10800.0, totalBeneficios);
        }

        @Test
        public void testEncontrarMaiorRecebedor() {
            FuncionarioFactory gerenteFactory = new GerenteFactory();

            List<Funcionario> funcionarios = List.of(
                    gerenteFactory.criarFuncionario("Maria", YearMonth.of(2018, 12)),
                    gerenteFactory.criarFuncionario("José", YearMonth.of(2015, 7))
            );

            CalculadoraService calculadoraService = new CalculadoraService(gerenteFactory);
            Funcionario maiorRecebedor = calculadoraService.encontrarMaiorRecebedor(funcionarios, 2022, 5);

            assertEquals("José", maiorRecebedor.getNome());
        }

        @Test
        public void testEncontrarMaiorRecebedorBeneficios() {

            FuncionarioFactory vendedorFactory = new VendedorFactory();

            List<Funcionario> funcionarios = List.of(
                    vendedorFactory.criarFuncionario("Ana Silva", YearMonth.of(2021, 12)),
                    vendedorFactory.criarFuncionario("João Mendes", YearMonth.of(2017, 7))
            );

            CalculadoraService calculadoraService = new CalculadoraService(vendedorFactory);
            String maiorRecebedorBeneficios = calculadoraService.encontrarMaiorRecebedorBeneficios(funcionarios, 2022, 5);
            assertEquals("", maiorRecebedorBeneficios);
        }

        @Test
        public void testEncontrarMelhorVendedor() {
            FuncionarioFactory vendedorFactory = new VendedorFactory();
            List<Funcionario> vendedores = List.of(
                    vendedorFactory.criarFuncionario("Ana Silva", YearMonth.of(2021, 12)),
                    vendedorFactory.criarFuncionario("João Mendes", YearMonth.of(2017, 7))
            );

            CalculadoraService calculadoraService = new CalculadoraService(vendedorFactory);
            Funcionario melhorVendedor = calculadoraService.encotrarMelhorVendedor(vendedores, 2022, 5);
            assertEquals("Ana Silva", melhorVendedor.getNome());
        }
    }

