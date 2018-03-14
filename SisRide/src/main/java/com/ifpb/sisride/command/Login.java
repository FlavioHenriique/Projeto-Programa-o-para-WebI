package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.exception.AutenticacaoException;
import com.ifpb.sisride.modelo.Lugar;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login implements Command {

    public Login() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        try {
            GerenciadorUsuario g = new GerenciadorUsuario();
            GerenciadorLugar gLugar = new GerenciadorLugar();
            HttpSession session = request.getSession();
            Usuario u = (Usuario) session.getAttribute("usuario");

            if (u != null) {
                response.sendRedirect("inicial.jsp");
            } else if (g.autenticar(request.getParameter("email"), request.getParameter("senha"))) {

                Usuario atual = g.buscaUsuario(request.getParameter("email"));
                session.setAttribute("usuario", atual);

                List<Lugar> lugares = gLugar.buscaLugares(atual.getEmail());
                session.setAttribute("lugares", lugares);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("inicial.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
