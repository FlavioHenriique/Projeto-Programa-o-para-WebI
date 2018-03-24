package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorAvaliacao;
import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.modelo.Avaliacao;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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

                response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=2");

            } else if ((request.getParameter("tipo").equals("passageiro")
                    && request.getParameter("nota") == "") || request.getParameter("avaliado") == null) {

                response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=2");

            } else {
                List<Avaliacao> minhasAvaliacoes = (List<Avaliacao>) session.getAttribute("minhasAvaliacoes");

                boolean atualizar = false;

                for (Avaliacao a : minhasAvaliacoes) {
                    if (atual.getEmail().equals(a.getAvaliador().getEmail())
                            && a.getUsuarioAvaliado().getEmail().equals(request.getParameter("avaliado"))
                            && a.getTipo().equals(request.getParameter("tipo"))
                            && a.getViagem() == Integer.parseInt(request.getParameter("viagem"))) {

                        AtualizarAvaliacao atualiza = new AtualizarAvaliacao();
                        atualiza.execute(request, response);
                        atualizar = true;
                    }
                }
                if (!atualizar) {

                    GerenciadorUsuario gUser = new GerenciadorUsuario();
                    Usuario avaliado = gUser.buscaUsuario(request.getParameter("avaliado"));

                    GerenciadorAvaliacao gerenciador = new GerenciadorAvaliacao();
                    gerenciador.adicionaAvaliacao(request.getParameter("comentario"),
                            Float.parseFloat(request.getParameter("nota")), avaliado,
                            atual, request.getParameter("tipo"), Integer.parseInt(request.getParameter("viagem")));

                    response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=1");
                }
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
