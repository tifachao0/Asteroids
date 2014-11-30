/**
 * Created by tiffanychao on 11/15/14.
 */
public class AsteroidsPoint {

  public static final AsteroidsPoint ORIGIN = new AsteroidsPoint(0, 0);
  private final double x;
  private final double y;

  /**
   * Construct a point in Cartesian coordinate space.
   */
  public AsteroidsPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x-coordinate.
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y-coordinate.
   */
  public double getY() {
    return y;
  }

  /**
   * Return the x-coordinate as an integer.
   */
  public int intX() {
    return (int) Math.round(getX());
  }

  /**
   * Return the y-coordinate as an integer.
   */
  public int intY() { return (int) Math.round(getY()); }

  /**
   * Vector addition.
   */
  public AsteroidsPoint add(AsteroidsPoint other) {
    return new AsteroidsPoint(getX() + other.getX(), getY() + other.getY());
  }

  /**
   * Scalar product.
   */
  public AsteroidsPoint scalarProduct(double factor) {
    return new AsteroidsPoint(factor * getX(), factor * getY());
  }

  /**
   * Dot product.
   */
  public double dotProduct(AsteroidsPoint other) {
    return (getX() * other.getX()) + (getY() * other.getY());
  }

  /**
   * Magnitude of vector.
   */
  public double magnitude() {
    return Math.sqrt(dotProduct(this));
  }

  /**
   * Displacement vector from this {@code AsteroidsPoint} to the given
   * {@code AsteroidsPoint}.
   * @param other
   * @return
   */
  public AsteroidsPoint displacement(AsteroidsPoint other) {
    return other.add(this.negate());
  }

  /**
   * Distance between this point and another point.
   */
  public double distance(AsteroidsPoint other) {
    return Math.sqrt(Math.pow(other.getX() - getX(), 2.0) +
                     Math.pow(other.getY() - getY(), 2.0));
  }

  public AsteroidsPoint negate() {
    return new AsteroidsPoint(-getX(), -getY());
  }

  /**
   * Rotates this {@code AsteroidsPoint} counter-clockwise about the origin
   * using matrix multiplication, multiplying the 2D rotation matrix A by the
   * matrix representation of this {@code AsteroidsPoint} B. {@code
   * AsteroidsPoint} is represented as a 2 * 1 matrix with the x-coordinate in
   * row 1, column 1 and the y-coordinate in row 2, column 1.
   *
   * @param angle given angle in radians
   */
  public AsteroidsPoint rotateAroundOrigin(double angle) {
    double newX = (Math.cos(angle) * getX()) + (-Math.sin(angle) * getY());
    double newY = (Math.sin(angle) * getX()) + (Math.cos(angle) * getY());
    return new AsteroidsPoint(newX, newY);
  }

  /**
   * Rotates this {@code AsteroidsPoint} counter-clockwise about the given
   * point. Translates this {@code AsteroidsPoint} to the origin, multiplies the
   * 2D rotation matrix A by the matrix representation of this {@code
   * AsteroidsPoint} B, then undo the translation to return the new rotated
   * {@code AsteroidsPoint}.
   *
   * @param angle given angle in radians
   * @param other the given point to rotate around
   */
  public AsteroidsPoint rotateAroundPoint(double angle, AsteroidsPoint other) {
    AsteroidsPoint translate = other.negate();
    AsteroidsPoint pointToRotate = this.add(translate);
    return pointToRotate.rotateAroundOrigin(angle).add(translate.negate());
  }

  /**
   * compares to another {@code AsteroidsPoint} for equality.
   */
  public boolean equals(AsteroidsPoint other) {
    double epsilon = .00001;
    return (Math.abs(getX() - other.getX()) <= epsilon) &&
           (Math.abs(getY() - other.getY()) <= epsilon);
  }
}
