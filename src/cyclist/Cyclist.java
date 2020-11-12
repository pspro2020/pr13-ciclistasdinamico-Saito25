package cyclist;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public abstract class Cyclist implements Runnable {

    protected final String name;
    protected final Phaser phaser;
    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    protected Cyclist(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
    }

    protected void goOutHome() throws InterruptedException {
        System.out.printf("%s -> %s: ha salido de casa dirección a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 4));
        System.out.printf("%s -> %s: Ha llegado a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
    }

    protected void goToInn() throws InterruptedException {
        System.out.printf("%s -> %s: ha salido de la gasolinera dirección a la venta\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5, 11));
        System.out.printf("%s -> %s: Ha llegado a la venta\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
    }

    protected void goToFuelStation() throws InterruptedException {
        System.out.printf("%s -> %s: ha salido de la venta dirección a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5, 11));
        System.out.printf("%s -> %s: Ha llegado a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
    }

    protected void goToHome() throws InterruptedException {
        System.out.printf("%s -> %s: ha salido de la gasolinera dirección a casa\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 4));
        System.out.printf("%s -> %s: Ya en casa\n",
                LocalTime.now().format(dateTimeFormatter),
                name);
    }
}
