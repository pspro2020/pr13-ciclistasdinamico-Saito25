package cyclist;

import java.time.LocalTime;
import java.util.concurrent.Phaser;

public class TardyFriend extends Cyclist {

    public TardyFriend(String name, Phaser phaser) {
        super(name, phaser);
    }

    @Override
    public void run() {
        if (!phaser.isTerminated()) {

            int joinPhase = phaser.register();
            System.out.printf("%s -> %s: Chavales, que me uno a vosotros en la fase %d somos %d\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name,
                    joinPhase,
                    phaser.getRegisteredParties());

            if (joinPhase <= StageManager.FIRST_STAGE_FUEL_STATION) {
                try {
                    phaser.awaitAdvanceInterruptibly(phaser.arrive());
                } catch (InterruptedException e) {
                    System.out.printf("%s -> %s: fue calcinado esperando en la galoniera porque esta explotó.\n",
                            LocalTime.now().format(dateTimeFormatter),
                            name);
                    return;
                }
            }
            if (joinPhase <= StageManager.SECOND_STAGE_INN) {
                try {
                    goToInn();
                    phaser.awaitAdvanceInterruptibly(phaser.arrive());
                } catch (InterruptedException e) {
                    System.out.printf("%s -> %s: Recordó que tenía que entregar un trabajo importante mañana" +
                                    "¡Y que no lo había ni empezado! Corrió como nunca hacia casa. Lo terminaron despidiendo.\n",
                            LocalTime.now().format(dateTimeFormatter),
                            name);
                    return;
                }
            }
            if (joinPhase <= StageManager.THIRD_STAGE_FUEL_STATION) {
                try {
                    goToFuelStation();
                    phaser.awaitAdvanceInterruptibly(phaser.arrive());
                } catch (InterruptedException e) {
                    System.out.printf("%s -> %s: Recordó que tenía que entregar un trabajo importante mañana" +
                                    "¡Y que no lo había ni empezado! Corrió como nunca hacia casa. Lo terminaron despidiendo.\n",
                            LocalTime.now().format(dateTimeFormatter),
                            name);
                    return;
                }
            }
            try {
                goToHome();
                phaser.arriveAndDeregister();
            } catch (InterruptedException e) {
                System.out.printf("%s -> %s: Recordó que tenía que ir a comprar antes de ir a casa.\n",
                        LocalTime.now().format(dateTimeFormatter),
                        name);
            }
        } else {
            System.out.printf("%s -> %s: Se levantó muy tarde y no llegó a la carrera\n",
                    LocalTime.now().format(dateTimeFormatter),
                    name);
        }
    }
}
