package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorViagem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SoElas implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    
        try {
            GerenciadorViagem g = new GerenciadorViagem();
            
            request.setAttribute("buscaViagens", g.soElas());
            RequestDispatcher dispatcher = request.getRequestDispatcher("solicitacoes.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(SoElas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SoElas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoElas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
