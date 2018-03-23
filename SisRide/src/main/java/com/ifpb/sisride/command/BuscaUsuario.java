package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorUsuario;
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

public class BuscaUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorUsuario gerenciador = new GerenciadorUsuario();

            Usuario buscado = gerenciador.buscarNome(request.getParameter("buscado"));

            HttpSession session = request.getSession();
            session.setAttribute("buscado", buscado);

            response.setCharacterEncoding("utf-8");

            if (buscado == null) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("inicial.jsp?erro=1");
                dispatcher.forward(request, response);

            } else {

                ListarSolicitacoes listar = new ListarSolicitacoes();
                listar.listar(session, (Usuario) session.getAttribute("usuario"));
                
                request.setAttribute("avaliacoesBuscado", Avaliacoes.avaliacoesUsuario(buscado.getEmail()));

                RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
