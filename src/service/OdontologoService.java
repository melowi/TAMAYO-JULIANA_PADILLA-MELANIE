package service;
import dao.IDao;
import model.Odontologos;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologos> odontologoIDao;
    public OdontologoService(IDao<Odontologos> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologos save(Odontologos odontologo){
        return odontologoIDao.save(odontologo);
    }
    public List<Odontologos> findAll() {
        return odontologoIDao.findAll();
    }
}