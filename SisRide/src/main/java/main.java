import com.ifpb.sisride.dao.AvaliacaoDao;
import com.ifpb.sisride.dao.CarroDao;
import com.ifpb.sisride.dao.LugarDao;
import com.ifpb.sisride.dao.ViagemDao;
import com.ifpb.sisride.modelo.Avaliacao;
import com.ifpb.sisride.modelo.Carro;
import com.ifpb.sisride.modelo.Lugar;
import com.ifpb.sisride.modelo.Viagem;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String[] args) {
        Viagem v = new Viagem(2,LocalDate.now(),"10:10",10,"flavio@gmail.com","música do cão",false,false,"baixo",1,3,1,1);
        
        try {
            ViagemDao dao = new ViagemDao();
            dao.atualizar(v);
            System.out.println(dao.buscar(1).toString());
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }

}
