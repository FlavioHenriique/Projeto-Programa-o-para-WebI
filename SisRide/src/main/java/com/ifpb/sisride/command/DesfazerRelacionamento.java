package com.ifpb.sisride.command;

import com.ifpb.sisride.modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DesfazerRelacionamento implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
    }
    
    
    
}
