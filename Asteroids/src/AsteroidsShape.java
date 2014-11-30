import java.awt.*;

/**
 * Created by tiffanychao on 11/20/14.
 */
public interface AsteroidsShape {

  public Shape getShape();

  public double getRotation();

  public void translate(AsteroidsPoint vector);

  public void rotateAroundCenter(double angle);

  public void rotateAroundPoint(double angle, AsteroidsPoint point);

  public boolean contains(double x, double y);

  public boolean contains(AsteroidsPoint p);

  public boolean intersects(AsteroidsShape other);

}
