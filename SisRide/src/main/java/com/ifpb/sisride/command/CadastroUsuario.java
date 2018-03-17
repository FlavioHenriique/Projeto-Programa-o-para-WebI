package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.exception.CadastroException;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class CadastroUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        GerenciadorUsuario g;
        try {
            g = new GerenciadorUsuario();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Part part = request.getPart("foto");
            InputStream input = part.getInputStream();

            if (request.getParameter("email") == null || request.getParameter("senha") == null
                    || request.getParameter("nome") == null || request.getParameter("data") == null
                    || request.getParameter("profissao") == null || request.getParameter("cidade") == null
                    || request.getParameter("sexo") == null) {
                
                response.sendRedirect("CadastroUsuario.jsp?erroCadastroUsuario=1");
                
            } else if (g.buscaUsuario(request.getParameter("email")) != null) {

                response.sendRedirect("CadastroUsuario.jsp?erroCadastroUsuario=2");

            } else if (g.adicionaUsuario(request.getParameter("email"), request.getParameter("senha"),
                    request.getParameter("nome"), LocalDate.parse(request.getParameter("data"), formatter),
                    request.getParameter("profissao"), request.getParameter("cidade"),
                    request.getParameter("sexo"), input)) {

                response.sendRedirect("index.jsp");

            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CadastroException ex) {

        }
    }

}
