package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class CustomRecursiveTask extends RecursiveAction {

    private float x;
    private float y;
    private float biais_x = 0;
    private float biais_y = 0;
    private float size = 1;
    private Draw draw;
    private int millis_to_wait = 600;
    private static final float THRESHOLD = (float) 0.125;

    public CustomRecursiveTask(Draw draw1){
        this.draw = draw1;
    }

    public CustomRecursiveTask(float x, float y, float size, Draw draw1, float biais_x, float biais_y){
        this.x = x;
        this.y = y;
        this.size = size;
        this.draw = draw1;
        this.biais_x = biais_x;
        this.biais_y = biais_y;
    }

    @Override
    protected void compute() {
        if (size > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            processing();
        }
    }

    private List<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> subtasks = new ArrayList<>();

        CustomRecursiveTask BottomLeft = new CustomRecursiveTask(biais_x+0, biais_y+0, size/2, draw, biais_x, biais_y);
        CustomRecursiveTask BottomRight = new CustomRecursiveTask(biais_x+size/2, biais_y+0, size/2, draw, biais_x+size/2, biais_y);
        CustomRecursiveTask TopLeft = new CustomRecursiveTask(biais_x+0, biais_y+size/2, size/2, draw, biais_x, biais_y+size/2);
        CustomRecursiveTask TopRight = new CustomRecursiveTask(biais_x+size/2, biais_y+size/2, size/2, draw, biais_x+size/2, biais_y+size/2);

        subtasks.add(BottomLeft);
        subtasks.add(BottomRight);
        subtasks.add(TopLeft);
        subtasks.add(TopRight);

        return subtasks;
    }

    private void processing() {
        Random rand = new Random();
        try {
            Thread.sleep(millis_to_wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        draw.setPenColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
        draw.filledSquare(x+(THRESHOLD/2), y+(THRESHOLD/2), size/2);
        System.out.println(Thread.currentThread().getName());
    }
}