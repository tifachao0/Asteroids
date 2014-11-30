/**
 * Created by tiffanychao on 11/29/14.
 */
public class Bullet extends Entity {

  public Bullet(AsteroidsPoint position, AsteroidsPoint velocity) {
    super(position, velocity);
    sprite = new Square(position, 2);
  }

  @Override
  public void collide(Entity other) {

  }
}
