import java.awt.*;
import java.awt.Image;

public class User {
    private final int SPACE = 56;
    int i;
    int j;
    private Image pic;

    public User( int x, int y) {
        this.i = x;
        this.j = y;
    }

    public Image getImage() {
        return this.pic;
    }

    public void setImage(Image img) {
        this.pic = img;
    }

    public int x() {
        return this.i;
    }

    public int y() {
        return this.j;
    }

    public void setX(int x) {
        this.i = x;
    }

    public void setY(int y) {
        this.j = y;
    }

    public boolean isLeftCollision(User actor) {
        if (((this.x() - SPACE) == actor.x()) && (this.y() == actor.y())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRightCollision(User actor) {
        if (((this.x() + SPACE) == actor.x()) && (this.y() == actor.y())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTopCollision(User actor) {
        if (((this.y() - SPACE) == actor.y()) && (this.x() == actor.x())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBottomCollision(User actor) {
        if (((this.y() + SPACE) == actor.y()) && (this.x() == actor.x())) {
            return true;
        } else {
            return false;
        }
    }
}
