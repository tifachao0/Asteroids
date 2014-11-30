/**
 * Created by tiffanychao on 11/29/14.
 */
public abstract class Asteroid extends Entity {

  public Asteroid(AsteroidsPoint location, AsteroidsPoint velocity) {
    super(location, velocity);
  }

  @Override
  public void collide(Entity other) {
  }

}
