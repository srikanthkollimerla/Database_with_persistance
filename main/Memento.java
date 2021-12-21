package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class used to set and retrieve the states of inventory object
 */
 

class Memento implements java.io.Serializable{

	protected HashMap<Integer, Book> bookCollection;
	
	protected void saveState(HashMap<Integer, Book> newBookCollection){
		this.bookCollection = new HashMap<Integer, Book>(newBookCollection);	
	}
	
	protected HashMap<Integer, Book> getState(){
		return this.bookCollection;
	}
}
