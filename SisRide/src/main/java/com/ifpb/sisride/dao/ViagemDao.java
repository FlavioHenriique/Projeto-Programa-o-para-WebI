package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
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

    private Connection con;

    public ViagemDao() throws ClassNotFoundException, SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean salvar(Viagem obj) throws SQLException {
        String sql = "INSERT INTO Viagem (vagas,data,horario,valor,motorista,"
                + "musica,animais,fumar,conversa,destino,partida,codcarro)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
            stmt.setInt(12, obj.getCodCarro());
            return stmt.execute();
        } catch (ParseException ex) {
            Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Viagem buscar(Object obj) throws SQLException {

        String sql = "SELECT * from Viagem WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        ResultSet result = stmt.executeQuery();
        Lugar destino = null;
        Lugar partida = null;
        Usuario motorista = null;
        if (result.next()) {

            LugarDao dao;
            UsuarioDao daoUser;
            try {
                dao = new LugarDao();
                daoUser = new UsuarioDao();
                
                motorista = daoUser.buscar(result.getString("motorista"));
                partida = dao.buscar(result.getInt("partida"));
                destino = dao.buscar(result.getInt("destino"));

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            Viagem viagem = new Viagem(result.getInt("vagas"), result.getDate("data").
                    toLocalDate(), result.getTime("horario").toString(), result.getFloat("valor"),
                    motorista, result.getString("musica"),
                    result.getBoolean("animais"), result.getBoolean("fumar"), result.getString("conversa"),
                    destino, partida, result.getInt("codcarro"), result.getInt("codigo"));
            return viagem;
        }
        return null;
    }

    @Override
    public boolean atualizar(Viagem obj) throws SQLException {

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
                stmt.setInt(12, obj.getCodCarro());
                stmt.setInt(13, obj.getCodigo());
                return stmt.execute();
            } catch (ParseException ex) {
                Logger.getLogger(ViagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {
        String sql = "DELETE FROM Viagem WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        return stmt.execute();
    }

    public List<Viagem> buscarNome(String nome) throws SQLException {

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

        return lista;
    }
}
