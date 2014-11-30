/**
 * Created by tiffanychao on 11/15/14.
 */
public class IsoscelesTriangle extends Triangle {

  public IsoscelesTriangle(double bx1, double bx2, double by,
                           AsteroidsPoint v) {
    super(bx1, bx2, by, v);
    double epsilon = .00001;
    AsteroidsPoint baseMidpoint = new AsteroidsPoint(v.getX(), by);
    if (v1.distance(baseMidpoint) - v2.distance(baseMidpoint) <= epsilon) {
      throw new IllegalArgumentException("not an isosceles triangle");
    }
  }

  public IsoscelesTriangle(AsteroidsPoint v0, double base, double height) {
    super(v0, base, height);
  }
}
