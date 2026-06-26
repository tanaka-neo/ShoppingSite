package jp.co.aforce.beans;

public class History implements java.io.Serializable {

    private int historyId;
    private String memberId;
    private String productId;
    private int quantity;
    private java.sql.Timestamp purchaseDate;
    private String productName;
    

    public int getHistoryId() {
        return historyId;
    }
    public String getMemberId() {
        return memberId;
    }
    public String getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public java.sql.Timestamp getPurchaseDate() {
        return purchaseDate;
    }
    public String getProductName() {
		return productName;
	}
    
    
    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPurchaseDate(java.sql.Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public void setProductName(String productName) {
		this.productName = productName;
	}
}