import java.util.Timer;
import java.util.TimerTask;

public class Stoper {
    private Timer timer;
    private TimerTask task;
    private long elapsedTime;
    private long lastTimeStart;
    private boolean isRunning;

    public Stoper() {
        this.timer = new Timer();
        this.elapsedTime = 0;
        this.lastTimeStart = 0;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            lastTimeStart = System.currentTimeMillis();
            task = new TimerTask() {
                public void run() {
                    elapsedTime = System.currentTimeMillis() - lastTimeStart;
                    System.out.println("Czas: " + elapsedTime / 1000.0 + " sekund");
                }
            };
            timer.scheduleAtFixedRate(task, 0, 100); // aktualizacja co 0.1 sekundy
        }
    }

    public void pause() {
        if (isRunning) {
            task.cancel();
            elapsedTime += System.currentTimeMillis() - lastTimeStart;
            isRunning = false;
        }
    }

    public void reset() {
        if (isRunning) {
            task.cancel();
        }
        elapsedTime = 0;
        isRunning = false;
    }

    public double getElapsedTime() {
        return elapsedTime / 1000.0;
    }
}
