package com.odontologica.persistence.impl;

import com.odontologica.model.Odontologo;
import com.odontologica.persistence.IDao;
import com.odontologica.persistence.utils.ConfiguracionJDBCH2;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private Logger logger = LogManager.getLogger(OdontologoDaoH2.class);
    private ConfiguracionJDBCH2 h2 = new ConfiguracionJDBCH2();

    @Override

    public List<Odontologo> consultarTodos() {
        List<Odontologo> resultado = new ArrayList<>();

        h2.cargarElControlador();
        try (Connection conn = h2.conectarConBaseDeDatos();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM odontologo");

            while (rs.next()) {
                Odontologo od = new Odontologo(rs.getInt("id"));
                od.setNombre(rs.getString("nombre"));
                od.setApellido(rs.getString("apellido"));
                od.setNumMatricula(rs.getString("matricula"));
                resultado.add(od);
            }

        } catch (SQLException e) {
            logger.error("Ha ocurrido un error al consultar los odontologos." + e.getMessage());
        }
        return resultado;
    }

    @Override
    public boolean guardar(Odontologo odontologo) {
        boolean rs = false;
        h2.cargarElControlador();
        try (Connection conn = h2.conectarConBaseDeDatos();
             Statement stmt = conn.createStatement()) {

            rs = stmt.execute("INSERT INTO odontologo(id, nombre, apellido, matricula) VALUES(" + odontologo.getId() + ",'"+ odontologo.getNombre() + "','"+ odontologo.getApellido() + "','"+ odontologo.getNumMatricula() + "');");
        } catch (SQLException e) {
            logger.error("Error al intentar guardar odontologo" + e.getMessage());
        }
        return rs;
    }
}