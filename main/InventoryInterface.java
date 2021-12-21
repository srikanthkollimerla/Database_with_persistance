package main;
public interface InventoryInterface {
	public void addBook(Book book);
	public void sellBook(String bookName);
	public void addCopy(String bookName, Integer NumberOfCopy );
	public void changePrice(String bookName,Integer newPrice);
	public Integer findPriceByName(String bookName);
	public Integer findQuantityByName(String bookName);
	public Integer findQuantityByID(Integer bookId);
	public Integer findPriceByID(Integer bookId);
	public void saveState();
	public void getState();
}
