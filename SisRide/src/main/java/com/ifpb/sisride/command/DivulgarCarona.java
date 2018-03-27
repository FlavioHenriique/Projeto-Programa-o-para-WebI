package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorViagem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DivulgarCarona implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorViagem g = new GerenciadorViagem();
            HttpSession session = request.getSession();
            
            g.divulgarCarona(Integer.parseInt(request.getParameter("carona")));
            
            session.setAttribute("divulgadas",g.buscarTodas());
            
            response.sendRedirect("front?command=Caronas&mensagem=6");

        } catch (SQLException ex) {
            Logger.getLogger(DivulgarCarona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DivulgarCarona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DivulgarCarona.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
