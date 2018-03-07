package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.exception.AutenticacaoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login implements Command {

    public Login() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        try {
          
            GerenciadorUsuario g = new GerenciadorUsuario();
            if(g.autenticar(request.getParameter("email"), request.getParameter("senha"))){
              response.sendRedirect("inicial.jsp");
            }
            else{
                response.sendRedirect("index.html");
            }
        } catch (SQLException | ClassNotFoundException | IOException  ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
