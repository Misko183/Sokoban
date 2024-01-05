import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PlayerCharacter extends User {

    public PlayerCharacter(int i, int j) {
        super(i, j);

        URL locate = this.getClass().getResource("assets/players/player1.png");
        ImageIcon ImgIcon = new ImageIcon(locate);
        Image img = ImgIcon.getImage();
        this.setImage(img);
    }

    public void move(int i, int j) {
        int mi = this.x() + i;
        int mj = this.y() + j;
        this.setX(mi);
        this.setY(mj);
    }
}
