package Lesson1;

public class Track {
    private int distance;

    public Track(int distance){
        this.distance = distance;
    }

//    public void start(Running runner){
//        runner.run(distance);
//    }

    public Boolean run(Running runner){
        if (runner.run(distance)) {
            return true;
        } else {
            return false;
        }
    }

}
