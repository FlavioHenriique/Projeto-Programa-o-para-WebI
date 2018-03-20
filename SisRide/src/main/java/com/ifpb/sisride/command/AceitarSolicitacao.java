package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorSolicitacao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AceitarSolicitacao implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            GerenciadorSolicitacao g = new GerenciadorSolicitacao();
            g.aceitarSolicitacao(request.getParameter("solicitador"), request.getParameter("requisitado"),
                    request.getParameter("tipoSolicitacao"));
                    
            
            response.sendRedirect("front?command=ListarSolicitacoes");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AceitarSolicitacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AceitarSolicitacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
