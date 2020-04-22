package sample;

import Metods.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    public Timer timer = new Timer();
    public Labl labl = new Labl("10");
    public ImageCollection imgs = new ImageCollection("Слайд");
    public Iterator iter_main = imgs.getIterator();
    public Timeline timeline = new Timeline();
    public Audio audio;
    public int tik = 30;
    public ImageView imageCurr;
    public Label st;


    public void initialize() {
        TimerTask task = new TimerTask() {
            public void run() {
                labl.setS("" + tik);
                tik--;
                st.setText(labl.getS());
               if (tik == 25) {
                   audio = new Audio("src/res/mus.wav", 0.8);
                   audio.sound();
                }
                if (tik == 12) {
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.getKeyFrames().add(new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            if (iter_main.hasNext()) {
                                Image name = (Image) iter_main.next();
                                imageCurr.setImage(name);
                            }
                        }
                    }));
                    timeline.play();
                }
            }
        };
        timer.schedule(task, 1000, 1000);
    }

}
