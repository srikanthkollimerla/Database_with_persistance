package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryDecoratorTest {

	public InventoryInterface inventory;
	
	@BeforeEach
	 void init() throws Exception {
		 inventory = new InventoryDecorator(new Inventory());
		 inventory.addBook(new Book("Insurgent", 100, 5));
		 inventory.addCopy("Insurgent", 15);
		 inventory.addBook(new Book("Divergent", 200, 20));
   }

	@Test
	void testAddBook() throws Exception {
		inventory.addBook(new Book("Four", 1200, 5));
		assertEquals(inventory.findPriceByID(3), 1200);
	}

	@Test
	void testAddCopy() throws Exception {
		inventory.addCopy("Insurgent", 15);
		assertEquals(inventory.findQuantityByName("Insurgent"), 35);
	}

	@Test
	void testChangePrice() throws Exception {
		inventory.changePrice("Insurgent", 2000);
		assertEquals(inventory.findPriceByName("Insurgent"), 2000);
	}

	@Test
	void testSellBook() throws Exception {
		inventory.sellBook("Insurgent");
		assertEquals(inventory.findQuantityByName("Insurgent"), 19);
	}
	
	@Test void testSaveState() throws Exception {
		 InventoryInterface invent2 = new InventoryDecorator(new Inventory());
	    invent2.addBook(new Book("book1",5,5));
	    
	    invent2.changePrice("book1", 10);
	   
	    invent2.saveState();
	    invent2.addCopy("book1", 100);
	    InventoryInterface invent = new InventoryDecorator(new Inventory());
	    invent.getState();
	    assertEquals(invent.findQuantityByName("book1"), 105);
	}
	
	

}
