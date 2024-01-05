import java.awt.*;

public class Block {
  public int type;
  public Color color;
  public int x;
  public int y;
  
  public Block(int x, int y) {
    this.x = x;
    this.y = y;
    type = 0;
    color = Color.black;
  }

  public void setType(int t) {
    type = t;
    if (type == 1) {
      color = Color.yellow;
    } else if (type == 2) {
      color = Color.blue;
    } else if (type == 3) {
      color = Color.green;
    } else if (type == 4) {
     color = Color.red;
    } else if (type == 5) {
      color = Color.orange;
    } else if (type == 6) {
      color = Color.pink;
    } else if (type == 7) {
      color = Color.magenta;
    } else {
      color = Color.black;
    }
  }
}