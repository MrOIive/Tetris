import java.io.*;
import java.util.ArrayList;

public class HighScore {
  //since the score can only be in the one thousands so 4 digits
  final static int MAX_NUM_SIZE = 5;
  final static int MAX_STR_SIZE = 10;
  final static int INDEX_OF_NUMS = (10 + MAX_NUM_SIZE);

  public static String getHighScores() {
    try {
      BufferedReader in = new BufferedReader(new FileReader("HighScores"));
      String line;
      String ln = "";
      int count = 0;
      while ((line = in.readLine()) != null && count < 10) {
        count++;
        String str = "";
        //grabbing the date from the line in the file, index 0-start, index 10-the date is the length of 10
        String date = line.substring(0, 10);
        //getting the name in the line of the file, and all numbers end at the index 14
        String name = line.substring(INDEX_OF_NUMS);
        //name = name.replaceAll(" ", "<&nbsp<");
        //getting the number in the line of the file see method below to understand, and all numbers end at the index 14
        String lineNum = line.substring(10, INDEX_OF_NUMS);
        //lineNum = lineNum.replaceAll(" ", "<&nbsp<");
        //5 spaces, also if count equals 10 then it will a delete a space in between the numbering and the scores to make it look cleaner
        String space = "<&nbsp<<&nbsp<<&nbsp<<&nbsp<<&nbsp< ";
        if (count == 10)
          str = count + ". " + lineNum.replaceAll(" ", "<&nbsp<") + space + name.replaceAll(" ", "<&nbsp<") + space + date;
        else
          str = count + ". <&nbsp<" + lineNum.replaceAll(" ", "<&nbsp<") + space + name.replaceAll(" ", "<&nbsp<") + space + date;
        ln += "<br>" + str + "<br>";
      }
      in.close();
      return ln;
    } catch (Exception e) {return "Error #101 couldn't load scores";}
  }

  public static void setScore(int hs, String name) {
    boolean placedIt = false;
    ArrayList<String> list = new ArrayList<String>();
    BufferedReader in = null;
    try { 
      in = new BufferedReader(new FileReader("HighScores"));
      int lineNum;
      String line;
      //reading the file and placing it in an ArrayList
      while ((line = in.readLine()) != null) {
        //start at index 10 because the date length 10 and 14 because thats when the numbers end with the spaces
        String beforeLn = line.substring(10, INDEX_OF_NUMS);
        int io = beforeLn.indexOf(" ");
        //indexOf return -1 if it cant find the char
        if (io == -1)
          io = MAX_NUM_SIZE;
        //finding the number inside of the substring to pull out the number with the spaces
        lineNum = Integer.valueOf(beforeLn.substring(0, io));
        if (lineNum > hs || placedIt)
          list.add(list.size(), line);
        else {
          placedIt = true;
          //adding spacing for the scores to go in the file
          int numDigits = (MAX_NUM_SIZE - String.valueOf(hs).length());
          String blnks = "";
          for (int i = 0; i < numDigits; i++) {
            blnks += " ";
          }
          //adding spacing for the names to go in the file
          int namDigits = (MAX_STR_SIZE - name.length());
          String blnksNam = "";
          for (int i = 0; i < namDigits; i++) {
            blnksNam += " ";
          }

          String date = String.valueOf(java.time.LocalDate.now());
          String str = date + hs + blnks + name + blnksNam;
          list.add(list.size(), str);
          list.add(list.size(), line);
        }
      }
      if (!placedIt) {
        int numDigits = (MAX_NUM_SIZE - String.valueOf(hs).length());
        String blnks = "";
        for (int i = 0; i < numDigits; i++) {
          blnks += " ";
        }
        
        int namDigits = (MAX_STR_SIZE - name.length());
        String blnksNam = "";
        for (int i = 0; i < namDigits; i++) {
          blnksNam += " ";
        }
        
        String date = String.valueOf(java.time.LocalDate.now());
        String str = date + hs + blnks + name + blnksNam;
        list.add(list.size(), str);
      }
      in.close();
      //rewriting the file with the ArrayList but with the score and name in the file
      PrintWriter pw = new PrintWriter("HighScores");
      for (int i = 0; i < list.size(); i++) {
         pw.println(list.get(i));
      } 
      pw.close();
    } catch (Exception e) {e.printStackTrace();}
  }
}