package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorViagem;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SolicitaVaga implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");

        try {
            GerenciadorViagem gerenciador = new GerenciadorViagem();
            gerenciador.solicitaVaga(atual.getEmail(), Integer.parseInt(request.getParameter("codViagem")));

            if (request.getParameter("pagina").equals("inicial")) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("front?command=DadosUsuario&email="+atual.getEmail()+"&mensagem=2");
                dispatcher.forward(request, response);
            } else {

                RequestDispatcher dispatcher = request.getRequestDispatcher("solicitacoes.jsp?mensagem=1");
                dispatcher.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitaVaga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitaVaga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SolicitaVaga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
