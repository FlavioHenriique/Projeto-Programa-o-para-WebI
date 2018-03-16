package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorSolicitacao;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListarSolicitacoes implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {

            HttpSession session = request.getSession();
            Usuario atual = (Usuario) session.getAttribute("usuario");
            listar(session, atual);

            response.sendRedirect("solicitacoes.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ListarSolicitacoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarSolicitacoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListarSolicitacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listar(HttpSession session, Usuario u) throws SQLException, ClassNotFoundException {

        GerenciadorSolicitacao g = new GerenciadorSolicitacao();
        session.setAttribute("solicitacoes", g.listarSolicitacoes(u));
    }

}
