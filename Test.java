package Lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        Human alex = new Human("Alex", 3000, 1);
        Human dave = new Human("Dave", 900, 3);
        Cat barsik = new Cat("Barsik", 500, 3);
        Cat moorzyk = new Cat("Moorzyk", 2000, 2);
        Robot eva = new Robot("Eva", 10000, 1);
        Robot walle = new Robot("Walle", 2000, 4);
        Track track = new Track(1000);
        Wall wall = new Wall(2);

        Running[] runners = {alex, dave, barsik, moorzyk, eva, walle};
        ArrayList runWinners = new ArrayList(Arrays.asList(runners));
        ArrayList jumpWinners = new ArrayList();

        for (Running runner: runners
             ) {if (!track.run(runner)) {
            runWinners.remove(runner);
                }
        }

        System.out.print("\n" + "RUN WINNERS: ");
        runWinners.forEach(winner -> System.out.print(winner.toString() + "; "));
        System.out.println("\n");

        runWinners.forEach(runner -> {
            Jumping jumper = (Jumping) runner;
            if (!wall.jump(jumper)) {
                jumpWinners.add(jumper);
            }
        });

        System.out.print("\n" + "Competition WINNERS are: ");
        jumpWinners.forEach(jumper -> System.out.print(jumper.toString() + "; "));
        System.out.println();

    }
}
