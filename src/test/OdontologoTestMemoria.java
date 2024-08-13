package test;

import model.Odontologos;
import dao.impl.DaoEnMemoria;
import org.apache.log4j.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;


import static org.junit.jupiter.api.Assertions.*;

public class OdontologoTestMemoria {
    static final Logger logger = Logger.getLogger(OdontologoTestMemoria.class);
    OdontologoService odontologoService = new OdontologoService(new DaoEnMemoria());

    @Test
    @DisplayName("Test creación de odontologo")
    void caso1(){
        Odontologos odontologo = new Odontologos("Pig","Pepito","Canarios");
        Odontologos odontologodb = odontologoService.save(odontologo);
        assertNotNull(odontologodb.getNombre());
    }

    @Test
    @DisplayName("Test nombre")
    void caso2(){
        DaoEnMemoria daoEnMemoria = new DaoEnMemoria();
        OdontologoService odontologoService = new OdontologoService(daoEnMemoria);
        Odontologos odontologo1 = new Odontologos( "Pig","Pepito","Canarios");
        Odontologos odontologo2 = new Odontologos( "Pig2","Pepito2","Canarios2");
        Odontologos odontologodb1 = odontologoService.save(odontologo1);
        Odontologos odontologodb2 = odontologoService.save(odontologo2);
        assertNotNull(daoEnMemoria.findAll());
    }
}