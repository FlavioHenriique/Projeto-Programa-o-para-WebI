package com.ifpb.sisride.command;

import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FotoBuscado implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Usuario u = (Usuario) request.getAttribute("buscado");

        try {
            response.getOutputStream().write(u.getFoto2());
        } catch (IOException ex) {
            Logger.getLogger(FotoBuscado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
