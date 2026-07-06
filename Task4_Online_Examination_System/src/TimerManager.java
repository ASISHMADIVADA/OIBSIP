import javax.swing.*;
import java.awt.event.*;

public class TimerManager {

    private int timeLeft;
    private Timer timer;
    private Runnable onTimeUp;

    public TimerManager(int seconds, Runnable onTimeUp) {
        this.timeLeft = seconds;
        this.onTimeUp = onTimeUp;
    }

    public void start(JLabel label) {

        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                timeLeft--;
                label.setText("Time Left: " + timeLeft);

                if (timeLeft <= 0) {
                    timer.stop();
                    onTimeUp.run();
                }
            }
        });

        timer.start();
    }

    public void stop() {
        if (timer != null) timer.stop();
    }
}