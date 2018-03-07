package com.ifpb.sisride.factory;

import com.ifpb.sisride.dao.AvaliacaoDao;
import com.ifpb.sisride.dao.CarroDao;
import com.ifpb.sisride.dao.LugarDao;
import com.ifpb.sisride.dao.UsuarioDao;
import com.ifpb.sisride.dao.ViagemDao;
import java.sql.SQLException;

public class DaoFactoryBD implements DaoFactoryIF {

    public DaoFactoryBD() {
    }

    @Override
    public CarroDao criaCarroDao() throws SQLException, ClassNotFoundException {
        return new CarroDao();
    }

    @Override
    public LugarDao criaLugarDao() throws SQLException, ClassNotFoundException {
        return new LugarDao();
    }

    @Override
    public UsuarioDao criaUsuarioDao() throws SQLException, ClassNotFoundException {
        return new UsuarioDao();
    }

    @Override
    public ViagemDao criaViagemDao() throws SQLException, ClassNotFoundException {
        return new ViagemDao();
    }

    @Override
    public AvaliacaoDao criaAvaliacaoDao() throws SQLException, ClassNotFoundException {
        return new AvaliacaoDao();
    }
}
