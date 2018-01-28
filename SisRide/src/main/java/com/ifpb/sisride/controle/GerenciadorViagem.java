package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.ViagemDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Viagem;
import java.sql.SQLException;
import java.time.LocalDate;

public class GerenciadorViagem {

    private DaoFactoryIF fabrica = null;
    private ViagemDao dao = null;

    public GerenciadorViagem() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        dao = fabrica.criaViagemDao();
    }

    public boolean adicionaViagem(int vagas, LocalDate data, String hora, float valor,
            String motorista, String musica, boolean animais, boolean fumar,
            String conversa, int destino, int partida, int codCarro) throws SQLException {
        Viagem v = new Viagem(vagas, data, hora, valor, motorista, musica,
                animais, fumar, conversa, destino, partida, codCarro);
        dao.salvar(v);
        return true;
    }

    public void removeViagem(int codigo) throws SQLException {
        dao.deletar(codigo);
    }

    public void atualizaViagem(int vagas, LocalDate data, String hora, float valor,
            String motorista, String musica, boolean animais, boolean fumar,
            String conversa, int destino, int partida, int codCarro,int codigo) throws SQLException {
        Viagem v = new Viagem(vagas, data, hora, valor, motorista, musica,
                animais, fumar, conversa, destino, partida, codCarro,codigo);
        dao.atualizar(v);
    }

    public Viagem buscaViagem(int codigo) throws SQLException {

        return dao.buscar(codigo);
    }
}
