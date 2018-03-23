package com.ifpb.sisride.command;


import com.ifpb.sisride.command.Command;
import com.ifpb.sisride.controle.GerenciadorAvaliacao;
import com.ifpb.sisride.modelo.Avaliacao;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Avaliacoes implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        
    }

    public static List<Avaliacao> avaliacoesUsuario(String avaliado) throws SQLException, ClassNotFoundException {

        GerenciadorAvaliacao g = new GerenciadorAvaliacao();
        return g.avaliacoesUsuario(avaliado);
    }

}
