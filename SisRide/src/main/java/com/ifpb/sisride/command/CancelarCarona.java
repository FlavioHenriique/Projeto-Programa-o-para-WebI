package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorViagem;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelarCarona implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorViagem gerenciador = new GerenciadorViagem();
            gerenciador.removeViagem( Integer.parseInt(request.getParameter("codCarona")));
            
            request.setAttribute("mensagem","3");
            
            ListarLugares listar = new ListarLugares();
            listar.execute(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CancelarCarona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CancelarCarona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
