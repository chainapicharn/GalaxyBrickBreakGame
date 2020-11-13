
package BrickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int x = (int) (Math.random() * 500);
    private int playerX = x;
    private int ballposX = x + 40;
    private int ballposY = 530;
    private int ballXdir = -1;
    private int ballYdir = -4;
    private final ImageIcon img = new ImageIcon(this.getClass().getResource("12.jpg"));
    private final ImageIcon img1 = new ImageIcon(this.getClass().getResource("ballm.png"));
    private MapGenerator map;

    private int hour = 0, min = 0, sec = 0;

    public Gameplay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    Thread time = new Thread(new Runnable() {
        public void run() {
            while (true) {
                if (play) {
                    if (sec > 58) {
                        min++;
                        sec = 0;
                    } else if (min > 58) {
                        min = 0;
                        sec = 0;
                        hour++;
                    }
                    sec++;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    });

    public void paint(Graphics g) {
        URL imageURL;
        Image image;
        Image image2;
        //blackground
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        imageURL = this.getClass().getResource("pa.png");
        image = new ImageIcon(imageURL).getImage();
        

        //deawing map
        g.drawImage(img.getImage(), 0, 0, 2500, 2000, this);
        map.draw((Graphics2D) g);
        //borders
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        //score
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 570, 30);

        //time
        g.drawString("" + hour + ":" + min + ":" + sec, 20, 30);

        //paddle
        //g.setColor(Color.PINK);
        //g.fillRect(playerX, 550, 110, 8);
        g.drawImage(image, playerX, 500, this);
        //ball
        //g.setColor(Color.ORANGE);
        g.drawImage(img1.getImage(), ballposX, ballposY, 30, 30, this);
      

        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won", 260, 300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter To Restart", 230, 350);
            g.drawString("Your Time--> " + hour + ":" + min + ":" + sec, 230, 400);

        }
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over-->Score:"+score, 230, 300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter To Restart", 230, 350);
            g.drawString("Your Time--> " + hour + ":" + min + ":" + sec, 230, 400);

        }
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;

                        }

                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }

        }
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
                time.start();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
                time.start();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = false;
                x = (int) (Math.random() * 500);
                ballposX = x + 40;
                ballposY = 530;
                ballXdir = -1;
                ballYdir = -4;
                playerX = x;
                score = 0;
                totalBricks = 21;
                hour = 0;
                min = 0;
                sec = 0;
                map = new MapGenerator(3, 7);
                repaint();

            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }
}