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

public class AtualizarAvaliacao implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    
        try {
            GerenciadorAvaliacao g = new GerenciadorAvaliacao();
            HttpSession session = request.getSession();
            Usuario atual = (Usuario) session.getAttribute("usuario");
            GerenciadorUsuario gUser = new GerenciadorUsuario();
            Usuario avaliado = gUser.buscaUsuario(request.getParameter("avaliado"));
            
            g.atualizaAvaliacao(request.getParameter("comentario"), Float.parseFloat(request.getParameter("nota")),
                    avaliado, atual, Integer.parseInt(request.getParameter("codigo")), request.getParameter("tipo"));
            
            response.sendRedirect("front?command=PaginaAvaliacoes&mensagem=3");
            
        } catch (SQLException ex) {
            Logger.getLogger(AtualizarAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtualizarAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AtualizarAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
