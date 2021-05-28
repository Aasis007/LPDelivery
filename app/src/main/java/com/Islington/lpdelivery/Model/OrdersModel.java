package com.Islington.lpdelivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrdersModel {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("delivery_details")
    @Expose
    private List<Deliverdetails> deliverdetails = null;


    public OrdersModel(String status, String message, List<Deliverdetails> deliverdetails) {
        this.status = status;
        this.message = message;
        this.deliverdetails = deliverdetails;
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

    public List<Deliverdetails> getDeliverdetails() {
        return deliverdetails;
    }

    public void setDeliverdetails(List<Deliverdetails> deliverdetails) {
        this.deliverdetails = deliverdetails;
    }

    public class Deliverdetails {

        @SerializedName("order_id")
        @Expose
        private String order_id;

        @SerializedName("order_date")
        @Expose
        private String order_date;

        @SerializedName("customer_id")
        @Expose
        private String customer_id;


        @SerializedName("p_id")
        @Expose
        private String p_id;


        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("order_type")
        @Expose
        private String order_type;


        @SerializedName("gas_name")
        @Expose
        private String gas_name;



        @SerializedName("extra_cost")
        @Expose
        private String extra_cost;



        @SerializedName("price")
        @Expose
        private String price;


        @SerializedName("qty")
        @Expose
        private String qty;

        @SerializedName("total_cost")
        @Expose
        private String total_cost;


        @SerializedName("notes")
        @Expose
        private String notes;


        @SerializedName("pName")
        @Expose
        private String pName;

        @SerializedName("delivery_address")
        @Expose
        private String delivery_address;

        @SerializedName("cName")
        @Expose
        private String cName;

        @SerializedName("cPhone")
        @Expose
        private String cPhone;

        @SerializedName("cAddress")
        @Expose
        private String cAddress;


        public Deliverdetails(String order_id, String order_date, String customer_id, String p_id, String status, String order_type, String gas_name, String extra_cost, String price, String qty, String total_cost, String notes, String pName, String delivery_address, String cName, String cPhone, String cAddress) {
            this.order_id = order_id;
            this.order_date = order_date;
            this.customer_id = customer_id;
            this.p_id = p_id;
            this.status = status;
            this.order_type = order_type;
            this.gas_name = gas_name;
            this.extra_cost = extra_cost;
            this.price = price;
            this.qty = qty;
            this.total_cost = total_cost;
            this.notes = notes;
            this.pName = pName;
            this.delivery_address = delivery_address;
            this.cName = cName;
            this.cPhone = cPhone;
            this.cAddress = cAddress;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getGas_name() {
            return gas_name;
        }

        public void setGas_name(String gas_name) {
            this.gas_name = gas_name;
        }

        public String getExtra_cost() {
            return extra_cost;
        }

        public void setExtra_cost(String extra_cost) {
            this.extra_cost = extra_cost;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getTotal_cost() {
            return total_cost;
        }

        public void setTotal_cost(String total_cost) {
            this.total_cost = total_cost;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getpName() {
            return pName;
        }

        public void setpName(String pName) {
            this.pName = pName;
        }

        public String getDelivery_address() {
            return delivery_address;
        }

        public void setDelivery_address(String delivery_address) {
            this.delivery_address = delivery_address;
        }

        public String getcName() {
            return cName;
        }

        public void setcName(String cName) {
            this.cName = cName;
        }

        public String getcPhone() {
            return cPhone;
        }

        public void setcPhone(String cPhone) {
            this.cPhone = cPhone;
        }

        public String getcAddress() {
            return cAddress;
        }

        public void setcAddress(String cAddress) {
            this.cAddress = cAddress;
        }
    }
}
