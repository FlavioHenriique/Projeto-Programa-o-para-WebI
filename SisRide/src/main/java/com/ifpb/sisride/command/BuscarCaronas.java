package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorViagem;
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

public class BuscarCaronas implements Command {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        try {
            GerenciadorViagem gerenciador = new GerenciadorViagem();
            List<Viagem> lista = gerenciador.buscaNome(request.getParameter("nomeLugar"));
           
            request.setAttribute("buscaViagens", lista);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("solicitacoes.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(BuscarCaronas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscarCaronas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscarCaronas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
