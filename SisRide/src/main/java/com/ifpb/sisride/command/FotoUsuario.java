package com.ifpb.sisride.command;

import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FotoUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        try {
            response.getOutputStream().write(u.getFoto2());
        } catch (IOException ex) {
            Logger.getLogger(FotoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
