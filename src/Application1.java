package exercice;

public class Application1 {

    public static void main(String[] args) {
        Draw draw1 = new Draw("Application1");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                SquareRunnable runner = new SquareRunnable(i, j, draw1);
                Thread t1 = new Thread(runner);
                t1.start();
            }
        }   
    }

}
