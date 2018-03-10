package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
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

public class BuscaUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorUsuario gerenciador = new GerenciadorUsuario();

            Usuario buscado = gerenciador.buscarNome(request.getParameter("buscado"));

            request.setAttribute("buscado", buscado);
            response.setCharacterEncoding("utf-8");
            if (buscado == null) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("inicial.jsp");
                dispatcher.forward(request, response);

            } else {
                System.out.println(buscado.toString());
                RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
