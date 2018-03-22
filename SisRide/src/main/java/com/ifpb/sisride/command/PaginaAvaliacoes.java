package com.ifpb.sisride.command;

import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaginaAvaliacoes implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");

        try {
            session.setAttribute("caronasRealizadas", Caronas.getCaronasRealizadas
                    (Caronas.minhasCaronas(atual.getEmail())));
                    
               RequestDispatcher dispatcher = request.getRequestDispatcher("avaliacao.jsp");
               dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaginaAvaliacoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaginaAvaliacoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PaginaAvaliacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
