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

        String sql = "INSERT INTO Avaliacao (comentario,nota,"
                + "usuarioavaliado,avaliador,tipo,momento) VALUES (?,?,?,?,?, current_timestamp)";

        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, obj.getComentario());
        stmt.setFloat(2, obj.getNota());
        stmt.setString(3, obj.getUsuarioAvaliado());
        stmt.setString(4, obj.getAvaliador());
        stmt.setString(5, obj.getTipo());
        return stmt.execute();

    }

    @Override
    public Avaliacao buscar(Object obj) throws SQLException {
        String sql = "SELECT * FROM Avaliacao WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            Avaliacao a = new Avaliacao(resultado.getString("comentario"),
                   resultado.getFloat("nota"), resultado.getString("usuarioavaliado"),
                    resultado.getString("avaliador"), resultado.getInt("codigo"));
            stmt.close();

            return a;
        }
        return null;
    }

    @Override
    public boolean atualizar(Avaliacao obj) throws SQLException {
        if (buscar(obj.getCodigo()) != null) {
            String sql = "UPDATE Avaliacao SET comentario =?,nota=?,usuarioavaliado=?,"
                    + "avaliador=? WHERE codigo = ? and tipo = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getComentario());
            stmt.setFloat(2, obj.getNota());
            stmt.setString(3, obj.getUsuarioAvaliado());
            stmt.setString(4, obj.getAvaliador());
            stmt.setInt(5, obj.getCodigo());
            stmt.setString(6, obj.getTipo());
            return stmt.execute();

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
