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

        String sql = "select u.*, s.tipo "
                + "from usuario u, usuario u2, solicitacao s "
                + "where u2.email = ? "
                + "and u2.email = s.emailamigo "
                + "and u.email = s.emailusuario "
                + "and s.situacao = 'pendente'";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        ResultSet result = stmt.executeQuery();

        List<Solicitacao> solicitacoes = new ArrayList<>();

        while (result.next()) {
            
            UsuarioDao dao = new UsuarioDao();
            Usuario u = dao.buscar(result.getString("email"));
            
            Solicitacao s = new Solicitacao(usuario,u,result.getString("tipo"),"pendente");
            
            solicitacoes.add(s);
        }
        return solicitacoes;
    }
}
