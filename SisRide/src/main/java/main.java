import com.ifpb.sisride.dao.AvaliacaoDao;
import com.ifpb.sisride.dao.CarroDao;
import com.ifpb.sisride.dao.LugarDao;
import com.ifpb.sisride.modelo.Avaliacao;
import com.ifpb.sisride.modelo.Carro;
import com.ifpb.sisride.modelo.Lugar;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String[] args) {
       Avaliacao a = new Avaliacao ("bais ","11:10",LocalDate.now(),10,
               "leo@gmail.com","flavio@gmail.com",1);
       
        try {
            AvaliacaoDao dao = new AvaliacaoDao();
            dao.atualizar(a);
            System.out.println(dao.buscar(2).getComentario());
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }

}
