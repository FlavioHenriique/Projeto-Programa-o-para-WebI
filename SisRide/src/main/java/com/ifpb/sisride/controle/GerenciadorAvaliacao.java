package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.AvaliacaoDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Avaliacao;
import java.sql.SQLException;
import java.time.LocalDate;

public class GerenciadorAvaliacao {
    
    private DaoFactoryIF fabrica = null;
    private AvaliacaoDao dao = null;
    
    public GerenciadorAvaliacao() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        dao = fabrica.criaAvaliacaoDao();
    }
    
    public void adicionaAvaliacao(String comentario, String hora, LocalDate data,
            float nota, String usuarioAvaliado, String avaliador) throws SQLException {
        Avaliacao a = new Avaliacao(comentario, hora, data, nota, usuarioAvaliado, avaliador);
        dao.salvar(a);
    }
    
    public void removeAvaliacao(int codigo) throws SQLException {
        dao.deletar(codigo);
    }
    
    public void atualizaAvaliacao(String comentario, String hora, LocalDate data,
            float nota, String usuarioAvaliado, String avaliador, int codigo) throws SQLException {
        Avaliacao a = new Avaliacao(comentario, hora, data, nota, usuarioAvaliado, avaliador, codigo);
        dao.atualizar(a);
    }
    
    public Avaliacao buscaAvaliacao(int codigo) throws SQLException {
        return dao.buscar(codigo);
    }
}
