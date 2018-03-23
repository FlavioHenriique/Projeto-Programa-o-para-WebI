package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorAvaliacao;
import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AvaliarUsuario implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");

        try {
            if (request.getParameter("tipo").equals("motorista") && request.getParameter("nota") == "") {
                System.out.println("motorista");
                response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=2");
                
            } else if ((request.getParameter("tipo").equals("passageiro")
                    && request.getParameter("nota") == "") || request.getParameter("avaliado") == null) {
                
                response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=2");

            } else {
                    GerenciadorUsuario gUser = new GerenciadorUsuario();
                    Usuario avaliado = gUser.buscaUsuario(request.getParameter("avaliado"));
                    
                GerenciadorAvaliacao gerenciador = new GerenciadorAvaliacao();
                gerenciador.adicionaAvaliacao(request.getParameter("comentario"),
                        Float.parseFloat(request.getParameter("nota")), avaliado,
                        atual, request.getParameter("tipo"));

                response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvaliarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvaliarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AvaliarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
