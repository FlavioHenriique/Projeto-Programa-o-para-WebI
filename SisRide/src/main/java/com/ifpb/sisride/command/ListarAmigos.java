package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorSolicitacao;
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

public class ListarAmigos implements Command {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");
        try {
            GerenciadorSolicitacao gerenciador = new GerenciadorSolicitacao();
            
            session.setAttribute("amigos", gerenciador.listarAmigos(atual.getEmail()));
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("amizades.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ListarAmigos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarAmigos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListarAmigos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
