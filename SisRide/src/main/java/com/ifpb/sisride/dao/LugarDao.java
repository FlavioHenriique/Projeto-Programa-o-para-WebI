package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Lugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LugarDao implements Dao<Lugar> {

    private Connection con;

    public LugarDao() throws ClassNotFoundException, SQLException {

    }

    @Override
    public boolean salvar(Lugar obj) throws SQLException {
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "INSERT INTO LUGAR (descricao,nome,rua,cidade,estado,"
                + "numero,emailUsuario) values (?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, obj.getDescricao());
        stmt.setString(2, obj.getNome());
        stmt.setString(3, obj.getRua());
        stmt.setString(4, obj.getCidade());
        stmt.setString(5, obj.getEstado());
        stmt.setInt(6, obj.getNumero());
        stmt.setString(7, obj.getEmailUsuario());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
    }

    public List<Lugar> buscarLugares() throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT * FROM LUGAR ";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        List<Lugar> lista = new ArrayList<>();
        while (resultado.next()) {
            Lugar lugar = new Lugar(resultado.getString("descricao"),
                    resultado.getString("nome"), resultado.getString("rua"),
                    resultado.getString("cidade"), resultado.getString("estado"),
                    resultado.getInt("numero"), resultado.getString("emailusuario"),
                    resultado.getInt("identificacao"));
            lista.add(lugar);

        }

        resultado.close();
        stmt.close();
        con.close();

        return lista;
    }

    @Override
    public boolean atualizar(Lugar obj) throws SQLException {
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (buscar(obj.getIdentificacao()) != null) {
            String sql = "UPDATE LUGAR set descricao= ?, nome = ?, rua = ?, "
                    + "cidade=?,estado=?,numero = ?, emailusuario = ? WHERE identificacao=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getRua());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getEstado());
            stmt.setInt(6, obj.getNumero());
            stmt.setString(7, obj.getEmailUsuario());
            stmt.setInt(8, obj.getIdentificacao());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } else {

            return false;
        }
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "DELETE FROM Viagem WHERE partida = ? OR destino = ?;"
                + "DELETE FROM lugar WHERE identificacao = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        stmt.setInt(2, (int) obj);
        stmt.setInt(3, (int) obj);
        stmt.execute();
        stmt.close();
        con.close();

        return true;
    }

    public List<Lugar> buscarMeusLugares(String email) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT * FROM lugar WHERE emailUsuario = ? ORDER BY nome";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet result = stmt.executeQuery();

        List<Lugar> lugares = new ArrayList<>();

        while (result.next()) {

            Lugar l = new Lugar();
            l.setCidade(result.getString("cidade"));
            l.setDescricao(result.getString("descricao"));
            l.setEmailUsuario(result.getString("emailUsuario"));
            l.setEstado(result.getString("estado"));
            l.setNome(result.getString("nome"));
            l.setNumero(result.getInt("numero"));
            l.setRua(result.getString("rua"));
            l.setIdentificacao(result.getInt("identificacao"));

            lugares.add(l);
        }
        result.close();
        stmt.close();
        con.close();

        return lugares;
    }

    public List<Lugar> buscaLugares(String email) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT * FROM Lugar WHERE EmailUsuario = ? ORDER BY nome";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet result = stmt.executeQuery();
        List<Lugar> lista = new ArrayList<>();

        while (result.next()) {
            Lugar lugar = new Lugar();
            lugar.setCidade(result.getString("cidade"));
            lugar.setDescricao(result.getString("descricao"));
            lugar.setEmailUsuario(result.getString("emailusuario"));
            lugar.setEstado(result.getString("estado"));
            lugar.setIdentificacao(result.getInt("identificacao"));
            lugar.setNome(result.getString("nome"));
            lugar.setNumero(result.getInt("numero"));
            lugar.setRua(result.getString("rua"));

            lista.add(lugar);
        }
        result.close();
        stmt.close();
        con.close();

        return lista;
    }

    @Override
    public Lugar buscar(Object obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LugarDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT * FROM Lugar WHERE identificacao = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);

        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            Lugar l = new Lugar();
            l.setCidade(result.getString("cidade"));
            l.setDescricao(result.getString("descricao"));
            l.setEmailUsuario(result.getString("emailusuario"));
            l.setEstado(result.getString("estado"));
            l.setIdentificacao(result.getInt("identificacao"));
            l.setNome(result.getString("nome"));
            l.setNumero(result.getInt("numero"));
            l.setRua(result.getString("rua"));

            result.close();
            stmt.close();
            con.close();

            return l;
        }
        stmt.close();
        con.close();

        return null;
    }

}
