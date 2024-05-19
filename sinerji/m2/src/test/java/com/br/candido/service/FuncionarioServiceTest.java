package com.br.candido.service;
import com.br.candido.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioServiceTest {
    private FuncionarioService service;
    private List<Funcionario> funcionarios;
    private List<Vendedor> vendedores;

    @BeforeEach
    public void setup() {
        service = new FuncionarioService();
        funcionarios = Arrays.asList(
                new Secretario("Jorge Carvalho", YearMonth.of(2018, 1)),
                new Secretario("Maria Souza", YearMonth.of(2015, 12)),
                new Vendedor("Ana Silva", YearMonth.of(2021, 12), criarVendasAna()),
                new Vendedor("João Mendes", YearMonth.of(2021, 12), criarVendasJoao()),
                new Gerente("Juliana Alves", YearMonth.of(2017, 7)),
                new Gerente("Bento Albino", YearMonth.of(2014, 3))
        );

        vendedores = Arrays.asList(
                new Vendedor("Ana Silva", YearMonth.of(2021, 12), criarVendasAna()),
                new Vendedor("João Mendes", YearMonth.of(2021, 12), criarVendasJoao())
        );
    }

    private Map<YearMonth, Double> criarVendasAna() {
        Map<YearMonth, Double> vendas = new HashMap<>();
        vendas.put(YearMonth.of(2021, 12), 5200.0);
        vendas.put(YearMonth.of(2022, 1), 4000.0);
        vendas.put(YearMonth.of(2022, 2), 4200.0);
        vendas.put(YearMonth.of(2022, 3), 5850.0);
        vendas.put(YearMonth.of(2022, 4), 7000.0);
        return vendas;
    }

    private Map<YearMonth, Double> criarVendasJoao() {
        Map<YearMonth, Double> vendas = new HashMap<>();
        vendas.put(YearMonth.of(2021, 12), 3400.0);
        vendas.put(YearMonth.of(2022, 1), 7700.0);
        vendas.put(YearMonth.of(2022, 2), 5000.0);
        vendas.put(YearMonth.of(2022, 3), 5900.0);
        vendas.put(YearMonth.of(2022, 4), 6500.0);
        return vendas;
    }

    @Test
    public void testCalcularTotalPago() {
        double totalPago = service.calcularTotalPago(funcionarios, YearMonth.of(2022, 1));
        assertEquals(127310,totalPago);
    }

    @Test
    public void testCalcularTotalSalarios() {
        double totalSalarios = service.calcularTotalSalarios(funcionarios, YearMonth.of(2022, 1));
        assertEquals(78000.0, totalSalarios);
    }

    @Test
    public void testCalcularTotalBeneficios() {
        double totalBeneficios = service.calcularTotalBeneficios(funcionarios, YearMonth.of(2022, 1));
        assertEquals(49310, totalBeneficios);
    }

    @Test
    public void testObterMaiorSalario() {
        Funcionario maiorSalario = service.obterMaiorSalario(funcionarios, YearMonth.of(2022, 1));
        assertEquals("Bento Albino", maiorSalario.getNome());
    }

    @Test
    public void testObterMaiorBeneficio() {
        Funcionario maiorBeneficio = service.obterMaiorBeneficio(funcionarios, YearMonth.of(2022, 1));
        assertEquals("Bento Albino", maiorBeneficio.getNome());
    }

    @Test
    public void testObterMaiorVenda() {
        Vendedor maiorVenda = service.obterMaiorVenda(vendedores, YearMonth.of(2022, 1));
        assertEquals("João Mendes", maiorVenda.getNome());
    }
}