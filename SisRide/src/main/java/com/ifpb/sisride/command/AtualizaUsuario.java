package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AtualizaUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorUsuario g = new GerenciadorUsuario();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            HttpSession session = request.getSession();
            Usuario u = (Usuario) session.getAttribute("usuario");

            if(g.atualizaUsuario(u.getEmail(), request.getParameter("senha"),
                    request.getParameter("nome"), LocalDate.parse(request.getParameter("data"), formatter),
                    request.getParameter("profissao"), request.getParameter("cidade"), request.getParameter("sexo"))){
            
                session.setAttribute("usuario", g.buscaUsuario(u.getEmail()));
                
                response.sendRedirect("inicial.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtualizaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtualizaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AtualizaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
