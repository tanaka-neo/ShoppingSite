package jp.co.aforce.beans;

public class Product implements java.io.Serializable{

    private String productId;
    private String productName;
    private int price;
    private int stock;
    private String description;
    private String imagePath;
    private int sweetness;
    private int sourness;
    private int berrySize;
    private String origin;
    private String volume;
    private int isDeleted;
    

	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public int getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	public String getDescription() {
		return description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public int getSweetness() {
		return sweetness;
	}
	public int getSourness() {
		return sourness;
	}
	public int getBerrySize() {
		return berrySize;
	}
	public String getOrigin() {
		return origin;
	}
	public String getVolume() {
		return volume;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	
	
	public void setProductId(String productId) {
		this.productId=productId;
	}
	public void setProductName(String productName) {
		this.productName=productName;
	}
	public void setPrice(int price) {
		this.price=price;
	}
	public void setStock(int stock) {
		this.stock=stock;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setImagePath(String imagePath) {
		this.imagePath=imagePath;
	}
	public void setSweetness(int sweetness) {
		this.sweetness=sweetness;
	}
	public void setSourness(int sourness) {
		this.sourness=sourness;
	}
	public void setBerrySize(int berrySize) {
		this.berrySize=berrySize;
	}
	public void setOrigin(String origin) {
		this.origin=origin;
	}
	public void setVolume(String volume) {
		this.volume=volume;	
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted=isDeleted;
	}
}