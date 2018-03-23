package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorAvaliacao;
import com.ifpb.sisride.controle.GerenciadorSolicitacao;
import com.ifpb.sisride.controle.GerenciadorUsuario;
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

public class DadosUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            HttpSession session = request.getSession();
            GerenciadorViagem gerenciador = new GerenciadorViagem();
            GerenciadorUsuario user = new GerenciadorUsuario();
            GerenciadorAvaliacao gAvaliacao = new GerenciadorAvaliacao();
            request = ListarAmigos.listar(request);

            request.setAttribute("recomendacoes", gerenciador.getRecomendacoes(request.getParameter("email")));
            request.setAttribute("notificacoes", user.getNotificacoes(request.getParameter("email")));

            request.setAttribute("avaliacoesUsuario", gAvaliacao.avaliacoesUsuario(request.getParameter("email")));

            session.setAttribute("caronasSolicitadas", gerenciador.caronasSolicitadas(request.getParameter("email")));
            RequestDispatcher dispatcher = request.getRequestDispatcher("inicial.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(DadosUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DadosUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DadosUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
