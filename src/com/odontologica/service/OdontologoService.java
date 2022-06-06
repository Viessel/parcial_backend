package com.odontologica.service;

import com.odontologica.model.Odontologo;
import com.odontologica.persistence.IDao;
import com.odontologica.persistence.impl.OdontologoDaoH2;

import java.util.Collection;
import java.util.List;

public class OdontologoService {
    IDao<Odontologo> odontologoDao;

    public OdontologoService () {
        odontologoDao = new OdontologoDaoH2();
    }

    public Collection<Odontologo> consultarTodosLosOdontologos(){
        List<Odontologo> odontologos = odontologoDao.consultarTodos();

        return odontologos;
    }

    public boolean guardarOdontologo(Odontologo od) {
        return odontologoDao.guardar(od);
    }
}
