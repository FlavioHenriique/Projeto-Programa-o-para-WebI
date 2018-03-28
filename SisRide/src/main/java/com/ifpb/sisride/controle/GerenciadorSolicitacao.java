package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.SolicitacaoDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Mensagem;
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
        dao.DesfazerRelacionamento(usuario1, usuario2, tipo);
    }

    public List<Usuario> listarAmigos(String email) throws SQLException, ClassNotFoundException {
        return dao.listarAmigos(email);
    }

    public void enviarMensagem(String usuario, String amigo, String mensagem) throws SQLException {

        dao.enviarMensagem(usuario, amigo, mensagem);
    }

    public List<Mensagem> getMensagens(String usuario, String amigo) throws SQLException {
        return dao.getMensagens(usuario, amigo);
    }
}
