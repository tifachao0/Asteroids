import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by tiffanychao on 11/29/14.
 */
public class Circle implements AsteroidsShape {

  private AsteroidsPoint center;
  private double radius;

  public Circle(AsteroidsPoint center, double radius) {
    this.center = center;
    this.radius = radius;
  }

  @Override
  public Shape getShape() {
    return new Ellipse2D.Double(center.getX() - radius, center.getY() - radius,
                                radius * 2, radius * 2);
  }

  @Override
  public double getRotation() {
    return 0;
  }

  @Override
  public void translate(AsteroidsPoint vector) {
    center = center.add(vector);
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
