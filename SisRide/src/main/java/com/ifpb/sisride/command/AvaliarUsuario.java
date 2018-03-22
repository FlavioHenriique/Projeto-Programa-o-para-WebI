package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorAvaliacao;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AvaliarUsuario implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");
        
        try {
            GerenciadorAvaliacao gerenciador = new GerenciadorAvaliacao();
            gerenciador.adicionaAvaliacao(request.getParameter("comentario"),
                    Float.parseFloat(request.getParameter("nota")), request.getParameter("passageiro"),
                    atual.getEmail(), request.getParameter("tipo"));
            
            response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=1");
            
        } catch (SQLException ex) {
            Logger.getLogger(AvaliarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AvaliarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
