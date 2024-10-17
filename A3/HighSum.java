import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighSum {
    public static void main(String[] args) {
        if (new LoginSystem().login()) {
            GameGUI g = new GameGUI();
            g.shuffleGUI();

            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    g.run();
                }
            });
            
            timer.setRepeats(false); 
            timer.start(); 
        }
    }
}
