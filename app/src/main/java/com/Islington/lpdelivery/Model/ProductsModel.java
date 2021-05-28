package com.Islington.lpdelivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsModel {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("products")
    @Expose
    private List<ProductsData> productsData = null;

    public ProductsModel(String status, String message, List<ProductsData> productsData) {
        this.status = status;
        this.message = message;
        this.productsData = productsData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductsData> getProductsData() {
        return productsData;
    }

    public void setProductsData(List<ProductsData> productsData) {
        this.productsData = productsData;
    }

    public class ProductsData {
        private String Image;


        @SerializedName("p_id")
        @Expose
        private String p_id;

        @SerializedName("vendor_id")
        @Expose
        private String vendor_id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("price")
        @Expose
        private String price;

        @SerializedName("stock")
        @Expose
        private int stock;


        public ProductsData(String image, String p_id, String vendor_id, String name, String price, int stock) {
            Image = image;
            this.p_id = p_id;
            this.vendor_id = vendor_id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }

}
