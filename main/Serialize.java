package main;
import java.io.*;

/**
 * This class helps to serialize and deserialize Memento object and save into the file.       
 */

public class Serialize {
  
 private Object object;
 private String fileName = "Inventory.ser";
 
   public void serialzeMemento(Memento state){
	   
	   try
	      {
	         FileOutputStream fileOutputStream = new FileOutputStream(fileName);
	         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	         objectOutputStream.writeObject(state);         
	         objectOutputStream.close();
	         fileOutputStream.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	   
   }

   public Memento deSerialseMemento(){	 
	   
	   try
	      {
	         FileInputStream fileInputStream = new FileInputStream(fileName);
	         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	         object = objectInputStream.readObject();
	         objectInputStream.close();
	         fileInputStream.close();
	         return (Memento)object;
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	         
	      }catch(ClassNotFoundException c)
	      {
	          System.out.println("class not found");
	          c.printStackTrace();
	          return null;
	          
	       }   
   }
   
}