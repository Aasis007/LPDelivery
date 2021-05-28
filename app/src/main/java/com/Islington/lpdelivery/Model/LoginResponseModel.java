package com.Islington.lpdelivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponseModel {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("user_type")
    @Expose
    private String user_type;


    @SerializedName("user_data")
    @Expose
    private Userdata userdata;
    public LoginResponseModel(String status, String message, String user_type, Userdata userdata) {
        this.status = status;
        this.message = message;
        this.user_type = user_type;
        this.userdata = userdata;
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

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }

    public class Userdata {
        @SerializedName("id")
        @Expose
        private String id;


        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("phone")
        @Expose
        private String phone;

        @SerializedName("address")
        @Expose
        private String address;

        public Userdata(String id, String name, String email, String phone, String address) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

}
