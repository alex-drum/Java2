package Lesson1;

public class Human implements Running, Jumping {
    public String getName() {
        return name;
    }

    private String name;

    public int getRunLimit() {
        return runLimit;
    }

    private int runLimit;
    private int jumpLimit;

    public Human(String name, int runLimit, int jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public Boolean run(int distance) {
        if (distance <= runLimit) {
            System.out.println(name + " has successfully run " + distance + " meters.");
            return true;
        } else {
            System.out.println(name + " failed to run " + distance + " meters.");
            return false;
        }
    }

    @Override
    public Boolean jump(int height) {
        if (height <= jumpLimit) {
            System.out.println(name + " has successfully jumped over " + height + " meter wall.");
            return true;
        } else {
            System.out.println(name + " failed to jump over " + height + " meter wall.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "HUMAN: " + name;
    }
}
