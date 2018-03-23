package com.ifpb.sisride.command;

import com.ifpb.sisride.controle.GerenciadorCarro;
import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.controle.GerenciadorViagem;
import com.ifpb.sisride.modelo.Carro;
import com.ifpb.sisride.modelo.Lugar;
import com.ifpb.sisride.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastroViagem implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {

            if (request.getParameter("vagas") == null || request.getParameter("data") == null
                    || request.getParameter("hora") == null || request.getParameter("valor") == null
                    || request.getParameter("musica") == null || request.getParameter("animal") == null
                    || request.getParameter("fumar") == null || request.getParameter("conversa") == null
                    || request.getParameter("partida") == null || request.getParameter("destino") == null
                    || request.getParameter("ar") == null || request.getParameter("modelo") == null
                    || request.getParameter("ano") == null) {

                response.sendRedirect("caronas.jsp?erroCadastroCarona=1");
            } else {

                GerenciadorViagem g = new GerenciadorViagem();
                GerenciadorCarro gCarro = new GerenciadorCarro();
                GerenciadorLugar gLugar = new GerenciadorLugar();

                Carro carro = new Carro();
                carro.setAno(request.getParameter("ano"));
                carro.setAr_condicionado(Boolean.parseBoolean(request.getParameter("ar")));
                carro.setModelo(request.getParameter("modelo"));

                int codCarro = gCarro.adicionaCarro(request.getParameter("modelo"), request.getParameter("ano"),
                        Boolean.parseBoolean(request.getParameter("ar")));
                carro.setCodigo(codCarro);
                HttpSession session = request.getSession();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                Usuario u = (Usuario) session.getAttribute("usuario");

                Lugar partida = null;
                Lugar destino = null;
                List<Lugar> lista = gLugar.buscaLugar();
                for (Lugar l : lista) {
                    if (l.getIdentificacao() == Integer.parseInt(request.getParameter("partida"))) {
                        partida = l;
                    }
                    if (l.getIdentificacao() == Integer.parseInt(request.getParameter("destino"))) {
                        destino = l;
                    }
                }
                g.adicionaViagem(Integer.parseInt(request.getParameter("vagas")), LocalDate.parse(request.getParameter("data"), formatter),
                        request.getParameter("hora"), Float.parseFloat(request.getParameter("valor")), u, request.getParameter("musica"),
                        Boolean.parseBoolean(request.getParameter("animais")), Boolean.parseBoolean(request.getParameter("fumar")), request.getParameter("conversa"),
                        destino, partida, carro);

                response.sendRedirect("front?command=Caronas&mensagem=1");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroViagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroViagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CadastroViagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

