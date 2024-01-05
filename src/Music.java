import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    Clip clip;
    AudioInputStream sound;

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }

    public void restart() {
        clip.setFramePosition(0);
        clip.start();
    }
}
