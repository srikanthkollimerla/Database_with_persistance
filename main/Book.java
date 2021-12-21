package main;

/**
 * 
 * This class contains all the methods that are associated with the Book object
 */
public class Book implements java.io.Serializable{

	private Integer  price, id, quantity;
	private String name;
	private static int count =0;
	
	Book(String name, Integer price, Integer quantity ){
		this.name = name;
		this.price = price;
		this.id = ++count;
		this.quantity = quantity;
	}
	
	public void changeQuantity(int quantity){
		this.quantity += quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
