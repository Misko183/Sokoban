import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

public class SuitableLocation extends User {
    public SuitableLocation(int i, int j) {
        super(i, j);
        URL locate = this.getClass().getResource("assets/obstacle/area1.png");
        ImageIcon ImgIcon = new ImageIcon(locate);
        Image img = ImgIcon.getImage();
        this.setImage(img);
    }
}
