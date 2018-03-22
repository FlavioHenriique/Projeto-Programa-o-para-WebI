package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Avaliacao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvaliacaoDao implements Dao<Avaliacao> {

    private final Connection con;

    public AvaliacaoDao() throws SQLException, ClassNotFoundException {
       con = ConFactory.getConnection();
    }

    @Override
    public boolean salvar(Avaliacao obj) throws SQLException {

        String sql = "INSERT INTO Avaliacao (comentario,hora,data,nota,"
                + "usuarioavaliado,avaliador) VALUES (?,?,?,?,?,?)";

        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        java.util.Date data;
        try {
            data = (java.util.Date) formatador.parse(obj.getHora());
            Time time = new Time(data.getTime());
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getComentario());
            stmt.setTime(2, time);
            stmt.setDate(3, Date.valueOf(obj.getData()));
            stmt.setFloat(4, obj.getNota());
            stmt.setString(5, obj.getUsuarioAvaliado());
            stmt.setString(6, obj.getAvaliador());
            return stmt.execute();
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Avaliacao buscar(Object obj) throws SQLException {
        String sql = "SELECT * FROM Avaliacao WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            Avaliacao a = new Avaliacao(resultado.getString("comentario"),
                    resultado.getTime("hora").toString(), resultado.getDate("data")
                    .toLocalDate(), resultado.getFloat("nota"), resultado.getString("usuarioavaliado"),
                    resultado.getString("avaliador"),resultado.getInt("codigo"));
            stmt.close();
            
            return a;
        }
        return null;
    }

    @Override
    public boolean atualizar(Avaliacao obj) throws SQLException {
        if (buscar(obj.getCodigo()) != null) {
            String sql = "UPDATE Avaliacao SET comentario =?,hora=?,data=?, "
                    + "nota=?,usuarioavaliado=?,avaliador=? WHERE codigo = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            java.util.Date data;
            try {
                data = (java.util.Date) formatador.parse(obj.getHora());
                Time time = new Time(data.getTime());
                stmt.setString(1, obj.getComentario());
                stmt.setTime(2, time);
                stmt.setDate(3, Date.valueOf(obj.getData()));
                stmt.setFloat(4, obj.getNota());
                stmt.setString(5, obj.getUsuarioAvaliado());
                stmt.setString(6, obj.getAvaliador());
                stmt.setInt(7, obj.getCodigo());
                return stmt.execute();
            } catch (ParseException ex) {
                Logger.getLogger(AvaliacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {
        String sql = "DELETE FROM Avaliacao WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        return stmt.execute();
    }
}
