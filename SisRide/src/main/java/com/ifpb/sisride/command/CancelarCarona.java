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

public class CancelarCarona implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorViagem gerenciador = new GerenciadorViagem();
            gerenciador.removeViagem(Integer.parseInt(request.getParameter("codCarona")));

            request.setAttribute("mensagem", "3");
            HttpSession session = request.getSession();
            Usuario atual = (Usuario) session.getAttribute("usuario");

            session.setAttribute("minhasCaronas", Caronas.minhasCaronas(atual.getEmail()));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CancelarCarona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CancelarCarona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
