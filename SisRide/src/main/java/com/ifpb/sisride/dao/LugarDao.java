package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Lugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LugarDao implements Dao<Lugar> {

    private Connection con;

    public LugarDao() throws ClassNotFoundException, SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean salvar(Lugar obj) throws SQLException {
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
        return stmt.execute();
    }

    public List<Lugar> buscarLugares() throws SQLException {
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
        System.out.println(lista.toString());
        return lista;
    }

    @Override
    public boolean atualizar(Lugar obj) throws SQLException {
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
            return stmt.execute();
        } else {
            return false;
        }
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {
        String sql = "DELETE FROM lugar WHERE identificacao = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        return stmt.execute();
    }

    public List<Lugar> buscarMeusLugares(String email) throws SQLException {

        String sql = "SELECT * FROM lugar WHERE emailUsuario = ?";

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
        return lugares;
    }

    public List<Lugar> buscaLugares(String email) throws SQLException {

        String sql = "SELECT * FROM Lugar WHERE EmailUsuario = ?";
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
        return lista;
    }

    @Override
    public Lugar buscar(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
