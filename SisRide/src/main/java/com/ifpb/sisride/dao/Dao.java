
package com.ifpb.sisride.dao;

import java.sql.SQLException;


public interface Dao<T> {
    
    boolean salvar(T obj) throws SQLException;
    T buscar(Object obj) throws SQLException;
    boolean atualizar(T obj) throws SQLException;
    boolean deletar(Object obj) throws SQLException;
}
