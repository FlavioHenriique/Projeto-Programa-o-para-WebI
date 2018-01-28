import com.ifpb.sisride.controle.GerenciadorAvaliacao;
import com.ifpb.sisride.controle.GerenciadorCarro;
import com.ifpb.sisride.controle.GerenciadorLugar;
import com.ifpb.sisride.controle.GerenciadorUsuario;
import com.ifpb.sisride.controle.GerenciadorViagem;
import com.ifpb.sisride.dao.AvaliacaoDao;
import com.ifpb.sisride.dao.CarroDao;
import com.ifpb.sisride.dao.LugarDao;
import com.ifpb.sisride.dao.ViagemDao;
import com.ifpb.sisride.modelo.Avaliacao;
import com.ifpb.sisride.modelo.Carro;
import com.ifpb.sisride.modelo.Lugar;
import com.ifpb.sisride.modelo.Usuario;
import com.ifpb.sisride.modelo.Viagem;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String[] args) {

        
        try {
            GerenciadorAvaliacao g = new GerenciadorAvaliacao();
            g.removeAvaliacao(10);
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
