package dao.impl;

import dao.IDao;
import model.Odontologos;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DaoEnMemoria implements IDao<Odontologos> {
    public static final Logger logger = Logger.getLogger(DaoEnMemoria.class);
    private List<Odontologos> odontologos = new ArrayList<>();
    @Override
    public Odontologos save(Odontologos odontologo) {
        odontologo.setId(odontologos.size()+1);
        odontologos.add(odontologo);
        logger.info("Odontologo guardado en memoria " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologos> findAll() {
        logger.info("Odontologos encontrados en memoria " + odontologos);
        return new ArrayList<>(odontologos);
    }
}