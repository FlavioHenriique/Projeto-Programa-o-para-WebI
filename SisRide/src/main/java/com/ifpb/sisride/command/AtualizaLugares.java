package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.modelo.Lugar;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AtualizaLugares {

    public static void execute(HttpServletRequest request, String email) throws
            ServletException, SQLException,
            ClassNotFoundException {
        HttpSession session = request.getSession();

        GerenciadorLugar gLugar = new GerenciadorLugar();
        
      
        List<Lugar> lugares = gLugar.buscaLugares(email);
        session.setAttribute("MeusLugares", lugares);
    }
}
