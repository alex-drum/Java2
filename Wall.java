package Lesson1;

public class Wall {
    private int height;

    public Wall(int height){
        this.height = height;
    }

    public Boolean jump(Jumping jumper){
        if (jumper.jump(height)) {
            return true;
        } else {
            return false;
        }
    }

}
