/*
Name:Conor Benson
Date: march 14 2023
Description: Simulates a game of Yathzee!
 */
package games;
import java.util.Arrays;
import java.util.Scanner;
public class Yathzee {
	


  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int[] diceArray = new int[5];
    int[] scoreCard = new int[13];
    int turn = 0;
    int category = 0;
    for (turn = 1; turn <= 13; turn++) {
    	//loop continues for 13 total turns
      System.out.print("Turn " + turn + ": ");
      rollDice(diceArray);
      for (int attempt = 1; attempt <= 3; attempt++) {
        System.out.print("\nReroll attempt " + attempt + ":");
        reroll(diceArray);
      }
      System.out.println("\nWhich item would you like to score on your scorecard?:");
      System.out.println("0=aces 1=twos 2=threes 3=fours 4=fives 5=sixes");
      System.out.println("6=three of a kind 7=four of a kind 8=full house");
      System.out.println("9=small straight 10=large straight 11=yahtzee 12=chance");
      category = scan.nextInt();
      /*checks to make sure a value isn't already in that array index. If there already is a value, the 
      below code restarts that turn. */
      if (repeatError(scoreCard, category) == false) {
        turn = turn - 1; 
        continue;
      }
      scoreDice(scoreCard, diceArray, category);
      System.out.println("Scorecard: " + Arrays.toString(scoreCard));
    }
    scan.close();
    int total = totalScore(scoreCard);
    System.out.println("Total: " + total);

  }
  /*
   Method takes an array as an input and outputs a array of length i with random numbers from 1-6
   (method simulates rolling five die).
   
   */
  public static void rollDice(int[] diceArray) {
    for (int i = 0; i <= diceArray.length - 1; i++) {
      diceArray[i] = (int)(Math.random() * 6 + 1);
    }
    String arrayList = Arrays.toString(diceArray);
    System.out.print(arrayList);

  }
  /*
   Method asks the user how many dice they want to re-roll, 
   then asks which dice they want to re-roll then re-rolls those dice. 
   */
  public static void reroll(int[] diceArray) {
    Scanner scan = new Scanner(System.in);
    System.out.println(" How many dice would  you like to reroll?");
    int numOfDice = scan.nextInt();
    if (numOfDice > 0) {
      //skips re-rolling if user inputs that they want to re-roll 0 die.
      System.out.print("Indicate the dice number(s) 1-5 you would lie to reroll\n");
     
      for (int i = 1; i <= numOfDice; i++) {
        int index = scan.nextInt()-1;
        diceArray[index] = (int)(Math.random() * 6 + 1);
        //re-rolls number of index i.
      }
    }
    System.out.print("Dice: " + Arrays.toString(diceArray));
  }
  /*
  Method calculates the sum of an array of numbers.
   */
  public static int SumAllDice(int[] diceArray) {
    int sumAll = 0;
    for (int i = 0; i <= diceArray.length - 1; i++) {
      sumAll += diceArray[i];
    }
    return sumAll;
  }
  /*
  Method calculates the sum of all the numbers in an array that have the same value as an 
  inputed number. 
   */
  public static int SumOfDice(int[] diceArray, int faceValue) {
    int sumOf = 0;
    for (int i = 0; i <= diceArray.length - 1; i++) {
      if (diceArray[i] == faceValue) {
        sumOf += diceArray[i];
      }
    }
    return sumOf;
  }
  /*
  Method stores a users dice rolls into a category of their choice and does the necessary calculations for each
  category. ex: if the users picks category 0 the method adds up all aces the user has and stores that value in 
  score card index 0.
   */
  public static void scoreDice(int[] scoreCard, int[] diceArray, int category) {
    if (category == 0) {
      scoreCard[0] = SumOfDice(diceArray, 1);
    } else if (category == 1) {
      scoreCard[1] = SumOfDice(diceArray, 2);
    } else if (category == 2) {
      scoreCard[2] = SumOfDice(diceArray, 3);
    } else if (category == 3) {
      scoreCard[3] = SumOfDice(diceArray, 4);
    } else if (category == 4) {
      scoreCard[4] = SumOfDice(diceArray, 5);
    } else if (category == 5) {
      scoreCard[5] = SumOfDice(diceArray, 6);
    } else if (category == 6) {
      scoreCard[6] = SumAllDice(diceArray);
    } else if (category == 7) {
      scoreCard[7] = SumAllDice(diceArray);
    } else if (category == 8) {
      scoreCard[8] = 25;
    } else if (category == 9) {
      scoreCard[9] = 30;
    } else if (category == 10) {
      scoreCard[10] = 40;
    } else if (category == 11) {
      scoreCard[11] = 50;
    } else if (category == 12) {
      scoreCard[12] = SumAllDice(diceArray);
    }
  }
  /*
  Method calculates the sum of all numbers in an Array.
   */
  public static int totalScore(int[] scoreCard) {
    int total = 0;
    for (int i = 0; i <= scoreCard.length - 1; i++) {
      total += scoreCard[i];
    }
    return total;
  }
  /*
  Method checks to make sure a user is not attempting to input score into an already used a score card index
  (already "full"). 
  */
  public static boolean repeatError(int[] scoreCard, int category) {
    if (scoreCard[category] == 0) {
      return true;
    } else {
      System.out.println("item already filled");
      return false;
    }
  }
}
