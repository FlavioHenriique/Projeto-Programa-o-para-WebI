package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Mensagem;
import com.ifpb.sisride.modelo.Solicitacao;
import com.ifpb.sisride.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitacaoDao {

    private Connection con;

    public SolicitacaoDao() throws ClassNotFoundException, SQLException {

    }

    public List<Solicitacao> listarSolicitacoes(Usuario usuario) throws SQLException, ClassNotFoundException {

        con = ConFactory.getConnection();

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
        result.close();
        stmt.close();
        con.close();

        return solicitacoes;
    }

    public void aceitaSolicitacao(String solicitador, String requisitado, String tipo) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "update solicitacao set situacao = 'aceita' where emailamigo = ?  and "
                + "emailusuario = ? and tipo = ?;"
                + "insert into notificacao (notificado,notificador,mensagem,situacao,momento,tipo)"
                + " values (?,?,?,?,current_timestamp,'aceita')";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, requisitado);
        stmt.setString(2, solicitador);
        stmt.setString(3, tipo);
        stmt.setString(5, requisitado);
        stmt.setString(4, solicitador);
        stmt.setString(6, "aceitou sua solicitação de " + tipo + ".");
        stmt.setString(7, "pendente");
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void recusaSolicitacao(String solicitador, String requisitado, String tipo) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "DELETE FROM Solicitacao WHERE emailUsuario = ? AND"
                + " emailAmigo = ? AND tipo = ? ;"
                + "insert into notificacao (notificado,notificador,mensagem,situacao,momento,tipo)"
                + " values (?,?,?,?,current_timestamp,'rejeita')";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(2, requisitado);
        stmt.setString(1, solicitador);
        stmt.setString(3, tipo);
        stmt.setString(5, requisitado);
        stmt.setString(4, solicitador);
        stmt.setString(6, "recusou sua solicitação de " + tipo + ".");
        stmt.setString(7, "pendente");
        stmt.execute();
        stmt.close();
        con.close();
    }

    public void DesfazerRelacionamento(String usuario1, String usuario2, String tipo) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        con.close();
    }

    public List<Usuario> listarAmigos(String email) throws SQLException, ClassNotFoundException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT emailusuario, emailamigo FROM SOLICITACAO WHERE (emailusuario = ? "
                + "or emailamigo = ?) and tipo = 'amizade' and situacao = 'aceita'";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, email);
        ResultSet rs = stmt.executeQuery();

        List<Usuario> amigos = new ArrayList<>();
        UsuarioDao dao = new UsuarioDao();

        while (rs.next()) {

            Usuario u = null;

            if (rs.getString("emailusuario").equals(email)) {

                u = dao.buscar(rs.getString("emailamigo"));

            } else if (rs.getString("emailamigo").equals(email)) {

                u = dao.buscar(rs.getString("emailusuario"));
            }
            amigos.add(u);
        }
        rs.close();
        stmt.close();
        con.close();

        return amigos;
    }

    public void enviarMensagem(String usuario, String amigo, String mensagem) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO Mensagem (emailUsuario, emailAmigo, mensagem, momento) VALUES"
                + " (?,?,?, current_timestamp)";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, amigo);
        stmt.setString(3, mensagem);

        stmt.execute();
        stmt.close();
        con.close();

    }
    
    public List<Mensagem> getMensagens(String usuario, String amigo) throws SQLException{
    
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(usuario + amigo);
        String sql = "SELECT * FROM Mensagem WHERE (emailUsuario = ? AND emailAmigo = ?)"
                + " OR (emailUsuario = ? AND emailAMigo = ?) ORDER BY momento asc";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, amigo);
        stmt.setString(3, amigo);
        stmt.setString(4, usuario);
        
        ResultSet rs = stmt.executeQuery();
        
        List<Mensagem> mensagens = new ArrayList<>();
        
        while(rs.next()){
            Mensagem m = new Mensagem();
            m.setAmigo(rs.getString("emailamigo"));
            m.setMensagem(rs.getString("mensagem"));
            m.setUsuario(rs.getString("emailusuario"));
            
            mensagens.add(m);
        }
        
        return mensagens;
        
    }
}
