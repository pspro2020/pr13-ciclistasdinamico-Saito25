package main;

import cyclist.ImpacientCyclist;
import cyclist.NeutralCyclist;
import cyclist.StageManager;
import cyclist.TardyFriend;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Main {

    private final int NEUTRAL_CYCLIST = 10;

    public Main() throws InterruptedException {
        StageManager stageManager = new StageManager();
        Phaser initRaceFlag = new Phaser(1);

        for (int i = 0; i < NEUTRAL_CYCLIST; i++) {
            initRaceFlag.register();
            int finalI = i;
            new Thread("Normal Friend #" + (i + 1)) {
                public void run() {
                    initRaceFlag.arriveAndAwaitAdvance();
                    new NeutralCyclist("Normal Friend #" + (finalI + 1), stageManager).run();
                }
            }.start();
        }

        initRaceFlag.register();
        new Thread("Impacient Friend #1") {
            public void run() {
                initRaceFlag.arriveAndAwaitAdvance();
                new ImpacientCyclist("Impacient Friend #1", stageManager).run();
            }
        }.start();

        initRaceFlag.arriveAndDeregister();

        TimeUnit.SECONDS.sleep(6);

        new Thread(new TardyFriend("Tardy Friend #1", stageManager), "Tardy Friend #1").start();

        TimeUnit.SECONDS.sleep(45);

        new Thread(new TardyFriend("Tardy Friend #2", stageManager), "Tardy Friend #2").start();
    }

    public static void main(String[] args) throws InterruptedException {
        new Main();
    }
}
