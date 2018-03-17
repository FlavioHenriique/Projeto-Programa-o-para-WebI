package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastroLugar implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorLugar gerenciador = new GerenciadorLugar();

            HttpSession session = request.getSession();
            Usuario atual = (Usuario) session.getAttribute("usuario");

            if (request.getParameter("descricao") == null || request.getParameter("nome") == null
                    || request.getParameter("rua") == null || request.getParameter("estado") == null
                    || request.getParameter("cidade") == null) {

                response.sendRedirect("lugar.jsp?erroCadastroLugar=1");
            } else {
                gerenciador.adicionaLugar(request.getParameter("descricao"), request.getParameter("nome"),
                        request.getParameter("rua"), request.getParameter("cidade"), request.getParameter("estado"),
                        Integer.parseInt(request.getParameter("numero")), atual.getEmail());

                AtualizaLugares.execute(request, atual.getEmail());

                response.sendRedirect("lugar.jsp?mensagem=1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CadastroLugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
