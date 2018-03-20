package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.controle.GerenciadorViagem;
import com.ifpb.sisride.modelo.Lugar;
import com.ifpb.sisride.modelo.Usuario;
import com.ifpb.sisride.modelo.Viagem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListarLugares implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            GerenciadorLugar g = new GerenciadorLugar();
                    
            List<Lugar> lista = g.buscaLugar();
            
            HttpSession session = request.getSession();
            Usuario u = (Usuario) session.getAttribute("usuario");
            
            
            session.setAttribute("lugares", lista);

            minhasCaronas(request, response,u.getEmail());

        } catch (SQLException ex) {
            Logger.getLogger(ListarLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListarLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListarLugares.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void minhasCaronas(HttpServletRequest request, HttpServletResponse response, String email) throws SQLException,
            ClassNotFoundException,
            ServletException,
            IOException {

        GerenciadorViagem gerenciador = new GerenciadorViagem();

        List<Viagem> caronas = gerenciador.minhasCaronas(email);

        request.setAttribute("minhasCaronas", caronas);
        RequestDispatcher dispatcher;

        if ((String) request.getAttribute("mensagem") != null) {
            dispatcher = request.getRequestDispatcher("caronas.jsp?mensagem="
                    + request.getAttribute("mensagem"));
        } else {
            dispatcher = request.getRequestDispatcher("caronas.jsp");
        }
        dispatcher.forward(request, response);
    }

}
