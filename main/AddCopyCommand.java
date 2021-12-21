package main;

/**
 * This command class is invoked to add additional copies of a book  
 */

public class AddCopyCommand extends Command implements java.io.Serializable {

	private String bookName;
	private Integer numberOfCopies;
	
	AddCopyCommand(String bookName, Integer newNumberOfCopies){
		this.bookName = bookName; 
		this.numberOfCopies = newNumberOfCopies;
	}
	
	@Override
	public void execute(Inventory newInvent) {
		
		try {
		    newInvent.addCopy(bookName,numberOfCopies);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
