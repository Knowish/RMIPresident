package fr.univnantes.impl;

import fr.univnantes.impl.Card;
import fr.univnantes.rmi.inter.PlayerInterface;

import java.rmi.RemoteException;
import java.util.concurrent.Exchanger;

public class ExchangerRunnable implements Runnable {
    private Exchanger<Card> exchanger = null;
    private PlayerInterface player    = null;
    private int winOrder;
    private Card cardObtained;

    public ExchangerRunnable(Exchanger<Card> exchanger, PlayerInterface player, int winOrder) {
        this.exchanger = exchanger;
        this.player = player;
        this.winOrder = winOrder;
    }

    @Override
    public void run() {
        try {
            switch (winOrder){
                case 1 :
                    for (int i=0; i<2; ++i){
                        exchange();
                    }
                    break;

                case 4 :
                    for (int i=0; i<2; ++i){
                        exchange();
                    }
                default:
                    exchange();
            }
        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void exchange() throws RemoteException, InterruptedException {
        Card cardToExchange = player.exchangeCard(winOrder);

        this.cardObtained = this.exchanger.exchange(cardToExchange);

        player.removeCardFromHand(cardToExchange);
        player.addToHand(cardObtained);
        player.updateHandView();
        System.out.println(
                player.getUserName() +
                        " exchanged " + cardToExchange.getName() + " for " + cardObtained.getName()
        );
    }
}
