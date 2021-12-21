package main;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * this class invokes the various commands to perform the 
 * certain tasks on the inventory data base.
 *
 */
public class InventoryDecorator implements InventoryInterface {
	
	private Inventory inventory;
	private FileInputStream fileInputStream ;
	private ArrayList<Command> commandList;
	private Memento memento;
	private Command command;
	private Object object;
	final private String inventoryFileName = "Inventory.ser";
	final private String commandFileName = "Command.ser";
	final private String fileName = "Command.ser";
	private File file;
	
	public InventoryDecorator(Inventory inventory) {
		this.inventory = inventory;
		commandList = new ArrayList<Command>();
		memento = new Memento();
		file = new File(inventoryFileName);
	}
	
	private void serializeCommand() {
		try {
	         FileOutputStream fileOutputStream = new FileOutputStream(fileName,true);
	         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	         objectOutputStream.writeObject(command);
	         objectOutputStream.close();
	         fileOutputStream.close();
	      } catch(Exception i) {
	    	  i.printStackTrace();
	      }
	}
	
	public void addBook(Book book) {
		command= new AddBookCommand(book);
		command.execute(inventory);
	    serializeCommand();
	}
	
	public void addCopy(String bookName, Integer numberOfCopies) {
		command = new AddCopyCommand(bookName, numberOfCopies);
		command.execute(inventory);	
		serializeCommand();
	}
	
	public void changePrice(String bookName, Integer price) {
		command = new ChangePriceCommand(bookName, price);
		command.execute(inventory);
		serializeCommand();
	}
	
	public void sellBook(String bookName) {
		command = new SellBookCommand(bookName);
		command.execute(inventory);
		serializeCommand();
	}
	
	public Integer findPriceByName(String bookName)  {
		try {
			return inventory.findPriceByName(bookName);	
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return -1;
	}
	
	public Integer findPriceByID(Integer bookId)  {
		try {
			return inventory.findPriceByID(bookId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Integer findQuantityByName(String bookName)  {
		try {
			return inventory.findQuantityByName(bookName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Integer findQuantityByID(Integer bookId)  {
		try { 
			return inventory.findQuantityByID(bookId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	   * This method gets the commands from file  
	   * and runs on inventory object       
	   */
	
	private void deserializeCommands(Inventory invent) {
		try {
			fileInputStream = new FileInputStream(commandFileName);
			while (true) {
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
		    commandList.add((Command) input.readObject());
		    //check for 2 or more commands
		  }  
		} catch (EOFException e) {
			try {	
				fileInputStream.close();
			}catch(IOException i) {
				i.printStackTrace();
			}
		} catch(IOException | ClassNotFoundException i) {
			i.printStackTrace();
		}
		for(Command command : commandList) {
	         command.execute(invent);
		}
	}
	
	private boolean deleteCommandFile() {
		 File file = new File(commandFileName);
		 boolean result= false;
			file.delete();
			try {
				file.createNewFile();
				result = true;
			} catch (IOException e) {
				result= false;
				e.printStackTrace();
			}
			return result;
	}
	
	@Override
	public void saveState() {		
		 inventory.saveState();
		 deleteCommandFile();
	}

	@Override
	public void getState() {	
		inventory.getState();
		deserializeCommands(inventory);
	}
}