package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        System.out.println(request.getParameter("sexo"));
        GerenciadorUsuario g;
        try {
            g = new GerenciadorUsuario();
           /*
            g.adicionaUsuario(request.getParameter("email"), request.getParameter("senha"),
            request.getParameter("nome"), LocalDate.MIN, request.getParameter("profissao"), 
            request.getParameter("cidade"), request.getParameter("sexo"));
*/
    
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
