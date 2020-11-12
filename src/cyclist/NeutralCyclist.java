package cyclist;

import java.time.LocalTime;
import java.util.concurrent.*;

public class NeutralCyclist extends Cyclist {

    public NeutralCyclist(String name, Phaser phaser) {
        super(name, phaser);
    }

    @Override
    public void run() {
        phaser.register();
        System.out.printf("%s -> %s: Se ha registrado.\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
        try {
            goOutHome();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: tuvo un accidente de camino a la gasolinera, y jamás volvió a saberse " +
                            "de él. Descanse en paz.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
            return;
        }
        try {
            phaser.awaitAdvanceInterruptibly(phaser.arrive());
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: fue calcinado esperando en la galoniera porque esta explotó.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
            return;
        }
        try {
            goToInn();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: Recordó que tenía que entregar un trabajo importante mañana" +
                            "¡Y que no lo había ni empezado! Corrió como nunca hacia casa. Lo terminaron despidiendo.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
            return;
        }
        try {
            phaser.awaitAdvanceInterruptibly(phaser.arrive());
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: Comió algo en mal estado en la venta mientras esperaba... Atascó el baño " +
                            "de la venta.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
            return;
        }
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
            phaser.awaitAdvanceInterruptibly(phaser.arrive());
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: Recordó que tenía que entregar un trabajo importante mañana" +
                            "¡Y que no lo había ni empezado! Corrió como nunca hacia casa. Lo terminaron despidiendo.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
        }
        try {
            goToHome();
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s: Recordó que tenía que ir a comprar antes de ir a casa.\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
        }
    }
}
