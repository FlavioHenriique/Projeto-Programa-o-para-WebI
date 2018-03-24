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

public class CancelaAvaliacaoPassageiro implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        HttpSession session = request.getSession();
        List<Avaliacao> minhasAvaliacoes = (List<Avaliacao>) session.getAttribute("minhasAvaliacoes");

        boolean cancelar = false;

        for (Avaliacao a : minhasAvaliacoes) {
            if (request.getParameter("avaliador").equals(a.getAvaliador().getEmail())
                    && a.getUsuarioAvaliado().getEmail().equals(request.getParameter("avaliado"))
                    && a.getTipo().equals(request.getParameter("tipo"))
                    && a.getViagem() == Integer.parseInt(request.getParameter("viagem"))) {

                cancelar = true;
            }
        }
        try {
            if (!cancelar) {
                response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=5");
            } else {
                GerenciadorAvaliacao g = new GerenciadorAvaliacao();
                int codigo = g.getCodigo(request.getParameter("avaliado"), request.getParameter("avaliador"),
                        request.getParameter("tipo"), Integer.parseInt(request.getParameter("viagem")));
                g.removeAvaliacao(codigo);
                
                response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=4");
            }
        } catch (IOException ex) {
            Logger.getLogger(CancelaAvaliacaoPassageiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CancelaAvaliacaoPassageiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CancelaAvaliacaoPassageiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
