package com.pb.puenteblancovet.dto.response;
public class OrderItemResponse {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double subtotal;
    private String productDescription;
    private String productCategory;
    private Double discount;
    private Double totalPrice;
    private String supplierName;
    private String productCode; 
    private String productOrigin; 

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(String productOrigin) {
        this.productOrigin = productOrigin;
    }

    public void calculateSubtotal() {
        if (this.price != null && this.quantity != null) {
            this.subtotal = this.price * this.quantity;
        }
    }

    public void applyDiscount() {
        if (this.discount != null && this.subtotal != null) {
            this.totalPrice = this.subtotal - (this.subtotal * this.discount / 100);
        } else {
            this.totalPrice = this.subtotal;
        }
    }

    @Override
    public String toString() {
        return "OrderItemResponse{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subtotal=" + subtotal +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                ", supplierName='" + supplierName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productOrigin='" + productOrigin + '\'' +
                '}';
    }
}