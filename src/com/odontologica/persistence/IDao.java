package com.odontologica.persistence;

import java.util.List;

public interface IDao<T> {
   public abstract boolean guardar(T t);
   public abstract List<T> consultarTodos();
}
