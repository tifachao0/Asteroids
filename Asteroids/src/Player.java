/**
 * Created by tiffanychao on 11/15/14.
 */
public class Player extends Entity {

  /**
   * Construct an entity to represent the player based on init location and
   * init velocity.
   */
  public Player(AsteroidsPoint location, AsteroidsPoint velocity) {
    super(location, velocity);
    sprite = new IsoscelesTriangle(location, 14, 20);
  }

  @Override
  public void collide(Entity other) {
    return; // TODO
  }
}
