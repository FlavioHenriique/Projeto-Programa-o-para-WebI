package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorSolicitacao;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviarMensagem implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            Usuario atual = (Usuario) request.getSession().getAttribute("usuario");

            GerenciadorSolicitacao g = new GerenciadorSolicitacao();
            g.enviarMensagem(atual.getEmail(), request.getParameter("amigo"),
                    request.getParameter("mensagem"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("front?command=ListarAmigos");
            dispatcher.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(EnviarMensagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnviarMensagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EnviarMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
