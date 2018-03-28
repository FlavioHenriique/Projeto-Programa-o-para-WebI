package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorSolicitacao;
import com.ifpb.sisride.controle.GerenciadorUsuario;
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
            request = listar(request);
            
            if (request.getParameter("amigo") != null) {
                GerenciadorSolicitacao g = new GerenciadorSolicitacao();
                GerenciadorUsuario gUser = new GerenciadorUsuario();    
                
                request.setAttribute("mensagens", g.getMensagens(atual.getEmail(),
                        request.getParameter("amigo")));
                
                request.setAttribute("amigo", gUser.buscaUsuario(request.getParameter("amigo")));
            }
            
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
    
    public static HttpServletRequest listar(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        
        HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");
        
        GerenciadorSolicitacao gerenciador = new GerenciadorSolicitacao();
        
        session.setAttribute("amigos", gerenciador.listarAmigos(atual.getEmail()));
        
        return request;
    }
    
}
