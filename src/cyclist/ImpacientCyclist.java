package cyclist;

import java.time.LocalTime;
import java.util.concurrent.Phaser;

public class ImpacientCyclist extends Cyclist {

    public ImpacientCyclist(String name, Phaser phaser) {
        super(name, phaser);
    }

    @Override
    public void run() {
        phaser.register();
        try {
            goOutHome();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: tuvo un accidente de camino a la gasolinera, y jamás volvió a saberse " +
                            "de él. Descanse en paz.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
            return;
        }
        System.out.printf("%s -> %s: Chavales, que sois muy lentos, voy a ir por mi cuenta cuando salgamos de la gasolinera.\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
        phaser.awaitAdvance(phaser.arrive());
        try {
            goToInn();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: Recordó que tenía que entregar un trabajo importante mañana" +
                            "¡Y que no lo había ni empezado! Corrió como nunca hacia casa. Lo terminaron despidiendo.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
            return;
        }
        phaser.arriveAndDeregister();
        try {
            goToFuelStation();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: Recordó que tenía que entregar un trabajo importante mañana" +
                            "¡Y que no lo había ni empezado! Corrió como nunca hacia casa. Lo terminaron despidiendo.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
            return;
        }
        try {
            goToHome();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: Recordó que tenía que ir a comprar antes de ir a casa.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
        }
    }
}
