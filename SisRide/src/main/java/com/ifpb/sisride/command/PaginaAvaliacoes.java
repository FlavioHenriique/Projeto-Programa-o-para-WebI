package com.ifpb.sisride.command;

import com.ifpb.sisride.modelo.Usuario;
import com.ifpb.sisride.modelo.Viagem;
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

public class PaginaAvaliacoes implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");

        try {
            session.setAttribute("avaliacao_motorista", Caronas.getCaronasRealizadas
                    (Caronas.minhasCaronas(atual.getEmail())));
                    
            session.setAttribute("avaliacao_passageiro", Caronas.getCaronasRealizadas
                    ((List<Viagem>) session.getAttribute("caronasSolicitadas")));
            
            session.setAttribute("minhasAvaliacoes", Avaliacoes.minhasAvaliacoes(atual.getEmail()));
            
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
