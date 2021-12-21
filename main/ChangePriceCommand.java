package main;

/**
 * This class updates the price of the book in the inventory
 */

public class ChangePriceCommand extends Command implements java.io.Serializable {

	private String bookName;
	private Integer numberOfCopies;
	
	ChangePriceCommand(String bookName, Integer numberOfCopies){
	this.bookName = bookName; 
	this.numberOfCopies = numberOfCopies;
	}
	
	@Override
	public void execute(Inventory newInvent) {
		
		try {
		    newInvent.changePrice(bookName,numberOfCopies);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
