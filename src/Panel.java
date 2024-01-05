import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Panel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int OFFSET = 56;
    private final int SPACE = 56;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;
    private ArrayList<Obstacle> obs = new ArrayList<>();
    private ArrayList<Crate> crates = new ArrayList<>();
    private ArrayList<SuitableLocation> SuitLo = new ArrayList<>();
    private ArrayList<Flor> flor = new ArrayList<>();
    private int w = 0;
    private int h = 0;
    private boolean solved = false;
    private PlayerCharacter PlayerChar;
    private int currentStage = 0;
    private String[] stages = {
                      "    ######\n"
                    + "    ##   #\n"
                    + "    ##$ $#\n"
                    + "  ####   ##\n"
                    + "  ##   $  #\n"
                    + "#### # ## #   ######\n"
                    + "##   # ## #####  ..#\n"
                    + "## $  $        $ ..#\n"
                    + "###### ### #@##  ..#\n"
                    + "    ##     #########\n"
                    + "    ########\n",
                      "    #####\n"
                    + "###   #\n"
                    + "#  .# ##\n"
                    + "# #  . #\n"
                    + "# .  # #\n"
                    + "## #@  #\n"
                    + " #   $##\n"
                    + " ###  #\n"
                    + "   ####\n",
                      "    ########\n"
                    + "    #  .   #\n"
                    + "    #   . @#\n"
                    + "    ##$ #  #\n"
                    + "    #   #. #\n"
                    + "    #      #\n"
                    + "    ########\n",
            "    ########\n"
                    + "    # .#  .#\n"
                    + "    #   $$ #\n"
                    + "    ## #   #\n"
                    + "    ## #$  #\n"
                    + "    #.    @#\n"
                    + "    ########\n",
            "    ########\n"
                    + "    # .#   #\n"
                    + "    #  $   #\n"
                    + "    ##   ###\n"
                    + "    ## #  .#\n"
                    + "    #  $   #\n"
                    + "    #   # @#\n"
                    + "    ########\n"
    };
    private JButton btnQuit;
    private Timer timer;
    private int secondsElapsed;
    private JButton btnRestart;
    private Music music;

    public Panel() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initUI();
        initWorld();
        startTimer();
        music = new Music();
    }

    private void initUI() {
        btnQuit = new JButton("Ukončiť hru");
        btnQuit.setBounds(100, 100, 100, 100);
        add(btnQuit);
        btnQuit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );

        btnRestart = new JButton("Reštart");
        btnRestart.setBounds(210, 100, 100, 100);
        add(btnRestart);
        btnRestart.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        restartStage();
                        requestFocusInWindow();
                    }
                }
        );
    }

    private void startTimer() {
        secondsElapsed = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++;
                repaint();
            }
        });
        timer.start();
    }

    private void stopTimer() {
        timer.stop();
    }

    private void nextStage() {
        stopTimer();
        currentStage = (currentStage + 1) % stages.length;
        restartStage();
        startTimer();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {
        int x = OFFSET;
        int y = OFFSET;

        Flor c;
        Obstacle wall;
        Crate b;
        SuitableLocation a;


        String currentStageStr = stages[currentStage];

        for (int i = 0; i < currentStageStr.length(); i++) {
            char item = currentStageStr.charAt(i);

            if (item == '\n') {
                y += SPACE;

                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '#') {
                wall = new Obstacle(x, y);
                obs.add(wall);
                x += SPACE;
            } else if (item == '$') {
                b = new Crate(x, y);
                crates.add(b);
                x += SPACE;
            } else if (item == '.') {
                a = new SuitableLocation(x, y);
                SuitLo.add(a);
                x += SPACE;
            } else if (item == '@') {
                PlayerChar = new PlayerCharacter(x, y);
                x += SPACE;
            } else if (item == ' ') {
                c = new Flor(x, y);
                flor.add(c);
                x += SPACE;
            }
            h = y;
        }
    }

    public void buildWorld(Graphics g) {
        //g.setColor(new Color(0, 108, 209));
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(new Color(0, 0, 0));
        g.drawString("Čas: " + secondsElapsed + " sekúnd", 25, 20);

        ArrayList<User> world = new ArrayList<>();
        world.addAll(obs);
        world.addAll(SuitLo);
        world.addAll(crates);
        world.add(PlayerChar);
        //world.addAll(flor);

        for (int i = 0; i < world.size(); i++) {
            User item = world.get(i);

            if ((item instanceof PlayerCharacter) || (item instanceof Crate)) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            //if (solved) {
                //g.setColor(new Color(255, 255, 255));
                //g.drawString("Level je hotový!", 25, 20);
                //Font f = new Font("Courier", Font.BOLD, 14);
                //setFont(f);
            //}
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (solved) {
                return;
            }

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                if (checkWallCollision(PlayerChar, LEFT_COLLISION)) {
                    return;
                }

                if (checkBagCollision(LEFT_COLLISION)) {
                    return;
                }

                PlayerChar.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {

                if (checkWallCollision(PlayerChar, RIGHT_COLLISION)) {
                    return;
                }

                if (checkBagCollision(RIGHT_COLLISION)) {
                    return;
                }

                PlayerChar.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {

                if (checkWallCollision(PlayerChar, TOP_COLLISION)) {
                    return;
                }

                if (checkBagCollision(TOP_COLLISION)) {
                    return;
                }

                PlayerChar.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {

                if (checkWallCollision(PlayerChar, BOTTOM_COLLISION)) {
                    return;
                }

                if (checkBagCollision(BOTTOM_COLLISION)) {
                    return;
                }

                PlayerChar.move(0, SPACE);

            } else if (key == KeyEvent.VK_R) {
                restartStage();
            }
            repaint();
        }
    }

    private boolean checkWallCollision(User actor, int type) {
        if (type == LEFT_COLLISION) {
            for (Obstacle wall : obs) {
                if (actor.isLeftCollision(wall)) {
                    return true;
                }
            }
            return false;
        } else if (type == RIGHT_COLLISION) {
            for (Obstacle wall : obs) {
                if (actor.isRightCollision(wall)) {
                    return true;
                }
            }
            return false;
        } else if (type == TOP_COLLISION) {
            for (Obstacle wall : obs) {
                if (actor.isTopCollision(wall)) {
                    return true;
                }
            }
            return false;
        } else if (type == BOTTOM_COLLISION) {
            for (Obstacle wall : obs) {
                if (actor.isBottomCollision(wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkBagCollision(int type) {
        if (type == LEFT_COLLISION) {
            for (Crate bag : crates) {
                if (PlayerChar.isLeftCollision(bag)) {
                    for (Crate item : crates) {
                        if (!bag.equals(item)) {
                            if (bag.isLeftCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, LEFT_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(-SPACE, 0);
                    isSolved();
                }
            }
            return false;
        } else if (type == RIGHT_COLLISION) {
            for (Crate bag : crates) {
                if (PlayerChar.isRightCollision(bag)) {
                    for (Crate item : crates) {
                        if (!bag.equals(item)) {
                            if (bag.isRightCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, RIGHT_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(SPACE, 0);
                    isSolved();
                }
            }
            return false;
        } else if (type == TOP_COLLISION) {
            for (Crate bag : crates) {
                if (PlayerChar.isTopCollision(bag)) {
                    for (Crate item : crates) {
                        if (!bag.equals(item)) {
                            if (bag.isTopCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, TOP_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, -SPACE);
                    isSolved();
                }
            }
            return false;
        } else if (type == BOTTOM_COLLISION) {
            for (Crate bag : crates) {
                if (PlayerChar.isBottomCollision(bag)) {
                    for (Crate item : crates) {
                        if (!bag.equals(item)) {
                            if (bag.isBottomCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, BOTTOM_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, SPACE);
                    isSolved();
                }
            }
        }
        return false;
    }

    public void isSolved() {
        int num = crates.size();
        int compl = 0;

        for (Crate bag : crates) {
            for (SuitableLocation area : SuitLo) {
                if (bag.x() == area.x() && bag.y() == area.y()) {
                    compl += 1;
                }
            }
        }

        if (compl == num) {
            solved = true;
            music.setFile("src/assets/music/win.wav");
            music.restart();
            stopTimer();
            repaint();
            displayCompletionMessage();
        }
    }

    private void displayCompletionMessage() {
        String message = "Level hotový za " + secondsElapsed + " sekúnd!";
        String[] options = {"Ďalší level", "Ukončiť hru", "Reštart"};
        int choice = JOptionPane.showOptionDialog(
                this,
                message,
                "Level hotový",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            nextStage();
        } else if(choice == 2) {
          restartStage();
        } else {
            System.exit(0);
        }
    }

    public void restartStage() {
        crates.clear();
        SuitLo.clear();
        obs.clear();
        solved = false;
        initWorld();
        repaint();
        startTimer();
    }
}
