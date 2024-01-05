import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Flor extends User {
    public Flor(int i, int j) {
        super(i, j);

        URL locate = this.getClass().getResource("assets/floors/floor1.png");
        ImageIcon ImgIcon = new ImageIcon(locate);
        Image img = ImgIcon.getImage();
        this.setImage(img);
    }
}
