package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Solicitacao;
import com.ifpb.sisride.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoDao {

    private Connection con;

    public SolicitacaoDao() throws ClassNotFoundException, SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    public List<Solicitacao> listarSolicitacoes(Usuario usuario) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM SOLICITACAO WHERE emailUsuario = ? OR emailamigo = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getEmail());
        ResultSet result = stmt.executeQuery();

        List<Solicitacao> solicitacoes = new ArrayList<>();
        UsuarioDao dao = new UsuarioDao();

        while (result.next()) {
            Solicitacao s = new Solicitacao(dao.buscar(result.getString("emailUsuario")),
                    dao.buscar(result.getString("emailamigo")), result.getString("tipo"), result.getString("situacao"));

            solicitacoes.add(s);
        }
        stmt.close();
        result.close();
        
        return solicitacoes;
    }

    public void aceitaSolicitacao(String solicitador, String requisitado, String tipo) throws SQLException {

        String sql = "update solicitacao set situacao = 'aceita' where emailamigo = ?  and "
                + "emailusuario = ? and tipo = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, requisitado);
        stmt.setString(2, solicitador);
        stmt.setString(3, tipo);
        stmt.executeUpdate();
        
        stmt.close();
       
    }

    public void recusaSolicitacao(String solicitador, String requisitado, String tipo) throws SQLException {

        String sql = "DELETE FROM Solicitacao WHERE emailUsuario = ? AND"
                + " emailAmigo = ? AND tipo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(2, requisitado);
        stmt.setString(1, solicitador);
        stmt.setString(3, tipo);
        stmt.execute();
        stmt.close();
    }

    public void DesfazerRelacionamento(String usuario1, String usuario2,String tipo) throws SQLException {

        String sql = "DELETE  FROM SOLICITACAO WHERE emailUsuario = ? AND emailAMigo = ? and tipo = ?; "
                + "DELETE FROM SOLICITACAO WHERE emailUsuario = ? AND emailAmigo = ? and tipo = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario1);
        stmt.setString(2, usuario2);
        stmt.setString(3, tipo);
        stmt.setString(4, usuario2);
        stmt.setString(5, usuario1);
        stmt.setString(6, tipo);
        stmt.execute();
        stmt.close();
    }
}
