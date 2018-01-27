package com.ifpb.sisride.dao;

import com.ifpb.sisride.factory.ConFactory;
import com.ifpb.sisride.modelo.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroDao implements Dao<Carro> {

    private Connection con;

    public CarroDao() throws ClassNotFoundException, SQLException {
        ConFactory factory = new ConFactory();
        con = factory.getConnection();
    }

    @Override
    public boolean salvar(Carro obj) throws SQLException {
        String sql = "INSERT INTO Carro (modelo,arcondicionado,ano,codigo) VALUES (?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, obj.getModelo());
        stmt.setBoolean(2, obj.isAr_condicionado());
        stmt.setInt(3, obj.getAno());
        stmt.setInt(4, obj.getCodigo());
        return stmt.execute();
    }

    @Override
    public Carro buscar(Object obj) throws SQLException {
        String sql = "SELECT * FROM carro WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        ResultSet resultado = stmt.executeQuery();
        if (resultado.next()) {
            Carro carro = new Carro(resultado.getString("modelo"),
                    resultado.getInt("ano"), resultado.getBoolean("arcondicionado"),
                    resultado.getInt("codigo"));
            return carro;
        }
        return null;
    }

    @Override
    public boolean atualizar(Carro obj) throws SQLException {
        if (buscar(obj.getCodigo()) != null) {
            String sql = "UPDATE Carro Set modelo=?, ano = ?, arcondicionado=? WHERE codigo =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getModelo());
            stmt.setInt(2, obj.getAno());
            stmt.setBoolean(3, obj.isAr_condicionado());
            stmt.setInt(4, obj.getCodigo());
            return stmt.execute();
        }
        return false;
    }

    @Override
    public boolean deletar(Object obj) throws SQLException {
        String sql = "DELETE FROM Carro WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, (int) obj);
        return stmt.execute();
    }
}