import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TestServeurCardGame {

    public static void main(String[] argv){

        try{

            LocateRegistry.createRegistry(8080);
            ServeurCardGame serveurCardGame = new ServeurCardGame();
            Naming.bind("//localhost:8080/serveurCardGame",serveurCardGame);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
