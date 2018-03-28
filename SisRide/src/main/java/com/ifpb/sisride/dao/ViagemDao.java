package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Carro;
import com.ifpb.sisride.modelo.Lugar;
import com.ifpb.sisride.modelo.Usuario;
import com.ifpb.sisride.modelo.Viagem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViagemDao implements Dao<Viagem> {

    private Connection con = null;

    public ViagemDao() throws ClassNotFoundException, SQLException {
    }

    @Override
    public boolean salvar(Viagem obj) throws SQLException, ClassNotFoundException {

        con = ConFactory.getConnection();

        String sql = "INSERT INTO Viagem (vagas,data,horario,valor,motorista,"
                + "musica,animais,fumar,conversa,destino,partida,codcarro, divulgada,soelas)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?, ?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        java.util.Date data;
        try {
            data = (java.util.Date) formatador.parse(obj.getHora());
            Time time = new Time(data.getTime());
            stmt.setInt(1, obj.getVagas());
            stmt.setDate(2, Date.valueOf(obj.getData()));
            stmt.setTime(3, time);
            stmt.setFloat(4, obj.getValor());
            stmt.setString(5, obj.getMotorista().getEmail());
            stmt.setString(6, obj.getMusica());
            stmt.setBoolean(7, obj.isAnimais());
            stmt.setBoolean(8, obj.isFumar());
            stmt.setString(9, obj.getConversa());
            stmt.setInt(10, obj.getDestino().getIdentificacao());
            stmt.setInt(11, obj.getPartida().getIdentificacao());
            stmt.setInt(12, obj.getCarro().getCodigo());
            stmt.setBoolean(13, false);
            stmt.setBoolean(14, obj.isSoelas());
            stmt.execute();
            stmt.close();
            return true;

        } catch (ParseException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        stmt.close();
        con.close();
        return false;
    }

    @Override
    public Viagem buscar(Object obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT * from Viagem WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        ResultSet result = stmt.executeQuery();
        Lugar destino = null;
        Lugar partida = null;
        Usuario motorista = null;
        Carro carro = null;

        if (result.next()) {

            LugarDao dao;
            CarroDao daoCarro;
            UsuarioDao daoUser;
            try {
                dao = new LugarDao();
                daoUser = new UsuarioDao();
                daoCarro = new CarroDao();

                motorista = daoUser.buscar(result.getString("motorista"));
                partida = dao.buscar(result.getInt("partida"));
                destino = dao.buscar(result.getInt("destino"));
                carro = daoCarro.buscar(result.getInt("codCarro"));
                List<Usuario> solicitadores = buscaSolicitadores((int) obj);
                List<Usuario> passageiros = this.getPassageiros((int) obj);

                Viagem viagem = new Viagem(result.getInt("vagas"), result.getDate("data").
                        toLocalDate(), result.getTime("horario").toString(), result.getFloat("valor"),
                        motorista, result.getString("musica"),
                        result.getBoolean("animais"), result.getBoolean("fumar"), result.getString("conversa"),
                        destino, partida, carro, result.getInt("codigo"));
                viagem.setSolicitadores(solicitadores);
                viagem.setPassageiros(passageiros);
                viagem.setDivulgada(result.getBoolean("divulgada"));
                viagem.setSoelas(result.getBoolean("soelas"));

                result.close();
                stmt.close();
                con.close();
                return viagem;

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public boolean atualizar(Viagem obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (buscar(obj.getCodigo()) != null) {
            String sql = "UPDATE  Viagem set vagas = ?, data = ?, horario = ?,"
                    + "valor = ?, motorista = ?, musica =?, animais = ?, "
                    + "fumar =?, conversa = ?, destino = ?, partida = ?, codcarro = ? WHERE codigo = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            java.util.Date data;
            try {
                data = (java.util.Date) formatador.parse(obj.getHora());
                Time time = new Time(data.getTime());
                stmt.setInt(1, obj.getVagas());
                stmt.setDate(2, Date.valueOf(obj.getData()));
                stmt.setTime(3, time);
                stmt.setFloat(4, obj.getValor());
                stmt.setString(5, obj.getMotorista().getEmail());
                stmt.setString(6, obj.getMusica());
                stmt.setBoolean(7, obj.isAnimais());
                stmt.setBoolean(8, obj.isFumar());
                stmt.setString(9, obj.getConversa());
                stmt.setInt(10, obj.getDestino().getIdentificacao());
                stmt.setInt(11, obj.getPartida().getIdentificacao());
                stmt.setInt(12, obj.getCarro().getCodigo());
                stmt.setInt(13, obj.getCodigo());
                stmt.execute();
                stmt.close();
                con.close();

            } catch (ParseException ex) {
                Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "DELETE FROM SOLICITA_VIAGEM WHERE CodViagem = ?;"
                + "DELETE FROM Viagem WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        stmt.setInt(2, (int) obj);

        stmt.execute();
        stmt.close();
        con.close();
        return true;
    }

    public List<Viagem> buscarNome(String nome) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select v.codigo from viagem v, lugar l "
                + "where l.nome ilike ? and v.destino = l.identificacao";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet result = stmt.executeQuery();
        List<Viagem> lista = new ArrayList<>();

        while (result.next()) {
            Viagem viagem = buscar(result.getInt("codigo"));
            lista.add(viagem);
        }
        result.close();
        stmt.close();
        con.close();

        return lista;
    }

    public List<Viagem> minhasCaronas(String usuario) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT codigo FROM viagem where motorista = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        ResultSet rs = stmt.executeQuery();

        List<Viagem> lista = new ArrayList<>();

        while (rs.next()) {
            Viagem viagem = buscar(rs.getInt("codigo"));
            lista.add(viagem);
        }

        rs.close();
        stmt.close();
        con.close();

        return lista;
    }

    public void solicitaVaga(String email, int codigo) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "INSERT INTO Solicita_Viagem (emailUsuario, codViagem, situacao)"
                + "VALUES (?,?,?);"
                + "DELETE FROM RECOMENDACAO WHERE Passageiro = ? AND carona = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setInt(2, codigo);
        stmt.setString(3, "pendente");
        stmt.setString(4, email);
        stmt.setInt(5, codigo);
        stmt.execute();

        stmt.close();
        con.close();
    }

    public List<Usuario> buscaSolicitadores(int viagem) throws SQLException, ClassNotFoundException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT U.* FROM Usuario U, solicita_viagem s WHERE U.email = s.emailusuario "
                + "AND s.situacao = 'pendente' AND s.codviagem = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, viagem);
        ResultSet result = stmt.executeQuery();
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> lista = new ArrayList<>();

        while (result.next()) {
            Usuario u = dao.buscar(result.getString("email"));
            lista.add(u);
        }
        result.close();
        stmt.close();
        con.close();

        return lista;

    }

    public void confirmaVaga(String solicitante, int viagem, String resposta) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "";

        if (resposta.equals("sim")) {
            sql = "UPDATE Solicita_viagem set situacao = 'aceita' where codviagem = ? and emailusuario = ? ;"
                    + "UPDATE Viagem set vagas = vagas - 1 WHERE codigo = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(2, solicitante);
            stmt.setInt(1, viagem);
            stmt.setInt(3, viagem);

            stmt.execute();
            stmt.close();

        } else if (resposta.equals("nao")) {
            sql = "DELETE FROM SOLICITA_VIAGEM where codviagem = ? and emailusuario = ?;"
                    + "INSERT INTO NOTIFICACAO (notificado, mensagem, situacao,momento,notificador,tipo) "
                    + "values (?,?,'pendente',current_timestamp,?,'rejeita')";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(2, solicitante);
            stmt.setInt(1, viagem);
            stmt.setString(3, solicitante);
            stmt.setString(4, "recusou sua solicitação de vaga.");
            stmt.setString(5, this.buscar(viagem).getMotorista().getEmail());
            stmt.execute();
            stmt.close();
        }
        con.close();
    }

    public List<Viagem> caronasSolicitadas(String email) throws SQLException, ClassNotFoundException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT V.codigo, s.situacao FROM solicita_viagem s, viagem v where v.codigo = s.codviagem and s.emailusuario = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        List<Viagem> lista = new ArrayList<>();

        ResultSet result = stmt.executeQuery();
        LugarDao dao = new LugarDao();

        while (result.next()) {
            Viagem v = buscar(result.getInt("codigo"));
            v.setSituacao(result.getString("situacao"));
            lista.add(v);
        }
        result.close();
        stmt.close();
        con.close();

        return lista;
    }

    public void recomendaCarona(String motorista, String passageiro, int carona)
            throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "INSERT INTO Recomendacao (motorista,passageiro,carona) "
                + "values (?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, motorista);
        stmt.setString(2, passageiro);
        stmt.setInt(3, carona);
        stmt.execute();

        stmt.close();
        con.close();
    }

    public List<Viagem> getRecomendacoes(String passageiro) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT carona from Recomendacao WHERE passageiro = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, passageiro);
        ResultSet rs = stmt.executeQuery();

        List<Viagem> viagens = new ArrayList<>();

        while (rs.next()) {
            Viagem viagem = buscar(rs.getInt("carona"));

            viagens.add(viagem);
        }
        rs.close();
        stmt.close();
        con.close();

        return viagens;
    }

    public List<Usuario> getPassageiros(int viagem) throws SQLException, ClassNotFoundException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT U.email from usuario u, solicita_viagem s "
                + "where u.email = s.emailusuario"
                + " and s.situacao = 'aceita' "
                + " and s.codviagem = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, viagem);
        ResultSet rs = stmt.executeQuery();
        List<Usuario> lista = new ArrayList<>();
        UsuarioDao dao = new UsuarioDao();

        while (rs.next()) {
            Usuario u = dao.buscar(rs.getString("email"));
            lista.add(u);
        }

        rs.close();
        stmt.close();
        con.close();

        return lista;

    }

    public void cancelaSolicitacao(int viagem, String usuario) throws SQLException {
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "DELETE FROM SOLICITA_VIAGEM WHERE codviagem = ? AND emailUsuario = ? ;"
                + "UPDATE Viagem set vagas = vagas + 1 WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, viagem);
        stmt.setString(2, usuario);
        stmt.setInt(3, viagem);

        stmt.execute();
        stmt.close();
        con.close();

    }

    public List<Viagem> buscarTodas() throws SQLException {
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT Codigo FROM Viagem ";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Viagem> viagens = new ArrayList<>();

        while (rs.next()) {
            Viagem v = this.buscar(rs.getInt("codigo"));
            viagens.add(v);
        }
        return viagens;
    }

    public void divulgarCarona(int codigo) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "UPDATE Viagem SET divulgada = true WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, codigo);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public List<Viagem> soElas() throws SQLException {
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT codigo FROM viagem WHERE soElas = true";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<Viagem> soElas = new ArrayList<>();
        
        while(rs.next()){
            Viagem v = buscar(rs.getInt("codigo"));
            soElas.add(v);
        }
        return soElas;
    }
}
