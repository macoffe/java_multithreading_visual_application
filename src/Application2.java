package exercice;

import java.util.concurrent.ForkJoinPool;

public class Application2 {

    public static void main(String[] args) {
        Draw draw1 = new Draw("Application2");
        ForkJoinPool forkJoinPool = new ForkJoinPool(16);
        forkJoinPool.execute(new CustomRecursiveTask(draw1));
    }

}
