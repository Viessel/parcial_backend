package com.odontologica.persistence.impl;

import com.odontologica.model.Odontologo;
import com.odontologica.persistence.IDao;
import com.odontologica.persistence.utils.ConfiguracionJDBCH2;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;
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
        boolean rs = true;
        h2.cargarElControlador();
        try ( Connection conn = h2.conectarConBaseDeDatos();
              PreparedStatement pStmt = conn.prepareStatement("INSERT INTO odontologo(id, nombre, apellido, matricula) VALUES (?,?,?,?);")) {
            conn.setAutoCommit(false); //no es necesario pero...
            pStmt.setInt(1, odontologo.getId());
            pStmt.setString(2, odontologo.getNombre());
            pStmt.setString(3, odontologo.getApellido());
            pStmt.setString(4, odontologo.getNumMatricula());
            pStmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            logger.error("Error al intentar guardar odontologo." + e.getMessage());
            // cual es el ambito de trys con argumentos?/
            //try {
            //  logger.info("Se intentara revertir la transacción.");
            //  conn.rollback();
            //} catch (SQLException ex) {
            //    logger.error("La transacción no se puedo revertir" + ex.getMessage());
            //}
        }
        return rs;
    }
}