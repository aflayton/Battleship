public class Board
{
 public String[][] visible = new String[7][7]; //2-d array of Strings that represent the board that is visible to the player
 public String[][] hidden = new String[7][7]; //2-d array of Strings that holds where the hits and misses are for the board
 
 public Board()
 {
  for (int i = 0; i < 7; i++) //iterating over each row 
  {
   for (int j = 0; j < 7; j++) //iterating over each column
   {
    this.visible[i][j] = "O"; //populate the visible board with O's
   }
  }
  for (int i = 0; i < 7; i++)
  {
   for (int j = 0; j < 7; j++)
   {
    this.hidden[i][j] = "M"; //populate the hidden board with M's
   }
  }
  this.placeHorizontalShip();
  this.placeVerticalShip();
 }
 
 /**
  Places the horizontal ship on the board in the hidden 2-d array
  Calculates random integers, randomRow and randomCol, for the row and column
  Assigns "H" to hidden[randomRow][randomCol], hidden[randomRow][randomCol+1], hidden[randomRow][randomCol+2] 
 */
 public void placeHorizontalShip()
 {
  int randomRow = (int)(Math.random()*7);
  int randomCol = (int)(Math.random()*5); // 0-4 to prevent the horizontal ship from going off the board
  
  this.hidden[randomRow][randomCol] = "H";
  this.hidden[randomRow][randomCol+1] = "H";
  this.hidden[randomRow][randomCol+2] = "H";
 }
 
 /**
  Places the vertical ship on the board in the hidden 2-d array
  Calculates random integers, randomRow and randomCol, for the row and column
  Checks if hidden[randomRow][randomCol], hidden[randomRow+1][randomCol], hidden[randomRow+2][randomCol] is equal to "H" already
   -if true, it recursively calls the function to recalculate a random row and column
   -otherwise, it assigns "H" to the values in question (see line 44)
 */
 public void placeVerticalShip()
 {
  int randomRow = (int)(Math.random()*5); //0-4 to prevent the vertical ship from going off the board
  int randomCol = (int)(Math.random()*7);
  
  if (this.hidden[randomRow][randomCol].equals("H") || this.hidden[randomRow+1][randomCol].equals("H") || this.hidden[randomRow+2][randomCol].equals("H")) //check to see if a ship already occupies the area
  {
   this.placeVerticalShip();
  }
  else
  {
   this.hidden[randomRow][randomCol] = "H";
   this.hidden[randomRow+1][randomCol] = "H";
   this.hidden[randomRow+2][randomCol] = "H";
  }
 }
 /**
  Prints each element in the visible 2-d array of the board
 */
 public void printBoard()
 {
  for (int i = 0; i < 7; i++)
  {
   for (int j = 0; j < 7; j++)
   {
    System.out.print(this.visible[i][j] + "\t");
   }
   System.out.println();
  }
 }
 /**
  = true if there are 6 H's in the visible 2-d array of the board
  = false otherwise
 */
 public boolean checkForWin()
 {
  int count = 0;
  for (int i = 0; i < 7; i++)
  {
   for (int j = 0; j < 7; j++)
   {
    if (this.visible[i][j].equals("H"))
    {
     count++;
    }
   }
  }
  if (count == 6)
  {
   return true;
  }
  else
  {
   return false;
  }
 }
}