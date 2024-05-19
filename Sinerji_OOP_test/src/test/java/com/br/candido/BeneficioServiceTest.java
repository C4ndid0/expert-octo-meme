package com.br.candido;



import com.br.candido.beneficios.TaxaBeneficio;
import com.br.candido.service.BeneficioService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeneficioServiceTest {
    @Test
    public void testCalcularBeneficio() {
        BeneficioService beneficioService = new BeneficioService();
        assertEquals(2000.0, beneficioService.calcularBeneficio(10000.0, TaxaBeneficio.SECRETARIO));
        assertEquals(3000.0, beneficioService.calcularBeneficio(10000.0, TaxaBeneficio.VENDEDOR));
    }
}
