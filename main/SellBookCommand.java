package main;

/**
 * This class used to execute sellBook method on inventory and serialize it to file.
 */

public class SellBookCommand extends Command implements java.io.Serializable {

	private String bookName;
	
	SellBookCommand(String bookName){
	this.bookName = bookName; 
	}
	
	@Override
	public void execute(Inventory newInvent) {
		
		try {
			  newInvent.sellBook(bookName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
