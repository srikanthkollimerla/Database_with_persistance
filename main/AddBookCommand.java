package main;

/**
 * This command class is used to perform addBook method on the inventory object.
 */

public class AddBookCommand extends Command implements java.io.Serializable {

	private Book book;
	
	public AddBookCommand(Book book){
		this.book = book; 
	}
	
	@Override
	public void execute(Inventory newInventory) {
		newInventory.addBook(book);
	}

}
