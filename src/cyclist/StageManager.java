package cyclist;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Phaser;

public class StageManager extends Phaser {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final int FIRST_STAGE_FUEL_STATION = 0;
    public static final int SECOND_STAGE_INN = 1;
    public static final int THIRD_STAGE_FUEL_STATION = 2;

    public StageManager() {
    }

    public StageManager(int parties) {
        super(parties);
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case FIRST_STAGE_FUEL_STATION:
                arriveFuelStation(registeredParties);
                break;
            case SECOND_STAGE_INN:
                arriveInn(registeredParties);
                break;
            case THIRD_STAGE_FUEL_STATION:
                arriveFuelStation(registeredParties);
                return true;
        }
        return super.onAdvance(phase, registeredParties);
    }

    private void arriveFuelStation(int registeredParties) {
        System.out.printf("-----------%s -> %s: Han llegado a la estación %d\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                registeredParties);
    }

    private void arriveInn(int registeredParties) {
        System.out.printf("-----------%s -> %s: Han llegado a la venta %d\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                registeredParties);
    }
} // End class.
