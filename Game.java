import java.util.Scanner;
class Game
{
 public static void main(String[] args)
 {
  Board playerBoard = new Board(); //create a board for the player
  Board computerBoard = new Board(); //create a board for the computer
  
  //declare some integers for later (in the game loop)
  int rowGuess;
  int colGuess;
  int randomRow;
  int randomCol;
  
  boolean done = false;
  while(!done) //game loop
  {
   //Print both boards
   System.out.println("Their board: ");
   computerBoard.printBoard();
   System.out.println("Your board: ");
   playerBoard.printBoard();
   
   //Check if someone won
   if (computerBoard.checkForWin() && playerBoard.checkForWin())
   {
    System.out.println("Tie!");
    done = true;
    break;//exit the game loop
   }
   if (computerBoard.checkForWin())
   {
    System.out.println("You win!");
    done = true;
    break;
   }
   if (playerBoard.checkForWin())
   {
    System.out.println("They win!");
    done = true;
    break;
   }
   
   //Player's turn
   //Get user input for the row and column guess
   System.out.println("Your turn!");
   System.out.print("Guess a row: ");
   rowGuess = getGuess();
   System.out.print("Guess a column: ");
   colGuess = getGuess();
   while (computerBoard.visible[rowGuess][colGuess].equals(computerBoard.hidden[rowGuess][colGuess]))
   {
    //redo the guess for the user
    System.out.println("You already guessed that spot. Try again.");
    System.out.print("Guess a row: ");
    rowGuess = getGuess();
    System.out.print("Guess a column: ");
    colGuess = getGuess();
   }
   computerBoard.visible[rowGuess][colGuess] = computerBoard.hidden[rowGuess][colGuess]; //assign the hidden value to the corresponding visible value
   
   //Computer's turn
   //Calculate a random row and column for the computer guess
   System.out.println("Their turn!");
   randomRow = (int)(Math.random()*7);
   randomCol = (int)(Math.random()*7);
   while (playerBoard.visible[randomRow][randomCol].equals(playerBoard.hidden[randomRow][randomCol]))
   {
    //redo the guess for the computer
    randomRow = (int)(Math.random()*7);
    randomCol = (int)(Math.random()*7);
   }
   System.out.println("They guessed row " + (randomRow+1)); //+1 because the computer has to guess 0-6 but it is shown as 1-7
   System.out.println("They guessed column " + (randomCol+1));
   playerBoard.visible[randomRow][randomCol] = playerBoard.hidden[randomRow][randomCol]; //see line 57
   
  }
  System.out.println("Game Over");
 }
 
 /**
  = user input if it is between 1 and 7
   otherwise it gets new user input
 */
 static int getGuess()
 {
  Scanner s = new Scanner(System.in);
  int guess = 1;
  try
  {
    guess = s.nextInt();
  }
  catch(Exception e)
  {
    getGuess();
  }
  while (guess < 1 || guess > 7)
  {
   System.out.println("Invalid input. Try again. (Number must be between 1 and 7)");
   guess = s.nextInt();
  }
  return guess - 1; //-1 because the user has to input a integer between 1-7, but the array is indexed 0-6
 }
}
