package com.ifpb.sisride.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sair implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        try {
            
            HttpSession session = request.getSession();
            session.removeAttribute("usuario");
 
            response.sendRedirect("index.html");
            
        } catch (IOException ex) {
            Logger.getLogger(Sair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
