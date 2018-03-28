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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AtualizaViagem implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
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
           

            g.atualizaViagem(Integer.parseInt(request.getParameter("vagas")), LocalDate.parse(request.getParameter("data")),
                    request.getParameter("hora"), Float.parseFloat(request.getParameter("valor")), u, request.getParameter("musica"),
                    Boolean.parseBoolean(request.getParameter("animais")), Boolean.parseBoolean(request.getParameter("fumar")), request.getParameter("conversa"),
                    destino, partida, carro, Integer.parseInt(request.getParameter("codigo")));
            
            response.sendRedirect("front?command=Caronas&mensagem=7");

        } catch (SQLException ex) {
            Logger.getLogger(AtualizaViagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtualizaViagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AtualizaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
