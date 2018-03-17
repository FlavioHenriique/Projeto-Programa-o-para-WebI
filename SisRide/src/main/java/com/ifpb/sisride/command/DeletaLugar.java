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
import javax.servlet.http.HttpSession;

public class DeletaLugar implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        try {
            GerenciadorLugar gerenciador = new GerenciadorLugar();
            gerenciador.removeLugar(Integer.parseInt(request.getParameter("IdLugar")));
            HttpSession session = request.getSession();
            Usuario u = (Usuario) session.getAttribute("usuario");
            AtualizaLugares.execute(request,u.getEmail());
            
            response.sendRedirect("lugar.jsp?mensagem=2");
        } catch (SQLException ex) {
            Logger.getLogger(DeletaLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeletaLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeletaLugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
