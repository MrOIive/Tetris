import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Panel extends JPanel implements MouseListener {

  final static int FRAME_WIDTH = 500;
  final static int FRAME_HEIGHT = 500;
  final static int PANEL_WIDTH = 375;
  final static int PANEL_WIDTH_START = 125;
  
  Image backgroundImage;
  static JLabel label;
  static JTextField textField;
  static JLabel TFLabel;

  public boolean LBScreen = true;
  public static String name;
  public static int score = 0;
  static boolean makePanel = true;
  public static boolean GAMEOVER = false;

  final static int BLOCKS_HEIGHT = 20;
  final static int BLOCKS_WIDTH = 10;
  final static int BLOCKS_SIZE = 25;
  
  public Panel() {
    if (makePanel) {
      this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
      this.addMouseListener(this);
      this.setLayout(null);

      backgroundImage = new ImageIcon("tBackground.jpeg").getImage();
    
      label = new JLabel("<html>" + HighScore.getHighScores() + "<html>");
      label.setFont(new Font("Consolas", Font.BOLD, 15));
      label.setBounds(0, 75, FRAME_WIDTH, FRAME_HEIGHT - 100);
      label.setVerticalAlignment(JLabel.TOP);
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setForeground(Color.WHITE);

      textField = new JTextField();
      textField.setBackground(Color.WHITE);
      textField.setBounds(275, 25, 200, 25);
      textField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (textField.getText().length() != 0 && textField.getText().length() <= 10) {
            name = textField.getText();
            makeTLayout();
          }
        }
      });

      TFLabel = new JLabel("Enter your name max 10 characters:");
      TFLabel.setBounds(25, 12, 250, 50);
      TFLabel.setForeground(Color.WHITE);
      TFLabel.setFont(new Font("Courier", Font.BOLD, 12));
    
      this.add(TFLabel);
      this.add(label);
      this.add(textField);

      makePanel = false;
    }
  }

  void makeTLayout() {
    Main.makeTLayout();
    LBScreen = false;
    TFLabel.setText("");
    label.setBounds(0, 0, 250, 50);
    label.setText(score + "");
    label.setVerticalAlignment(JLabel.CENTER);
    label.setFont(new Font("Courier", Font.PLAIN, 35));
    label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED));
    textField.setBounds(25, -100, 250, -50);
    textField.setEnabled(false);
    this.setBounds(PANEL_WIDTH_START, 0, 250, FRAME_HEIGHT);
    this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
  
    new Shape();
    Frame.canPress = true;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g); 
    Graphics2D g2D = (Graphics2D) g;
    if (LBScreen) {
      g2D.drawImage(backgroundImage, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    } else {
      g2D.drawImage(backgroundImage, 0, 0, PANEL_WIDTH, FRAME_HEIGHT, null);
      for (int y = 0; y < BLOCKS_HEIGHT; y++) {
        for (int x = 0; x < BLOCKS_WIDTH; x++) {
          int bx = Shape.blocks[x][y].x;
          int by = Shape.blocks[x][y].y;
          Color color = Shape.blocks[x][y].color;
          g2D.setColor(color);
          g2D.fillRect(bx, by, bx + BLOCKS_SIZE, by + BLOCKS_SIZE);
        }
      }
    }
  }

  public void redrawWindow() {
    repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {}
  
  @Override
  public void mousePressed(MouseEvent e) {}
  
  @Override
  public void mouseReleased(MouseEvent e) {}
  
  @Override
  public void mouseEntered(MouseEvent e) {}
  
  @Override
  public void mouseExited(MouseEvent e) {}
}