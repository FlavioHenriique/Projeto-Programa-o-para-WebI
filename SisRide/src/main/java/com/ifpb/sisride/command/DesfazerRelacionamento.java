package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorSolicitacao;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DesfazerRelacionamento implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        Usuario usuario1 = (Usuario) session.getAttribute("usuario");
        Usuario usuario2 = (Usuario) session.getAttribute("buscado");
        
        try {
            GerenciadorSolicitacao gerenciador = new GerenciadorSolicitacao();
            response.sendRedirect("front?command=BuscaUsuario&buscado="+usuario2.getNome());
            
            gerenciador.desfazerRelacionamento(usuario1.getEmail(), usuario2.getEmail(),request.getParameter("tipo"));
            
        } catch (SQLException ex) {
            Logger.getLogger(DesfazerRelacionamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DesfazerRelacionamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DesfazerRelacionamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
