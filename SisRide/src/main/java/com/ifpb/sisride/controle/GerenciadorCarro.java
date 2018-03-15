package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.CarroDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Carro;
import java.sql.SQLException;

public class GerenciadorCarro {

    private DaoFactoryIF fabrica = null;
    private CarroDao dao = null;

    public GerenciadorCarro() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        dao = fabrica.criaCarroDao();
    }

    public int adicionaCarro(String modelo, String ano, boolean ar_condicionado)
            throws SQLException {
        Carro carro = new Carro(modelo, ano, ar_condicionado);
        System.out.println(carro.toString());
        return dao.salvarCarro(carro);
    }

    public void removeCarro(int codigo) throws SQLException {

        dao.deletar(codigo);
    }

    public void atualizaCarro(String modelo, String ano, boolean ar_condicionado,
            int codigo) throws SQLException {
        Carro carro = new Carro(modelo, ano, ar_condicionado, codigo);
        dao.atualizar(carro);
    }

    public Carro buscaCarro(int codigo) throws SQLException {
        return dao.buscar(codigo);
    }
}
