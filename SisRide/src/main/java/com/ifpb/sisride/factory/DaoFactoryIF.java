package com.ifpb.sisride.factory;

import com.ifpb.sisride.dao.AvaliacaoDao;
import com.ifpb.sisride.dao.CarroDao;
import com.ifpb.sisride.dao.LugarDao;
import com.ifpb.sisride.dao.UsuarioDao;
import com.ifpb.sisride.dao.ViagemDao;
import java.sql.SQLException;

public interface DaoFactoryIF {

    public AvaliacaoDao criaAvaliacaoDao()  throws SQLException, ClassNotFoundException;

    public CarroDao criaCarroDao()  throws SQLException, ClassNotFoundException;

    public LugarDao criaLugarDao()  throws SQLException, ClassNotFoundException;

    public UsuarioDao criaUsuarioDao()  throws SQLException, ClassNotFoundException;

    public ViagemDao criaViagemDao()  throws SQLException, ClassNotFoundException;
}
