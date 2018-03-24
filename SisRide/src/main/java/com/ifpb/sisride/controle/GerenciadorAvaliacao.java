package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.AvaliacaoDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Avaliacao;
import com.ifpb.sisride.modelo.Usuario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GerenciadorAvaliacao {

    private DaoFactoryIF fabrica = null;
    private AvaliacaoDao dao = null;

    public GerenciadorAvaliacao() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        dao = fabrica.criaAvaliacaoDao();
    }

    public void adicionaAvaliacao(String comentario, float nota, Usuario usuarioAvaliado,
            Usuario avaliador, String tipo) throws SQLException {
        Avaliacao a = new Avaliacao(comentario, nota, usuarioAvaliado, avaliador, tipo);
        dao.salvar(a);
    }

    public void removeAvaliacao(int codigo) throws SQLException {
        dao.deletar(codigo);
    }

    public void atualizaAvaliacao(String comentario, float nota, Usuario usuarioAvaliado,
            Usuario avaliador, int codigo, String tipo) throws SQLException {
        Avaliacao a = new Avaliacao(comentario, nota, usuarioAvaliado, avaliador, codigo);
        a.setTipo(tipo);
        dao.atualizar(a);
    }

    public Avaliacao buscaAvaliacao(int codigo) throws SQLException {
        return dao.buscar(codigo);
    }

    public List<Avaliacao> avaliacoesUsuario(String avaliado) throws SQLException {
        return dao.AvaliacoesUsuario(avaliado);
    }

    public List<Avaliacao> minhasAvaliacoes(String avaliador) throws SQLException {
        return dao.minhasAvaliacoes(avaliador);
    }
}
