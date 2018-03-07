<<<<<<< HEAD
package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        GerenciadorUsuario g;
        try {
            g = new GerenciadorUsuario();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            if (g.adicionaUsuario(request.getParameter("email"), request.getParameter("senha"),
                    request.getParameter("nome"), LocalDate.parse(request.getParameter("data"), formatter),
                    request.getParameter("profissao"),
                    request.getParameter("cidade"), request.getParameter("sexo"))) {
                response.sendRedirect("index.html");
            }else{
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
=======
package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        GerenciadorUsuario g;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        if(request.getParameter("email") == "" || request.getParameter("senha")=="" || request.getParameter(name))
        try {
            g = new GerenciadorUsuario();
            
            g.adicionaUsuario(request.getParameter("email"), request.getParameter("senha"),
                    request.getParameter("nome"), LocalDate.parse(request.getParameter("data"), formatter),
                    request.getParameter("profissao"),
                    request.getParameter("cidade"), request.getParameter("sexo"));

        } catch (SQLException ex) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {

        }

    }

}
>>>>>>> a7f40b15b5d6b264b8b16c0ee2621ee2e9a4916b
