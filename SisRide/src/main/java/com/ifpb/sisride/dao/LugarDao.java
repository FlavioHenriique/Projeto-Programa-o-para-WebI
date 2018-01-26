package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Lugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LugarDao implements Dao<Lugar> {

    private Connection con;

    public LugarDao() throws ClassNotFoundException, SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean salvar(Lugar obj) throws SQLException {
        String sql = "INSERT INTO LUGAR (descricao,nome,rua,cidade,estado,"
                + "numero,emailUsuario,identificacao) values (?,?,?,?,?,?,?,?)";
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
    }

    @Override
    public Lugar buscar(Object obj) throws SQLException {
        String sql = "SELECT * FROM LUGAR WHERE identificacao = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            Lugar lugar = new Lugar(resultado.getString("descricao"),
                    resultado.getString("nome"), resultado.getString("rua"),
                    resultado.getString("cidade"), resultado.getString("estado"),
                    resultado.getInt("numero"), resultado.getString("emailusuario"));
            return lugar;
        }
        return null;
    }

    @Override
    public boolean atualizar(Lugar obj) throws SQLException {
        if (buscar(obj.getIdentificacao()) != null) {
            String sql = "UPDATE LUGAR set descricao= ?, nome = ?, rua = ?, "
                    + "cidade=?,estado=?,numero = ?, emailusuario = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getRua());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getEstado());
            stmt.setInt(6, obj.getNumero());
            stmt.setString(7, obj.getEmailUsuario());
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

}
