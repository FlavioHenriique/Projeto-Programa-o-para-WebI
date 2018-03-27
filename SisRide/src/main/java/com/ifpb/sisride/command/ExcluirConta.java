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

public class ExcluirConta implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorUsuario g = new GerenciadorUsuario();

            HttpSession session = request.getSession();

            Usuario atual = (Usuario) session.getAttribute("usuario");
            g.removeUsuario(atual.getEmail());

            session.removeAttribute("usuario");

            response.sendRedirect("index.jsp?mensagem=1");

        } catch (SQLException ex) {
            Logger.getLogger(ExcluirConta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExcluirConta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcluirConta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
