package test;

import model.Odontologos;
import dao.impl.DaoH2Odontologo;
import org.apache.log4j.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoTest {
    static final Logger logger = Logger.getLogger(OdontologoTest.class);
    OdontologoService odontologoService = new OdontologoService(new DaoH2Odontologo());

    @BeforeAll
    static void createTables(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./Odontologos.database;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Test creación de odontologo")
    void caso1(){
        Odontologos odontologo = new Odontologos(1, "Pig","Pepito","Canarios");
        Odontologos odontologodb = odontologoService.save(odontologo);
        assertNotNull(odontologodb.getNombre());
    }

    @Test
    @DisplayName("Test nombre")
    void caso2(){
        Odontologos odontologo = new Odontologos(1, "Pig","Pepito","Canarios");
        Odontologos odontologodb = odontologoService.save(odontologo);
        assertEquals("Pepito",odontologodb.getNombre());
    }
}