package com.ifpb.sisride.command;

import com.ifpb.sisride.modelo.Usuario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FotoBuscado implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        Usuario buscado = (Usuario) session.getAttribute("buscado");
        try {
           response.getOutputStream().write(buscado.getFoto2());
            
        } catch (IOException ex) {
            Logger.getLogger(FotoBuscado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
