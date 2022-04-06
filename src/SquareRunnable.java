package exercice;

import java.awt.*;
import java.util.Random;

public class SquareRunnable implements Runnable{

    private float x;
    private float y;
    private Draw draw;
    private long millis_to_wait = 1000;

    public SquareRunnable(float x, float y, Draw draw) {
        this.x = x;
        this.y = y;
        this.draw = draw;
    }

    //2: Redéfinir la méthode run
    @Override
    public void run(){
        Random rand = new Random();
        try {
            Thread.sleep((long)(rand.nextFloat() * millis_to_wait));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        draw.setPenColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
        draw.filledSquare(0.10 * x + 0.05, 0.10 * y + 0.05, 0.05);
        System.out.println(Thread.currentThread().getName());
    }

}