package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.io.IOException;

/**
 * This class helps in performing operations on the inventory object
 * and uses the methods of memonto to save and retrieve the state of
 * the inventory object
 *  
 */
public class Inventory implements InventoryInterface {

	private HashMap<Integer, Book> bookCollection;
	private Memento memento;
	private Integer numberOfOperations = 0;
	private final int THRESHOLD = 10;

	public Inventory() {
		bookCollection = new HashMap<Integer, Book>();
		memento = new Memento();
	}
	public void addBook(Book book){
		bookCollection.put(book.getId(),book);
		numberOfOperations+=1;
		if(numberOfOperations == THRESHOLD){
	    	saveState();
	    	numberOfOperations=0;
	    }	
	}
	
	   /**
	   * this method decreases the count of book by 1, if the book exists in the
	   * inventory object.
	   * @param: indicates the bookName (datatype: String)
	   */
	
	public void sellBook(String bookName) {
		Set<Integer> keys= bookCollection.keySet();
		for(Integer value: keys){
			Book book = bookCollection.get(value);
			if(book.getName().equals(bookName) && book.getQuantity() > 0){
				book.changeQuantity(-1);
				numberOfOperations += 1;
				if(numberOfOperations == THRESHOLD){
			    	saveState();
			    	numberOfOperations = 0;
			    }
				return ;
			}	
		}
	}
	
	/**
	 * this method increases the count of the books
	 * @param bookName: indicates the name of the book (datatype: String)
	 * @param number: indicates the number of copies of the book (datatype: Intger)
	 */
	public void addCopy(String bookName, Integer numberOfCopies ) {
		Set<Integer> keys= bookCollection.keySet();
		for(Integer value: keys){
			Book book = bookCollection.get(value);
			if(book.getName().equals(bookName) && book.getQuantity() > 0){
				book.changeQuantity(numberOfCopies);
				numberOfOperations += 1;
				if(numberOfOperations == THRESHOLD){
			    	saveState();
			    	numberOfOperations = 0;
			    }
				return ;
			}	
		}
		
	}
	
	/**
	 * this method is invoked to change the price of the book.
	 * @param bookName: name of the book (datatype: String)
	 * @param price: price of the book(datatype: Integer)
	 */
	
	public void changePrice(String bookName,Integer price) {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName)){
				book.setPrice(price);
				numberOfOperations+=1;
				if(numberOfOperations == THRESHOLD){
			    	saveState();
			    	numberOfOperations = 0;
			    }
				return ;
			}
		}
	}
	
	/**
	 * this method returns the price of the given book
	 */
	
	public Integer findPriceByName(String bookName) {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName)){
				return book.getPrice();
			}
		}
		return -1;
		
	}
	
	public Integer findQuantityByName(String bookName) {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName)){
				return book.getQuantity();
			}	
		}
		return -1;
	}
	
	public Integer findQuantityByID(Integer bookId) {
		for(Book book : bookCollection.values()){
			if(book.getId().equals(bookId)){
				return book.getQuantity();
			}	
		}
		return -1;
	}
	
	public Integer findPriceByID(Integer bookId) {
		for(Book book : bookCollection.values()){
			if(book.getId().equals(bookId)){
				return book.getPrice();
			}
		}
		return -1;
	}
	
	   /**
	   * this method is invoked in the following conditions:
	   * 	->when the client code invokes the saveState method
	   * 	-> when the number of command operations performed on the data base is 10.
	   * 
	   * this method then creates a new memonto to save the state of inventory and 
	   * deletes the command file safely and serializes the memonto object created
	   * 
	   */
	
	public void saveState(){
		Serialize careTaker = new Serialize();
		memento.saveState(bookCollection);
		careTaker.serialzeMemento(memento);
	}
	
	/**
	   * this method gets previous state of inventory by deserialization  
	   * and run the commands to set the inventory back to original state       
	   */

	public void getState() {
		Serialize careTaker = new Serialize();
		memento  = careTaker.deSerialseMemento();
		bookCollection = memento.getState();
	}
}