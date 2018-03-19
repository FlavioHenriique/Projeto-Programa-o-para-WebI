package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.SolicitacaoDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Solicitacao;
import com.ifpb.sisride.modelo.Usuario;
import java.sql.SQLException;
import java.util.List;

public class GerenciadorSolicitacao {

    private DaoFactoryIF fabrica = null;
    private SolicitacaoDao dao = null;

    public GerenciadorSolicitacao() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        dao = fabrica.criaSolicitacaoDao();
    }

    public List<Solicitacao> listarSolicitacoes(Usuario u) throws SQLException, ClassNotFoundException {
        return dao.listarSolicitacoes(u);
    }

    public void aceitarSolicitacao(String solicitador, String requisitado, String tipo) throws SQLException {

        dao.aceitaSolicitacao(solicitador, requisitado, tipo);
    }

    public void recusarSolicitacao(String solicitador, String requisitado, String tipo) throws SQLException {
        dao.recusaSolicitacao(solicitador, requisitado, tipo);
    }

    public void desfazerRelacionamento(String usuario1, String usuario2, String tipo) throws SQLException {
        dao.DesfazerRelacionamento(usuario1, usuario2,tipo);
    }
}
