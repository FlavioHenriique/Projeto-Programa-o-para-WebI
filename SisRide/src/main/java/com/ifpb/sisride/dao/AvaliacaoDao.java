package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Avaliacao;
import com.ifpb.sisride.modelo.Usuario;
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

public class AvaliacaoDao implements Dao<Avaliacao> {

    private Connection con;

    public AvaliacaoDao() throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean salvar(Avaliacao obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "INSERT INTO Avaliacao (comentario,nota,"
                + "usuarioavaliado,avaliador,tipo,momento) VALUES (?,?,?,?,?, current_timestamp)";

        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, obj.getComentario());
        stmt.setFloat(2, obj.getNota());
        stmt.setString(3, obj.getUsuarioAvaliado().getEmail());
        stmt.setString(4, obj.getAvaliador().getEmail());
        stmt.setString(5, obj.getTipo());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
    }

    @Override
    public Avaliacao buscar(Object obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM Avaliacao WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        ResultSet resultado = stmt.executeQuery();
        try {
            UsuarioDao dao = new UsuarioDao();

            if (resultado.next()) {

                Usuario avaliado = dao.buscar(resultado.getString("usuarioavaliado"));
                Usuario avaliador = dao.buscar(resultado.getString("avaliador"));

                Avaliacao a = new Avaliacao(resultado.getString("comentario"),
                        resultado.getFloat("nota"), avaliado, avaliador, resultado.getInt("codigo"));
                a.setTipo(resultado.getString("tipo"));
                
                stmt.close();
                con.close();
                return a;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        stmt.close();
        con.close();

        return null;
    }

    @Override
    public boolean atualizar(Avaliacao obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (buscar(obj.getCodigo()) != null) {
            String sql = "UPDATE Avaliacao SET comentario =?,nota=?,usuarioavaliado=?,"
                    + "avaliador=? WHERE codigo = ? and tipo = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getComentario());
            stmt.setFloat(2, obj.getNota());
            stmt.setString(3, obj.getUsuarioAvaliado().getEmail());
            stmt.setString(4, obj.getAvaliador().getEmail());
            stmt.setInt(5, obj.getCodigo());
            stmt.setString(6, obj.getTipo());
            stmt.execute();
            stmt.close();
        }
        con.close();

        return true;
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {

        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "DELETE FROM Avaliacao WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        stmt.execute();
        stmt.close();
        con.close();
        return true;

    }

    public List<Avaliacao> AvaliacoesUsuario(String avaliado) throws SQLException {
        try {
            con = ConFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT codigo FROM Avaliacao WHERE UsuarioAvaliado = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, avaliado);
        ResultSet rs = stmt.executeQuery();
        
        List<Avaliacao> lista = new ArrayList<>();
        
        while(rs.next()){
            Avaliacao avaliacao = this.buscar(rs.getInt("codigo"));
            lista.add(avaliacao);
        }
        rs.close();
        stmt.close();
        
        return lista;
    }
}
