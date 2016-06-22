
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

public class CadastroLugar implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        try {
            GerenciadorLugar gerenciador = new GerenciadorLugar();
            
            HttpSession session = request.getSession();
            Usuario atual = (Usuario) session.getAttribute("usuario");
            
            gerenciador.adicionaLugar(request.getParameter("descricao"), request.getParameter("nome"),
                    request.getParameter("rua"), request.getParameter("cidade"), request.getParameter("estado"),
                    Integer.parseInt(request.getParameter("numero")),atual.getEmail());
            
            response.sendRedirect("lugar.jsp");
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroLugar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CadastroLugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
