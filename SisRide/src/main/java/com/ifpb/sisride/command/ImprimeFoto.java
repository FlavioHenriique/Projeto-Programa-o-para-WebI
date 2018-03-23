package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImprimeFoto implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        List<Usuario> amigos = (List<Usuario>) session.getAttribute("amigos");
        System.out.println("entrou");
        if (request.getParameter("avaliador") != "") {
            try {
                imprimir(request.getParameter("avaliador"), response);
            } catch (SQLException ex) {
                Logger.getLogger(ImprimeFoto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ImprimeFoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            for (Usuario u : amigos) {
                if (u.getEmail().equals(request.getParameter("amigo"))) {
                    try {
                        response.getOutputStream().write(u.getFoto2());
                    } catch (IOException ex) {
                        Logger.getLogger(ImprimeFoto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void imprimir(String usuario, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        
        GerenciadorUsuario g = new GerenciadorUsuario();
        Usuario u = g.buscaUsuario(usuario);
        System.out.println(usuario);

        try {
            response.getOutputStream().write(u.getFoto2());
        } catch (IOException ex) {
            Logger.getLogger(ImprimeFoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
