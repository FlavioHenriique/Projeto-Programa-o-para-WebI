package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
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
            List<Usuario> usuarios = gerenciador.buscarNome(request.getParameter("buscado"));
            System.out.println(usuarios.toString());
            request.setAttribute("buscado", usuarios);
            response.setCharacterEncoding("utf-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("teste.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
