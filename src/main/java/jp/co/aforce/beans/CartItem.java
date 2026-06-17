package jp.co.aforce.beans;

public class CartItem implements java.io.Serializable{

	private Product product;
	private int quantity;
	
	public Product getProduct() {
		return product;
	}
	public int  getQuantity() {
		return quantity;
	}
	
	public void setProduct(Product product) {
		this.product=product;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
}
