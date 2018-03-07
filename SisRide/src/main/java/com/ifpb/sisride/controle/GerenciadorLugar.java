package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.LugarDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Lugar;
import java.sql.SQLException;

public class GerenciadorLugar {

    private DaoFactoryIF fabrica = null;
    private LugarDao dao = null;

    public GerenciadorLugar() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        dao = fabrica.criaLugarDao();
    }

    public boolean adicionaLugar(String descricao, String nome, String rua,
            String cidade, String estado, int numero, String emailUsuario)
            throws SQLException {

        Lugar l = new Lugar(descricao, nome, rua, cidade, estado, numero, emailUsuario);
        return dao.salvar(l);
    }

    public boolean removeLugar(int identificacao) throws SQLException {
        return dao.deletar(identificacao);
    }

    public boolean atualizaLugar(String descricao, String nome, String rua,
            String cidade, String estado, int numero, String emailUsuario, int identificacao) 
            throws SQLException {

        Lugar l = new Lugar(descricao, nome, rua, cidade, estado, numero, emailUsuario,identificacao);
        return dao.atualizar(l);
    }
    
    public Lugar buscaLugar(int identificacao) throws SQLException{
        return dao.buscar(identificacao);
    }
}
