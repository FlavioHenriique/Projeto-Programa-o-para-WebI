package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorAvaliacao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelaAvaliacao implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    
        try {
            GerenciadorAvaliacao g = new GerenciadorAvaliacao();
            System.out.println(request.getParameter("codigo"));
            g.removeAvaliacao(Integer.parseInt(request.getParameter("codigo")));
                
            response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=4");
        } catch (SQLException ex) {
            Logger.getLogger(CancelaAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CancelaAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CancelaAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
