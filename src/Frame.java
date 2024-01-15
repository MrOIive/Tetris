import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Frame extends JFrame implements KeyListener {

  final static int FRAME_WIDTH = 500;
  final static int FRAME_HEIGHT = 500;

  public static JLabel image;
  ImageIcon icon;

  public static boolean makeFrame = true;
  public static Panel panel;
  public static boolean canPress = false;
  
  public static boolean down = false;
  public static boolean right = false;
  public static boolean left = false;

  Frame() {
    if (makeFrame) {
      icon = new ImageIcon("T2Background.jpg");
    
      panel = new Panel();
      image = new JLabel();

      image.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
      Image imageimg = icon.getImage();
      imageimg = imageimg.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, java.awt.Image.SCALE_SMOOTH);
      ImageIcon ii = new ImageIcon(imageimg);
      image.setIcon(ii);
      image.setOpaque(true);

      this.setTitle("Tetris");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      this.setLocationRelativeTo(null);
      this.setResizable(false);
      this.setIconImage(new ImageIcon("Ticon.png").getImage());
      this.setFocusable(true);
      this.addKeyListener(this);
      this.add(panel);
      this.pack();
      this.setVisible(true);
      this.add(image);
      getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));
      makeFrame = false;
      
      Thread t1 = new Thread() {
    	  @Override
    	  public void run() {
      		while (!Panel.GAMEOVER) {
      			System.out.print("");
      			if (right && canGoR()) {
      				Shape.moveRight();
      				try {
      					Thread.sleep(100);
      				} catch (InterruptedException e) {}
      			} else if (left && canGoL()) {
      				Shape.moveLeft();
      				try {
      					Thread.sleep(100);
      				} catch (InterruptedException e) {}
      			}
      		}
    	  }
      };
      Thread t2 = new Thread() {
    	 @Override
    	  public void run() {
    		 while (!Panel.GAMEOVER) {
    		 	System.out.print("");
    		 	if (down) {
    		 		Shape.checkBlockStop();
    		 		Panel.score++;
    		 		Panel.label.setText(Panel.score + "");
    		 		repaint();
    		 		try {
    		 			Thread.sleep(40);
    		 		} catch (InterruptedException e) {}
    		 	} 
    		 }
    	 }
      };
      t1.start();
      t2.start();
    }
  }

  void makeTLayout() {
    getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {
	  switch (e.getKeyCode()) {
      	case KeyEvent.VK_LEFT: 
      		left = false;
      		break;
      	case KeyEvent.VK_RIGHT:
      		right = false;
      		break;
      	case KeyEvent.VK_DOWN:
      		down = false;
      		break;
	  }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (canPress && !Panel.GAMEOVER) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT: 
          left = true;
          break;
        case KeyEvent.VK_RIGHT:
          right = true;
          break;
        case KeyEvent.VK_DOWN:
        	down = true;
          break;
        case KeyEvent.VK_UP:
        	Shape.rotate();
          break;
      }
    }
  }
  boolean canGoR() {
    int[][] bsPos = {{Shape.b1Pos[0], Shape.b1Pos[1]}, {Shape.b2Pos[0], Shape.b2Pos[1]}, {Shape.b3Pos[0], Shape.b3Pos[1]}, {Shape.b4Pos[0], Shape.b4Pos[1]}};
    for (int i = 0; i < 4; i++) {
      try {
        if (Shape.blocks[bsPos[i][0]+1][bsPos[i][1]].getType() != 0 && !findEqual(bsPos[i][0]+1, bsPos[i][1], bsPos)) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }
  boolean findEqual(int x, int y, int[][] bsPos) {
	  for (int i = 0; i < 4; i++) {
		  if (bsPos[i][0] == x && bsPos[i][1] == y) {
			  return true;
		  }
	  }
	  return false;
  }
  boolean canGoL() {
    int[][] bsPos = {{Shape.b1Pos[0], Shape.b1Pos[1]}, {Shape.b2Pos[0], Shape.b2Pos[1]}, {Shape.b3Pos[0], Shape.b3Pos[1]}, {Shape.b4Pos[0], Shape.b4Pos[1]}};
    for (int i = 0; i < 4; i++) {
      try {
        if (Shape.blocks[bsPos[i][0]-1][bsPos[i][1]].getType() != 0 && !findEqual(bsPos[i][0]-1, bsPos[i][1], bsPos)) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }
}