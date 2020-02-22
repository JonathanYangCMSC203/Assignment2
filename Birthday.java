import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * This program will allow users to input the gifts they want to buy and then print out a receipt.
 * @author Jonathan Yang
 *
 */
public class Birthday {//Driver class
	//fields
	private static Toy a = new Toy();//toy class
	
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args){	//main
		double totalCost = 0;
		JOptionPane.showMessageDialog(null, "Welcome to the Toy Company to choose gifts for young children\nProgrammer: Jonathan Yang");//Welcome message
		do{
			 totalCost += giftInput();//This method allows users to add gifts
		}while(askForAnotherGift());//This loops until user doesn't want another gift. 
		orderReceipt(totalCost);//This creates and prints out the receipt. 
	}//end main
	
	/**
	 * The method adds the name, age, and choice of toy then returns 
	 * @param totalCost this is the cost of the toy, card, balloon chosen and the previous choices from the loop before.
	 * @return totalCost returns the cost of the toy, card, balloon chosen and the previous choices from the loop before.
	 */
	private static double giftInput() {
		//fields
		boolean loopV = false;//this variable is to keep the loop running until tokOkforChild() returns true
		String name = "";//Name of child
		
		
		while(!loopV){//This loops until the user determines if the toy is age appropriate.
			
			name = nameInput();//This method allow users input the name and capitalize the first letter of the name. 
			ageInput();//This method allow users to input the age. Then it will determine if the age is valid.
			toyInput();//This method allows users to input choice of toy. Then determine if the toy is valid.
			if(toyOkForChild()){//This method determine if the choice of toy is age-appropriate for the child. If it isn't, the user is given a choice to buy the gift regardless.
				a.setCost(a.getToy());
				loopV = true;
			}
		}
		a.addCard(JOptionPane.showInputDialog("Do you want a card with that gift? Yes or No."));//prompt user to add card
		a.addBalloon(JOptionPane.showInputDialog("Do you want a Balloon with the gift? Yes or No."));//prompt user to add balloon
		JOptionPane.showMessageDialog(null, "The gift for " + name + a);//prints the message of the gift you just bought 
		return a.getCost();//Returns the cost of the gift
	}//end giftInput
	
	/**
	 * Inputs the name and capitalize the letter of the names.
	 * @return the name of the child with the first and last name capitalized.
	 */
	private static String nameInput() {
		//fields
		String name;//name of child
		
		name = JOptionPane.showInputDialog("Enter the name of the child.");//Prompts user to add name of child
		
		if ((name == null) || (name.trim().length() == 0)) {
		       return name;//If no string, return the name.
		    }
			name = name.toLowerCase();//Lowercase all the words
		    char[] c = name.trim().toCharArray();//turns name to charr variable stored in an array.
		    c[0] = Character.toUpperCase(c[0]);//Uppercase the first letter
		    for (int i = 0; i < c.length; i++) {//loops until another word is found to be capitalized
		       if (c[i] == ' ' && (i + 1) < c.length) {
		         c[i + 1] = Character.toUpperCase(c[i + 1]);
		       }
		       if (c[i] == '-' && (i + 1) < c.length) {
		         c[i + 1] = Character.toUpperCase(c[i + 1]);
		       }
		       if (c[i] == '\'' && (i + 1) < c.length) {
		         c[i + 1] = Character.toUpperCase(c[i + 1]);
		       }
		    }
		    return new String(c);//returns the name of the child with capitalized letters
	}//end nameInput
	
	/**
	 * Inputs the age and determines if the age is valid.
	 */
	private static void ageInput() {
		//fields
		boolean loopV = false;//Loop variable
		String ageStr = "";//Age stored in a string
		
		while(!loopV){//Loops until an appropriate age is found
			ageStr = JOptionPane.showInputDialog("How old is the child?");//prompt user for age
			if(isInteger(ageStr)){//true if ageStr is valid for int variables.
				a.setAge(Integer.parseInt(ageStr)); //sets ageStr as age
				if(a.getAge() >= 0)//ends loop if age is above 0
					loopV = true;
				else
					JOptionPane.showMessageDialog(null, "Age cannot be a negative number.");//prints user that age cannot be a negative number
			}
			if(!isInteger(ageStr))//false if ageStr is not an integer number
				JOptionPane.showMessageDialog(null, "This is not a valid number. Please enter a valid integer number.");//prints to user that the age inputted is not a valid integer number.
		}		
	}//end ageInput
	
	/**
	 * Inputs the choice of toy and determines if the toy is either a plushie, book, or blocks.
	 */
	private static void toyInput() {
		//fields
		boolean loopV = false;;//loop variable

		while(!loopV)//loops until either a plushie, blocks, or book is chosen.
		{
			a.setToy(JOptionPane.showInputDialog("Choose a toy: a plushie, blocks, or a book."));//prompt user to enter a toy
			if(a.getToy().equals("plushie") || a.getToy().equals("book") || a.getToy().equals("blocks"))//ends loop if chosen toy is valid
				loopV = true;
			else
				JOptionPane.showMessageDialog(null, "Invalid Input. Must be a Plushie, Blocks, or a book");//prints to user that the toy is invalid and the loop continues.
		}
	}//end toyInput
	
	/**
	 * Determines if the choice of toy is age appropriate
	 * @return true if the toy is age appropriate or if the user wants to add the toy even if it isn't. False if the user wants to enter a new toy when it is not age appropriate.
	 */
	private static boolean toyOkForChild() {
		//fields
		boolean loopV = false;//loop variable
		String toyCheck; //Stores yes no response
		
		while(!loopV)//loops until the toy is age appropriate or if the user wishes to add the toy even if it is not age approrpiate.
		{
			if(!a.ageOK())
			{
				toyCheck = JOptionPane.showInputDialog(a.getToy() + " is not age appropriate. Would you like to enter a new gift? Yes or no");//Prompt user to add the toy if it is not age appropriate
				if(toyCheck.toLowerCase().equals("no"))//User wishes to add the toy.
					return true;
				else if(toyCheck.toLowerCase().equals("yes"))//User doesn't wish to add toy so user must go around a new loop to add a new toy.
					return false;
				else
					JOptionPane.showMessageDialog(null, "INVALID CHOICE. Try again");//User didn't choose yes or no.
			}
			else if(a.ageOK())//Toy is already age appropriate and can end loop.
				return true;
		}
		JOptionPane.showMessageDialog(null, "ERROR: You are not suppose to see this.");//Prints user that you should not be able to see this message normally.
		return true;//A way to safely end method.
	
	}//end toyOkForChild
	
	/**
	 * This method ask the user if he wants to buy more gifts. 
	 * @return true to continue the loop to buy more gifts. False to end the loop to get a receipt
	 */
	private static boolean askForAnotherGift() {
		//fields
		boolean loopV = false;//loop variable. 
		String askGift;//store yes or no response
		
		while(!loopV){//loops until user makes a valid yes or no choice. 
			askGift = JOptionPane.showInputDialog("Do you want to enter another gift? Enter Yes or no.");//Prompts user if he wants to add more gifts
			if(askGift.toLowerCase().equals("no"))//Ends main loop to print order Receipt
				return false;
			else if(askGift.toLowerCase().equals("yes"))//loops again to buy more gifts
				return true;
			else
				JOptionPane.showMessageDialog(null, "INVALID CHOICE. Try again");//User did not add a valid choice
		}
		JOptionPane.showMessageDialog(null, "ERROR: You are not suppose to see this.");//User are not suppose to see this message.
		return false;//a way to safely end method.
		
	}//end askForAnotherGift
	
	/**
	 * This method prints the receipt for all the gifts bought
	 * @param totalCost is the cost of all gifts bought in a loop.
	 */
	public static void orderReceipt(double totalCost){
		//fields
		Random rand = new Random();//random class
		DecimalFormat dollar = new DecimalFormat("#,##0.00");//Turns cost into dollar format.
		String receiptMessage = "";
		
		//message of the receipt
		receiptMessage += "\nThe total cost of your order is $" + dollar.format(totalCost); 
		receiptMessage += "\nThe Order number is " + rand.nextInt(10000);
		receiptMessage += "\nProgrammer Name: Jonathan Yang"; 
		
		JOptionPane.showMessageDialog(null, receiptMessage);//Prints out the receipt.
	}//end orderReceipt
	
	/**
	 * This method checks if string is a valid integer.
	 * @param s is the age string.
	 * @return true if the string is a valid integer for conversion
	 */
	public static boolean isInteger(String s) {
	    try { //returns false if string is not an integer
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) { 
	        return false;
	    }
	    return true;//returns true string is not an integer
	}//end isInteger
}//end class
