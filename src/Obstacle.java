import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Obstacle extends User {
    public Obstacle(int i, int j) {
        super(i, j);

        URL locate = this.getClass().getResource("assets/walls/wall1.png");
        ImageIcon ImgIcon = new ImageIcon(locate);
        Image img = ImgIcon.getImage();
        this.setImage(img);
    }
}
