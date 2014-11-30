import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * Created by tiffanychao on 11/15/14.
 */
public class Game extends JPanel implements ActionListener {

  public final static int GAME_WIDTH = 800;
  public final static int GAME_HEIGHT = 800;

  private Entity player;
  private final double PLAYER_ROTATION = Math.PI / 10;
  private final double PLAYER_INITIAL_VELOCITY = 5;

  private ArrayList<Asteroid> lAsteroid = new ArrayList<Asteroid>();
  private final double ASTEROID_INITIAL_VELOCITY = 2.5;

  private ArrayList<Bullet> lBullet = new ArrayList<Bullet>();
  private final double BULLET_INITIAL_VELOCITY = 10;
  private final double BULLET_MAX_TRAVEL_DISTANCE = GAME_WIDTH / 2;

  private boolean inGame = true;
  private boolean leftPressed = false;
  private boolean rightPressed = false;
  private boolean upPressed = false;
  private boolean spacePressed = false;
  private Timer timer;
  private final int DELAY = 25;

  /**
   * constructor
   */
  public Game() {
    setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    addKeyListener(new KeyHandler());
    setFocusable(true);

    initEntities();

    timer = new Timer(DELAY, this);
    timer.start();
  }

  private void initEntities() {
    player = new Player(new AsteroidsPoint(GAME_WIDTH / 2, GAME_HEIGHT / 2),
                        new AsteroidsPoint(0, PLAYER_INITIAL_VELOCITY));

    Random r = new Random();
    int asteroidInitCount = 7;
    for (int i = 0; i < asteroidInitCount; i++) {
      // determine random velocity
      double newPosX = r.nextDouble() * GAME_WIDTH;
      double newPosY = r.nextDouble() * GAME_HEIGHT;
      AsteroidsPoint randomVelocity = findRandomAsteroidVelocity
          (ASTEROID_INITIAL_VELOCITY);
      // determine type of asteroid
      int asteroidType = r.nextInt(2);
      if (asteroidType == 0) {
        Asteroid asteroid = new AsteroidSquare(new AsteroidsPoint(newPosX,
                                                                newPosY),
                                             randomVelocity);
        lAsteroid.add(asteroid);
      } else {
        Asteroid asteroid = new AsteroidCircle(new AsteroidsPoint(newPosX,
                                                                newPosY),
                                             randomVelocity);
        lAsteroid.add(asteroid);
      }
    }
  }

  private AsteroidsPoint findRandomAsteroidVelocity(double magnitude) {
    Random r = new Random();
    double randomAngle = r.nextDouble() * (Math.PI * 2);
    double newVelocityX = Math.cos(randomAngle) * magnitude;
    double newVelocityY = Math.sin(randomAngle) * magnitude;
    return new AsteroidsPoint(newVelocityX, newVelocityY);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    setBackground(Color.black);

    draw(g2d);
  }

  private void move() {
    if (upPressed) {
      player.move();
    }
    if (leftPressed) {
      player.rotate(-PLAYER_ROTATION);
    }
    if (rightPressed) {
      player.rotate(PLAYER_ROTATION);
    }

    player.wrap();

    for (Asteroid a : lAsteroid) {
      a.move();
      a.wrap();
    }

    for (Bullet b : lBullet) {
      b.move();
      b.wrap();
    }
  }

  /**
   * handles player firing bullets
   */
  private void fire() {
    if (spacePressed) {
      Triangle t = (Triangle) player.sprite;
      AsteroidsPoint origin = t.getV0();
      // TODO
      // write more general method for determining vector from given
      // magnitude and angle
      double rotation = t.getRotation();
      double velocityX = Math.cos(rotation) * BULLET_INITIAL_VELOCITY;
      double velocityY = Math.sin(rotation) * BULLET_INITIAL_VELOCITY;
      AsteroidsPoint velocity = new AsteroidsPoint(velocityX, velocityY);

      Bullet b = new Bullet(origin, velocity);
      lBullet.add(b);
    }
  }

  private void draw(Graphics2D g) {

    if (inGame) {
      // draw player
      g.setColor(Color.WHITE);
      g.draw(player.drawShape());

      // draw asteroids
      for (Asteroid a : lAsteroid) {
        if (a instanceof AsteroidSquare) {
          g.setColor(Color.CYAN);
        }
        if (a instanceof AsteroidCircle) {
          g.setColor(Color.MAGENTA);
        }
        g.draw(a.drawShape());
      }

      // draw bullets
      g.setColor(Color.WHITE);
      for (Bullet b : lBullet) {
        g.draw(b.drawShape());
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (inGame) {
      move();
      fire();
    }

    repaint();
  }

  private class KeyHandler extends KeyAdapter {

    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        leftPressed = true;
      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        rightPressed = true;
      }
      if (e.getKeyCode() == KeyEvent.VK_UP) {
        upPressed = true;
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        spacePressed = true;
      }
    }

    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        leftPressed = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        rightPressed = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_UP) {
        upPressed = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        spacePressed = false;
      }
    }

    // if escape key is typed, quit the game
    public void keyTyped(KeyEvent e) {
      if (e.getKeyChar() == 27) {
        System.exit(0);
      }
    }
  }

  public static void main (String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Asteroids");
        frame.setContentPane(new Game());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
}
