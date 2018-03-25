package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorViagem;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelaSolicitacao implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
         HttpSession session = request.getSession();
        Usuario atual = (Usuario) session.getAttribute("usuario");

        try {
            GerenciadorViagem g = new GerenciadorViagem();
            
            g.cancelaSolicitacao(Integer.parseInt(request.getParameter("carona")), atual.getEmail());
            
            response.sendRedirect("front?command=DadosUsuario&email="+atual.getEmail()+"&mensagem=3");
        } catch (SQLException ex) {
            Logger.getLogger(CancelaSolicitacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CancelaSolicitacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CancelaSolicitacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
