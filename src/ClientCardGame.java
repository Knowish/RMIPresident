import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Calendar;

public class ClientCardGame {

    public static void main(String[] argv){

        try{

            Cardgame cardgame = (Cardgame) Naming.lookup(
                    "//localhost:8080/serveurCardGame");
            System.out.println(cardgame.affiche());
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
