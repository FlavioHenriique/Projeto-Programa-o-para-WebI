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
        return solicitacoes;
    }
    
    public void aceitaSolicitacao(String atual, String solicitador, String tipo) throws SQLException {
        
        String sql = "UPDATE Solicitacao SET situacao = 'aceita' WHERE emailUsuario = ? "
                + "AND emailAmigo = ? AND tipo = ?";
        System.out.println("aqui "+atual + solicitador + tipo);
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, atual);
        stmt.setString(2, solicitador);
        stmt.setString(3, tipo);
        stmt.execute();
    }
}
