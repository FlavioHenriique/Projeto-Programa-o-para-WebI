package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao implements Dao<Usuario> {

    private Connection con;

    public UsuarioDao() throws ClassNotFoundException, SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean salvar(Usuario obj) throws SQLException {

        String sql = "INSERT INTO USUARIO(Email,Nome,Nascimento,Senha,Profissão,"
                + "Cidade,Sexo) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, obj.getEmail());
        stmt.setString(2, obj.getNome());
        stmt.setDate(3, Date.valueOf(obj.getNascimento()));
        stmt.setString(4, obj.getSenha());
        stmt.setString(5, obj.getProfissao());
        stmt.setString(6, obj.getCidade());
        stmt.setString(7, obj.getSexo());
        stmt.execute();
        return true;
    }

    @Override
    public Usuario buscar(Object obj) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE email= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setObject(1, obj);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            Usuario u = new Usuario(resultado.getString("email"),
                    resultado.getString("senha"), resultado.getString("nome"),
                    resultado.getDate("nascimento").toLocalDate(), resultado.getString("profissão"),
                    resultado.getString("cidade"), resultado.getString("sexo"));
            return u;
        }
        return null;
    }

    @Override
    public boolean atualizar(Usuario obj) throws SQLException {

        Usuario u = buscar(obj.getEmail());
        if (u == null) {
            return false;
        } else {
            String sql = "UPDATE USUARIO SET email=?, senha =?, nome=?,"
                    + " nascimento=?, profissão=?,cidade=?,sexo=? WHERE email= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getEmail());
            stmt.setString(2, obj.getSenha());
            stmt.setString(3, obj.getNome());
            stmt.setDate(4, Date.valueOf(obj.getNascimento()));
            stmt.setString(5, obj.getProfissao());
            stmt.setString(6, obj.getCidade());
            stmt.setString(7, obj.getSexo());
            stmt.setString(8, obj.getEmail());
            return stmt.execute();
        }
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {
        if (buscar(obj) != null) {
            String sql = "DELETE FROM Usuario WHERE email= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setObject(1, obj);
            return stmt.execute();
        }
        return false;
    }
}
