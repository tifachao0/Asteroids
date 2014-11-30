import java.awt.*;

/**
 * Created by tiffanychao on 11/15/14.
 */
public abstract class Entity {

  /**
   * The position of this entity in graphic coordinate space.
   */
  protected AsteroidsPoint position;
  /**
   * The velocity of this entity (pixels/sec)
   */
  protected AsteroidsPoint velocity;
  /**
   * magnitude of velocity vector
   */
  protected double magnitude;
  /**
   * Determines if this entity is visible.
   */

  protected AsteroidsShape sprite; // todo

  protected boolean isVisible;
  /**
   * Determines if this entity is screen wrapping based on its position on the
   * x-axis.
   */
  protected boolean isWrappingX;
  /**
   * Determines if this entity is screen wrapping based on its position on the
   * y-axis.
   */
  protected boolean isWrappingY;
  /**
   * Construct an entity based on position.
   */
  public Entity(AsteroidsPoint position, AsteroidsPoint velocity) {
    this.position = position;
    this.velocity = velocity;
    this.magnitude = velocity.magnitude();
  }

  // move in graphic coordinates
  public void move() {
    position = position.add(velocity.negate());
    sprite.translate(velocity.negate());
  }

  public void rotate(double angle) {
    sprite.rotateAroundCenter(angle);
    double newAngle = sprite.getRotation();
    velocity = findVelocity(newAngle);
  }

  /**
   * helper method for rotate
   * @param angle in radians
   */
  private AsteroidsPoint findVelocity(double angle) {
    double newVelocityX = Math.cos(angle) * magnitude;
    double newVelocityY = Math.sin(angle) * magnitude;
    return new AsteroidsPoint(newVelocityX, newVelocityY);

  }

  /**
   * If the entity is still visible or if it is in the process of wrapping both
   * x/y coordinates around the screen then do nothing. Otherwise, if the entity
   * is offscreen, wraps the entity's position around the screen.
   */
  public void wrap() {

    if (isVisible()) {
      isWrappingX = false;
      isWrappingY = false;
      return;
    }

    if (isWrappingX && isWrappingY) {
      return;
    }

    AsteroidsPoint relativePosition = getRelativePosition();
    AsteroidsPoint newPosition = getCartesianPosition();

    if (!isWrappingX && (relativePosition.getX() < 0.0
                         || relativePosition.getX() > 1.0)) {
      newPosition = new AsteroidsPoint(-newPosition.getX(), newPosition.getY());
      isWrappingX = true;
    }
    if (!isWrappingY && (relativePosition.getY() < 0.0
                         || relativePosition.getY() > 1.0)) {
      newPosition = new AsteroidsPoint(newPosition.getX(), -newPosition.getY());
      isWrappingY = true;
    }

    setPosition(new AsteroidsPoint(newPosition.getX() + (double) (Game.GAME_WIDTH / 2),
                                   newPosition.getY() + (double) (Game.GAME_HEIGHT /
                                                         2)));
  }

  public boolean isVisible() {
    AsteroidsPoint relativePosition = getRelativePosition();
    return (relativePosition.getX() >= 0.0 &&
            relativePosition.getX() <= 1.0) &&
           (relativePosition.getY() >= 0.0 &&
            relativePosition.getY() <= 1.0);
  }

  /**
   * Sets the position of this entity.
   */
  public void setPosition(AsteroidsPoint position) {
    AsteroidsPoint oldPosition = this.position;
    AsteroidsPoint displacementVector = oldPosition.displacement(position);
    this.position = position;
    sprite.translate(displacementVector);
  }

  /**
   * Gets the position of this entity.
   */
  public AsteroidsPoint getposition() {
    return position;
  }

  /**
   * Gets the position of this entity as a {@code Point} for rendering. If this
   * entity's position's x/y coordinates are in between pixels then round to the
   * nearest pixel.
   */
  public Point getIntPosition() {
    return new Point((int) Math.round(position.getX()),
                     (int) Math.round(position.getY()));
  }

  /**
   * Gets the position of this entity in relation to the visible scene.
   */
  public AsteroidsPoint getRelativePosition() {
    return new AsteroidsPoint(position.getX() / (double) Game.GAME_WIDTH,
                              position.getY() / (double) Game.GAME_HEIGHT);
  }

  /**
   * Gets the position of this entity in Cartesian coordinate space.
   */
  public AsteroidsPoint getCartesianPosition() {
    return new AsteroidsPoint(position.getX() - (double) (Game.GAME_WIDTH / 2),
                              position.getY() - (double) (Game.GAME_HEIGHT / 2));
  }

  /**
   * Set the velocity of this entity.
   */
  public void setVelocity(AsteroidsPoint velocity) {
    this.velocity = velocity;
  }

  /**
   * Get the velocity of this entity.
   */
  public AsteroidsPoint getVelocity() {
    return velocity;
  }

  public boolean hasCollide(Entity other) {
    // TODO
    return true;
  }

  public abstract void collide(Entity other);

  public Shape drawShape() {
    return sprite.getShape();
  }
}
