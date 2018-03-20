package com.ifpb.sisride.dao;

import com.ifpb.sisride.exception.CadastroException;
import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Usuario;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements Dao<Usuario> {

    private final Connection con;

    public UsuarioDao() throws ClassNotFoundException, SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean salvar(Usuario obj) throws SQLException, CadastroException, ClassNotFoundException {

        String sql = "INSERT INTO USUARIO(Email,Nome,Nascimento,Senha,Profissão,"
                + "Cidade,Sexo) VALUES (?,?,?,?,?,?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, obj.getEmail());
        stmt.setString(2, obj.getNome());
        stmt.setDate(3, Date.valueOf(obj.getNascimento()));
        stmt.setString(4, obj.getSenha());
        stmt.setString(5, obj.getProfissao());
        stmt.setString(6, obj.getCidade());
        stmt.setString(7, obj.getSexo());

        if (obj.getFoto() != null) {
            sql.concat(" UPDATE USUARIO SET foto = ? WHERE email = ?");
            stmt.setBinaryStream(8, obj.getFoto());
            stmt.setString(9, obj.getEmail());
        }

        stmt.execute();
        con.close();
        stmt.close();

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
                    resultado.getString("cidade"), resultado.getString("sexo"), resultado.getBytes("foto"));
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

            stmt.execute();

            if (obj.getFoto() instanceof FileInputStream) {
                setFoto(obj.getEmail(), obj.getFoto());
            }
            return true;
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

    public boolean autenticar(String email, String senha) throws SQLException {
        if (email != "" && senha != "") {
            String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            if (stmt.executeQuery().next()) {
                return true;
            }
        }
        return false;
    }

    public void setFoto(String email, InputStream foto) throws SQLException {

        String sql = "UPDATE Usuario SET foto = ? WHERE email = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(2, email);
        stmt.setBinaryStream(1, foto);
        stmt.execute();
    }

    public Usuario buscaNome(String email) throws SQLException {

        String sql = "SELECT email FROM Usuario WHERE nome ilike ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet result = stmt.executeQuery();

        if (result.next()) {

            Usuario u = new Usuario();
            Usuario u2 = this.buscar(result.getString("email"));

            u = u2;
            return u;
        }
        return null;
    }

    public void solicitacao(String usuario, String amigo, String tipo) throws SQLException {

        String sql = "INSERT INTO Solicitacao (EmailUsuario, EmailAmigo, "
                + "Situacao,tipo) VALUES (?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, amigo);
        stmt.setString(3, "pendente");
        stmt.setString(4, tipo);
        stmt.execute();

    }

    public List<String> getNotificacoes(String email) throws SQLException {

        String sql = "SELECT u.nome,n.mensagem FROM NOTIFICACAO n, usuario u WHERE notificado = ?"
                + "and n.notificador = u.email order by momento desc";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet result = stmt.executeQuery();
        List<String> notificacoes = new ArrayList<>();
        System.out.println("print");
        while (result.next()) {
            String notificacao = result.getString("nome") + " " + result.getString("mensagem");
            notificacoes.add(notificacao);
            System.out.println(notificacao);
        }

        return notificacoes;
    }

}
