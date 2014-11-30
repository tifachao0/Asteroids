/**
 * Created by tiffanychao on 11/29/14.
 */
public class AsteroidSquare extends Asteroid {

  /**
   * Construct an entity to represent a square asteroid.
   * @param location
   * @param velocity
   */
  public AsteroidSquare(AsteroidsPoint location, AsteroidsPoint velocity) {
    super(location, velocity);
    sprite = new Square(location, 40);
  }
}
