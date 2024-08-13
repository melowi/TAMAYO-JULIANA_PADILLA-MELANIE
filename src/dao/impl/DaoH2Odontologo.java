package dao.impl;

import dao.IDao;
import model.Odontologos;
import org.apache.log4j.Logger;
import db.H2Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DaoH2Odontologo implements IDao<Odontologos> {
    public static final Logger logger = Logger.getLogger(DaoH2Odontologo.class);
    public static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    public static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    @Override
    public Odontologos save(Odontologos odontologo){
        Connection connection = null;
        Odontologos odontologoGuardado = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                Integer idBD = resultSet.getInt(1);
                odontologoGuardado = new Odontologos(idBD, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }
            logger.info("Odontologos guardado" + odontologoGuardado);
        }catch(Exception e){
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex){
                    logger.error(e.getMessage());
                } finally{
                    try {
                        connection.setAutoCommit(true);
                    } catch(SQLException ex){
                        throw new RuntimeException(ex);

                    }


                }
            }logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoGuardado;


    }
    @Override
    public List<Odontologos> findAll() {
        Connection connection = null;
        List<Odontologos> odontologos = new ArrayList<>();
       Odontologos odontologodb = null;
        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String matricula = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                odontologodb = new Odontologos(id, matricula, nombre, apellido);
                // vamos cargando la lista de odontologos
                odontologos.add(odontologodb);
                logger.info("odontologo "+ odontologodb);
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return odontologos;
    }
}
