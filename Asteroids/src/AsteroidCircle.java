/**
 * Created by tiffanychao on 11/29/14.
 */
public class AsteroidCircle extends Asteroid {

  public AsteroidCircle(AsteroidsPoint position, AsteroidsPoint velocity) {
    super(position, velocity);
    sprite = new Circle(position, 20);
  }
}
