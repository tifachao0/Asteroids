import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by tiffanychao on 11/29/14.
 */
public class Square implements AsteroidsShape {

  // graphic coordinates
  private AsteroidsPoint topLeft;
  private AsteroidsPoint topRight;
  private AsteroidsPoint bottomLeft;
  private AsteroidsPoint bottomRight;
  private AsteroidsPoint center;
  // side length
  private final double side;

  public Square(AsteroidsPoint center, double side) {
    this.side = side;
    this.center = center;
    double sideDiv2 = side / 2;
    topLeft = center.add(new AsteroidsPoint(-sideDiv2, -sideDiv2));
    topRight = center.add(new AsteroidsPoint(sideDiv2, -sideDiv2));
    bottomLeft = center.add(new AsteroidsPoint(-sideDiv2, sideDiv2));
    bottomRight = center.add(new AsteroidsPoint(sideDiv2, sideDiv2));
  }

  @Override
  public Shape getShape() {
    return new Rectangle2D.Double(topLeft.getX(), topLeft.getY(), side, side);
  }

  @Override
  public double getRotation() {
    return 0;
  }


  @Override
  public void translate(AsteroidsPoint vector) {
    center = center.add(vector);
    topLeft = topLeft.add(vector);
    topRight = topRight.add(vector);
    bottomLeft = bottomLeft.add(vector);
    bottomRight = bottomRight.add(vector);
  }

  @Override
  public void rotateAroundCenter(double angle) {

  }

  @Override
  public void rotateAroundPoint(double angle, AsteroidsPoint point) {

  }

  @Override
  public boolean contains(double x, double y) {
    return false;
  }

  @Override
  public boolean contains(AsteroidsPoint p) {
    return false;
  }

  @Override
  public boolean intersects(AsteroidsShape other) {
    return false;
  }
}
