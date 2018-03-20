package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorViagem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmaVaga implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorViagem gerenciador = new GerenciadorViagem();
            gerenciador.confirmaVaga(request.getParameter("solicitante"),
              Integer.parseInt(request.getParameter("codviagem")), request.getParameter("resposta"));
            
            response.sendRedirect("front?command=ListarLugares&mensagem=4");
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmaVaga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfirmaVaga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfirmaVaga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
