package com.Islington.lpdelivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VendorModel {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("products")
    @Expose
    private List<VendorData> vendorData = null;


    public VendorModel(String status, String message, List<VendorData> vendorData) {
        this.status = status;
        this.message = message;
        this.vendorData = vendorData;
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

    public List<VendorData> getVendorData() {
        return vendorData;
    }

    public void setVendorData(List<VendorData> vendorData) {
        this.vendorData = vendorData;
    }

    public class VendorData {


        @SerializedName("p_id")
        @Expose
        private String p_id;

        @SerializedName("vendor_id")
        @Expose
        private String vendor_id;



        @SerializedName("vendorName")
        @Expose
        private String vendorName;



        @SerializedName("lat")
        @Expose
        private String lat;



        @SerializedName("lon")
        @Expose
        private String lon;

        public VendorData(String p_id,String vendor_id, String vendorName, String lat, String lon) {
            this.p_id = p_id;
            this.vendor_id = vendor_id;
            this.vendorName = vendorName;
            this.lat = lat;
            this.lon = lon;
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

        public String getVendorName() {
            return vendorName;
        }

        public void setVendorName(String vendorName) {
            this.vendorName = vendorName;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }
}
