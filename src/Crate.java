import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;
import fri.shapesge.Obrazok;

public class Crate extends User {
    private Obrazok obrazok;


    public Crate(int i, int j) {

        super(i, j);
        URL locate = this.getClass().getResource("assets/crates/crate1.png");
        ImageIcon ImgIcon = new ImageIcon(locate);
        Image img = ImgIcon.getImage();
        this.setImage(img);
    }

    public void move(int i, int j) {
        int mi = this.x() + i;
        int mj = this.y() + j;
        this.setX(mi);
        this.setY(mj);
        //obrazok.zmenPolohu(mi, mj);
    }


//    public void zobraz() {
//        obrazok.zobraz();
//    }
}
