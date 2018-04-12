package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtualizaLugar implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    
        try {
            GerenciadorLugar gLugar = new GerenciadorLugar();
         
                    
            gLugar.atualizaLugar(request.getParameter("descricao"),request.getParameter("nome"),request.getParameter("rua"),
                    request.getParameter("cidade"),request.getParameter("estado"),Integer.parseInt(request.getParameter("numero")),
                    request.getParameter("email"), Integer.parseInt(request.getParameter("id")));
            
            AtualizaLugares.execute(request, request.getParameter("email"));
            
            response.sendRedirect("lugar.jsp?mensagem=3");
        } catch (SQLException ex) {
            Logger.getLogger(AtualizaLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtualizaLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AtualizaLugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
