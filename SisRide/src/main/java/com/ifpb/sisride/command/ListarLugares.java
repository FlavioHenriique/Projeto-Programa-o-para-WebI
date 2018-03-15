package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.modelo.Lugar;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListarLugares implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorLugar g = new GerenciadorLugar();
            List<Lugar> lista = g.buscaLugar();
            HttpSession session = request.getSession();
            session.setAttribute("lugares", lista);
            response.sendRedirect("caronas.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ListarLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListarLugares.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
