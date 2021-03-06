package blatt_06.aufgabe_06_vorgabe;

public class Position implements Comparable<Position>{
  private int x;
  private int y;
  
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() { return this.x; }

  public int getY() { return this.y; }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Position other = (Position) obj;
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Pos[" + x + ", " + y + "]";
  }

  @Override
  public int compareTo(Position o) {
    return (this.x == o.x) ? this.y - o.y : this.x - o.x;
  }

}
