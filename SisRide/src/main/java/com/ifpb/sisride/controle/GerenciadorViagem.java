package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.ViagemDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Carro;
import com.ifpb.sisride.modelo.Lugar;
import com.ifpb.sisride.modelo.Usuario;
import com.ifpb.sisride.modelo.Viagem;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GerenciadorViagem {

    private DaoFactoryIF fabrica = null;
    private ViagemDao dao = null;

    public GerenciadorViagem() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        dao = fabrica.criaViagemDao();
    }

    public boolean adicionaViagem(int vagas, LocalDate data, String hora, float valor,
            Usuario motorista, String musica, boolean animais, boolean fumar,
            String conversa, Lugar destino, Lugar partida, Carro carro) throws SQLException, ClassNotFoundException {
        Viagem v = new Viagem(vagas, data, hora, valor, motorista, musica,
                animais, fumar, conversa, destino, partida, carro);
        dao.salvar(v);
        return true;
    }

    public void removeViagem(int codigo) throws SQLException {
        dao.deletar(codigo);
    }

    public void atualizaViagem(int vagas, LocalDate data, String hora, float valor,
            Usuario motorista, String musica, boolean animais, boolean fumar,
            String conversa, Lugar destino, Lugar partida, Carro carro, int codigo) throws SQLException {
        Viagem v = new Viagem(vagas, data, hora, valor, motorista, musica,
                animais, fumar, conversa, destino, partida, carro, codigo);
        dao.atualizar(v);
    }

    public Viagem buscaViagem(int codigo) throws SQLException {

        return dao.buscar(codigo);
    }

    public List<Viagem> buscaNome(String nome) throws SQLException {

        return dao.buscarNome(nome);
    }

    public List<Viagem> minhasCaronas(String usuario) throws SQLException {
        return dao.minhasCaronas(usuario);
    }

    public void solicitaVaga(String email, int codigo) throws SQLException {
        dao.solicitaVaga(email, codigo);
    }

    public void confirmaVaga(String solicitante, int viagem, String resposta) throws SQLException {
        dao.confirmaVaga(solicitante, viagem, resposta);
    }

    public List<Viagem> caronasSolicitadas(String email) throws SQLException, ClassNotFoundException {
        return dao.caronasSolicitadas(email);
    }

    public void recomendaCarona(String motorista, String passageiro, int carona) throws SQLException {
        dao.recomendaCarona(motorista, passageiro, carona);
    }

    public List<Viagem> getRecomendacoes(String passageiro) throws SQLException {

        return dao.getRecomendacoes(passageiro);
    }

    public void cancelaSolicitacao(int viagem, String usuario) throws SQLException {
        dao.cancelaSolicitacao(viagem, usuario);
    }
    
    public List<Viagem> buscarTodas() throws SQLException{
        return dao.buscarTodas();
    }
    
    public void divulgarCarona(int codigo) throws SQLException{
        dao.divulgarCarona(codigo);
    }
}
