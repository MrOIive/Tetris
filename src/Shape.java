import java.util.Random;

public class Shape {
  public static int waitTime = 500;
  public static int[] b1Pos = new int[2];
  public static int[] b2Pos = new int[2];
  public static int[] b3Pos = new int[2];
  public static int[] b4Pos = new int[2];
  public static int shape = 0;

  public static Block[][] blocks = new Block[10][20];
  public static int[][] newBlocks = new int[10][20];
  final static int BLOCKS_HEIGHT = 20;
  final static int BLOCKS_WIDTH = 10;
  final static int BLOCKS_SIZE = 25;
  final static int EMPTY = 0;
  
  Shape() {
    int addX = 0;
    int addY = 0;
    for (int y = 0; y < BLOCKS_HEIGHT; y++) {
      for (int x = 0; x < BLOCKS_WIDTH; x++) {
        newBlocks[x][y] = 0;
        blocks[x][y] = new Block(addX, addY);
        addX += 25;
      }
      addX = 0;
      addY += 25;
    }
    Frame.panel.redrawWindow();
    randomizeBlock();
    
    Thread thread = new Thread() {
      @Override
      public void run() {
        while (!Panel.GAMEOVER) {
          try {         
            Thread.sleep(waitTime);
          } catch (InterruptedException e) {}
          if (Panel.GAMEOVER) 
            break;
          if (!Frame.down) {
          	checkBlockStop();
          	Frame.panel.redrawWindow();
          }
        }
      }
    };
    thread.start();
  }

  static void checkBlockStop() {
    if (blockStop()) {
    	/*
      int countX = 0;
      for (int y = 0; y < BLOCKS_HEIGHT; y++) {
        for (int x = 0; x < BLOCKS_WIDTH; x++) {
          if (blocks[x][y].type != 0)
            countX++;
        }
        if (countX == BLOCKS_WIDTH) {
          Panel.score += 100;
          Panel.label.setText(Panel.score + "");
          for (int i = BLOCKS_HEIGHT-1; i > 0; i--) {
            for (int j = 0; j < BLOCKS_WIDTH; j++) {
              newBlocks[j][i] = blocks[j][i-1].type;
            }
          }
          waitTime -= 5;
        }
        countX = 0;
      }
      for (int y = 0; y < BLOCKS_HEIGHT; y++) {
        for (int x = 0; x < BLOCKS_WIDTH; x++) {
          blocks[x][y].type = newBlocks[x][y];
        }
      }
		*/
      randomizeBlock();
    } else {
      moveDown();
    }
  }

  public static void moveDown() {
    blocks[b1Pos[0]][b1Pos[1]].setType(EMPTY);
    newBlocks[b1Pos[0]][b1Pos[1]] = EMPTY;
    
    blocks[b2Pos[0]][b2Pos[1]].setType(EMPTY);
    newBlocks[b2Pos[0]][b2Pos[1]] = EMPTY;
    
    blocks[b3Pos[0]][b3Pos[1]].setType(EMPTY);
    newBlocks[b3Pos[0]][b3Pos[1]] = EMPTY;
    
    blocks[b4Pos[0]][b4Pos[1]].setType(EMPTY);
    newBlocks[b4Pos[0]][b4Pos[1]] = EMPTY;

    
    b1Pos[1]++;
    blocks[b1Pos[0]][b1Pos[1]].setType(shape);
    newBlocks[b1Pos[0]][b1Pos[1]] = shape;
    
    b2Pos[1]++;
    blocks[b2Pos[0]][b2Pos[1]].setType(shape);
    newBlocks[b2Pos[0]][b2Pos[1]] = shape;
    
    b3Pos[1]++;
    blocks[b3Pos[0]][b3Pos[1]].setType(shape);
    newBlocks[b3Pos[0]][b3Pos[1]] = shape;
    
    b4Pos[1]++;
    blocks[b4Pos[0]][b4Pos[1]].setType(shape);
    newBlocks[b4Pos[0]][b4Pos[1]] = shape;

  }

  public static void moveLeft() {
    blocks[b1Pos[0]][b1Pos[1]].setType(EMPTY);
    newBlocks[b1Pos[0]][b1Pos[1]] = EMPTY;

    blocks[b2Pos[0]][b2Pos[1]].setType(EMPTY);
    newBlocks[b2Pos[0]][b2Pos[1]] = EMPTY;

    blocks[b3Pos[0]][b3Pos[1]].setType(EMPTY);
    newBlocks[b3Pos[0]][b3Pos[1]] = EMPTY;

    blocks[b4Pos[0]][b4Pos[1]].setType(EMPTY);
    newBlocks[b4Pos[0]][b4Pos[1]] = EMPTY;


    b1Pos[0]--;
    blocks[b1Pos[0]][b1Pos[1]].setType(shape);
    newBlocks[b1Pos[0]][b1Pos[1]] = shape;

    b2Pos[0]--;
    blocks[b2Pos[0]][b2Pos[1]].setType(shape);
    newBlocks[b2Pos[0]][b2Pos[1]] = shape;

    b3Pos[0]--;
    blocks[b3Pos[0]][b3Pos[1]].setType(shape);
    newBlocks[b3Pos[0]][b3Pos[1]] = shape;

    b4Pos[0]--;
    blocks[b4Pos[0]][b4Pos[1]].setType(shape);
    newBlocks[b4Pos[0]][b4Pos[1]] = shape;
    Frame.panel.redrawWindow();
  }

  public static void moveRight() {
    blocks[b1Pos[0]][b1Pos[1]].setType(EMPTY);
    newBlocks[b1Pos[0]][b1Pos[1]] = EMPTY;

    blocks[b2Pos[0]][b2Pos[1]].setType(EMPTY);
    newBlocks[b2Pos[0]][b2Pos[1]] = EMPTY;

    blocks[b3Pos[0]][b3Pos[1]].setType(EMPTY);
    newBlocks[b3Pos[0]][b3Pos[1]] = EMPTY;

    blocks[b4Pos[0]][b4Pos[1]].setType(EMPTY);
    newBlocks[b4Pos[0]][b4Pos[1]] = EMPTY;


    b1Pos[0]++;
    blocks[b1Pos[0]][b1Pos[1]].setType(shape);
    newBlocks[b1Pos[0]][b1Pos[1]] = shape;

    b2Pos[0]++;
    blocks[b2Pos[0]][b2Pos[1]].setType(shape);
    newBlocks[b2Pos[0]][b2Pos[1]] = shape;

    b3Pos[0]++;
    blocks[b3Pos[0]][b3Pos[1]].setType(shape);
    newBlocks[b3Pos[0]][b3Pos[1]] = shape;

    b4Pos[0]++;
    blocks[b4Pos[0]][b4Pos[1]].setType(shape);
    newBlocks[b4Pos[0]][b4Pos[1]] = shape;
    Frame.panel.redrawWindow();
  }
  static void rotate() {  
	 int[][] bsPos = {{b1Pos[0], b1Pos[1]}, {b2Pos[0], b2Pos[1]}, {b3Pos[0], b3Pos[1]}, {b4Pos[0], b4Pos[1]}};
	 blocks[b1Pos[0]][b1Pos[1]].setType(EMPTY);
	 newBlocks[b1Pos[0]][b1Pos[1]] = EMPTY;

	 blocks[b2Pos[0]][b2Pos[1]].setType(EMPTY);
	 newBlocks[b2Pos[0]][b2Pos[1]] = EMPTY;

	 blocks[b3Pos[0]][b3Pos[1]].setType(EMPTY);
	 newBlocks[b3Pos[0]][b3Pos[1]] = EMPTY;

	 blocks[b4Pos[0]][b4Pos[1]].setType(EMPTY);
	 newBlocks[b4Pos[0]][b4Pos[1]] = EMPTY;
	    
	 int origin = -1;
	 switch (shape) {
	   case 1:
		   origin = 2;
		   break;
	   case 2:
		   origin = 2;
		   break;
	   case 3:
		   origin = 1;
		   break;
	   case 5:
		   origin = 2;
		   break;
	   case 6:
		   origin = 2;
		   break;
	   case 7:
		   origin = 1;
		   break;
	 }
     for (int i = 0; i < 4; i++) {
    	 if (origin != i && origin != -1) {
    		 int[] tempPos = {bsPos[i][1] - bsPos[origin][1], bsPos[i][0] - bsPos[origin][0]};
    		 tempPos[1] = -tempPos[1];
    		 tempPos[0] += bsPos[origin][0];
    		 tempPos[1] += bsPos[origin][1];
    		 
    		 bsPos[i][0] = tempPos[0];
    		 bsPos[i][1] = tempPos[1];
    	 }
     }
     
     b1Pos[0] = bsPos[0][0];
     b1Pos[1] = bsPos[0][1];
     b2Pos[0] = bsPos[1][0];
     b2Pos[1] = bsPos[1][1];
     b3Pos[0] = bsPos[2][0];
     b3Pos[1] = bsPos[2][1];
     b4Pos[0] = bsPos[3][0];
     b4Pos[1] = bsPos[3][1];
     
     blocks[b1Pos[0]][b1Pos[1]].setType(shape);
     newBlocks[b1Pos[0]][b1Pos[1]] = shape;

     blocks[b2Pos[0]][b2Pos[1]].setType(shape);
     newBlocks[b2Pos[0]][b2Pos[1]] = shape;

     blocks[b3Pos[0]][b3Pos[1]].setType(shape);
     newBlocks[b3Pos[0]][b3Pos[1]] = shape;

     blocks[b4Pos[0]][b4Pos[1]].setType(shape);
     newBlocks[b4Pos[0]][b4Pos[1]] = shape;
     Frame.panel.redrawWindow();
  }
 
  static boolean blockUnderBlock(int x, int y) {
    y++;
    int[][] bsPos = {{b1Pos[0], b1Pos[1]}, {b2Pos[0], b2Pos[1]}, {b3Pos[0], b3Pos[1]}, {b4Pos[0], b4Pos[1]}};
    for (int i = 0; i < 4; i++) {
      if (bsPos[i][0] == x && bsPos[i][1] == y)
        return true;
    }
    return false;
  }
  static boolean blockStop() {
    try {
      if (blocks[b1Pos[0]][b1Pos[1]+1].type != 0 && !blockUnderBlock(b1Pos[0], b1Pos[1])) {
        return true;
      } else if (blocks[b2Pos[0]][b2Pos[1]+1].type != 0 && !blockUnderBlock(b2Pos[0], b2Pos[1])) {
        return true;
      } else if (blocks[b3Pos[0]][b3Pos[1]+1].type != 0 && !blockUnderBlock(b3Pos[0], b3Pos[1])) {
        return true;
      } else if (blocks[b4Pos[0]][b4Pos[1]+1].type != 0 && !blockUnderBlock(b4Pos[0], b4Pos[1])) {
        return true;
      } 
      return false;
    } catch (Exception e) {
      return true;
    }
    //^checks if out of bounds if it is then that means in shape is at the bottom
  }

 static void randomizeBlock() {
    Random r = new Random();
    shape = (r.nextInt(6) + 1);

    if (shape == 1) {
      b1Pos[0] = 3;
      b2Pos[0] = 4;
      b3Pos[0] = 5;
      b4Pos[0] = 6;

      b1Pos[1] = 0;
      b2Pos[1] = 0;
      b3Pos[1] = 0;
      b4Pos[1] = 0;
      
    } else if(shape == 2) {
      b1Pos[0] = 4;
      b2Pos[0] = 4;
      b3Pos[0] = 5;
      b4Pos[0] = 6;

      b1Pos[1] = 0;
      b2Pos[1] = 1;
      b3Pos[1] = 1;
      b4Pos[1] = 1;
    } else if (shape == 3) {
      b1Pos[0] = 3;
      b2Pos[0] = 4;
      b3Pos[0] = 5;
      b4Pos[0] = 5;

      b1Pos[1] = 1;
      b2Pos[1] = 1;
      b3Pos[1] = 1;
      b4Pos[1] = 0;
    } else if (shape == 4) {
      b1Pos[0] = 4;
      b2Pos[0] = 4;
      b3Pos[0] = 5;
      b4Pos[0] = 5;

      b1Pos[1] = 0;
      b2Pos[1] = 1;
      b3Pos[1] = 1;
      b4Pos[1] = 0;
    } else if (shape == 5) {
      b1Pos[0] = 4;
      b2Pos[0] = 5;
      b3Pos[0] = 5;
      b4Pos[0] = 6;

      b1Pos[1] = 1;
      b2Pos[1] = 1;
      b3Pos[1] = 0;
      b4Pos[1] = 0;
    } else if (shape == 6) {
      b1Pos[0] = 3;
      b2Pos[0] = 4;
      b3Pos[0] = 4;
      b4Pos[0] = 5;

      b1Pos[1] = 0;
      b2Pos[1] = 0;
      b3Pos[1] = 1;
      b4Pos[1] = 1;
    } else {
      b1Pos[0] = 4;
      b2Pos[0] = 5;
      b3Pos[0] = 5;
      b4Pos[0] = 6;

      b1Pos[1] = 1;
      b2Pos[1] = 1;
      b3Pos[1] = 0;
      b4Pos[1] = 1;
    }

    blocks[b1Pos[0]][b1Pos[1]].setType(shape);
    newBlocks[b1Pos[0]][b1Pos[1]] = shape;
    blocks[b2Pos[0]][b2Pos[1]].setType(shape);
    newBlocks[b2Pos[0]][b2Pos[1]] = shape;
    blocks[b3Pos[0]][b3Pos[1]].setType(shape);
    newBlocks[b3Pos[0]][b3Pos[1]] = shape;
    blocks[b4Pos[0]][b4Pos[1]].setType(shape);
    newBlocks[b4Pos[0]][b4Pos[1]] = shape;
  }
}