package com.odontologica.test;


import com.odontologica.model.Odontologo;
import com.odontologica.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class OdontologoServiceTest {
    private OdontologoService sujetoDePrueba = new OdontologoService();
    @Test
    public void TestConsultarTodosLosOdontologos(){
        Odontologo od = new Odontologo(4, "elod", "ontologo", "999");
        int expectedSize = 4;

        sujetoDePrueba.guardarOdontologo(od);
        Collection<Odontologo>  resultado = sujetoDePrueba.consultarTodosLosOdontologos();
        Assertions.assertTrue(resultado.size() == expectedSize);
    }


}
