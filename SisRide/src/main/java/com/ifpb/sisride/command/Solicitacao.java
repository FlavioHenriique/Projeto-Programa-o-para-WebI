package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Solicitacao implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorUsuario g = new GerenciadorUsuario();

            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Usuario amigo = (Usuario) session.getAttribute("buscado");

            g.solicitacao(usuario.getEmail(), amigo.getEmail(),request.getParameter("tipo"));
            
            response.sendRedirect("usuario.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(Solicitacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Solicitacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Solicitacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
