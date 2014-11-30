import java.awt.*;

/**
 * Created by tiffanychao on 11/15/14.
 */
public abstract class Triangle implements AsteroidsShape {

  protected AsteroidsPoint v0;
  protected AsteroidsPoint v1;
  protected AsteroidsPoint v2;
  protected final double base;
  protected final double height;
  protected AsteroidsPoint incenter;

  /**
   * rotation of triangle around the incenter in radians
   */
  protected double rotation;

  protected Triangle(double bx1, double bx2, double by, AsteroidsPoint v) {
    double epsilon = .00001;
    if (Math.abs(bx1 - bx2) <= epsilon) {
      throw new IllegalArgumentException("x-coordinates of two points on "
                                         + "triangle cannot be equal");
    }
    if (Math.abs(by - v.getY()) <= epsilon) {
      throw new IllegalArgumentException("y-coordinates of two points on "
                                         + "triangle cannot be equal");
    }
    v0 = v;
    v1 = new AsteroidsPoint(bx1, by);
    v2 = new AsteroidsPoint(bx2, by);
    base = v1.distance(v2);
    height = Math.abs(v0.getY() - by);
    incenter = findIncenter(v0, v1, v2,
                            v1.distance(v2), v2.distance(v0), v0.distance(v1));
    rotation = Math.PI / 2;
  }

  // consider graphic coordinates
  protected Triangle(AsteroidsPoint v0, double base, double height) {
    this.v0 = v0;
    this.v1 = new AsteroidsPoint(v0.getX() - (base / 2), v0.getY() + height);
    this.v2 = new AsteroidsPoint(v0.getX() + (base / 2), v0.getY() + height);
    this.base = base;
    this.height = height;
    incenter = findIncenter(v0, v1, v2,
                            v1.distance(v2), v2.distance(v0), v0.distance(v1));
    rotation = Math.PI / 2;
  }

  /**
   * Helper method for constructor. Finds incenter of this triangle.
   * @param va vertex A
   * @param vb vertex B
   * @param vc vertex C
   * @param a length of side opposite vertex A
   * @param b length of side opposite vertex B
   * @param c length of side opposite vertex C
   * @return the incenter
   */
  protected AsteroidsPoint findIncenter(AsteroidsPoint va, AsteroidsPoint vb,
                                      AsteroidsPoint vc, double a, double b,
                                      double c){
    double xa = v0.getX();
    double xb = v1.getX();
    double xc = v2.getX();
    double ya = v0.getY();
    double yb = v1.getY();
    double yc = v2.getY();
    double newX = (a * xa + b * xb + c * xc) / (a + b + c);
    double newY = (a * ya + b * yb + c * yc) / (a + b + c);
    return new AsteroidsPoint(newX, newY);
  }

  public AsteroidsPoint getV0() {
    return v0;
  }

  public AsteroidsPoint getV1() {
    return v1;
  }

  public AsteroidsPoint getV2() {
    return v2;
  }

  public double getBase() {
    return base;
  }

  public double getHeight() {
    return height;
  }

  public AsteroidsPoint getIncenter() {
    return incenter;
  }

  public double getRotation() {
    return rotation;
  }

  public void setRotation(double angle) { rotation = angle; }

  public Shape getShape() {
    return new Polygon(new int[]{v0.intX(), v1.intX(), v2.intX()},
                       new int[]{v0.intY(), v1.intY(), v2.intY()},
                       3);
  }

  public void translate(AsteroidsPoint vector) {
    incenter = getIncenter().add(vector);
    v0 = getV0().add(vector);
    v1 = getV1().add(vector);
    v2 = getV2().add(vector);
  }

  /**
   * Set the rotation angle of this triangle around its incenter.
   * @param angle given rotation angle in radians
   */
  public void rotateAroundCenter(double angle) {
    setRotation((angle + rotation) % (Math.PI * 2));
    v0 = getV0().rotateAroundPoint(angle, getIncenter());
    v1 = getV1().rotateAroundPoint(angle, getIncenter());
    v2 = getV2().rotateAroundPoint(angle, getIncenter());
  }

  /**
   * this probably doesn't work
   *
   * @param angle
   * @param other
   */
  public void rotateAroundPoint(double angle, AsteroidsPoint other) {
    AsteroidsPoint translate = other.negate();
    incenter = getIncenter().add(translate);
    v0 = getV0().rotateAroundOrigin(angle).add(translate.negate());
    v1 = getV1().rotateAroundOrigin(angle).add(translate.negate());
    v2 = getV2().rotateAroundOrigin(angle).add(translate.negate());
  }

  public boolean contains(double x, double y) {
    return true;
  }

  public boolean contains(AsteroidsPoint p) {
    return true;
  }

  public boolean intersects(AsteroidsShape other) {
    return true;
  }


}