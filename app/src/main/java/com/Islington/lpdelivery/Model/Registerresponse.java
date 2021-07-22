package com.Islington.lpdelivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Registerresponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("data")
    @Expose
    private List<UsersData> usersData;

    public Registerresponse(String status, String message, List<UsersData> usersData) {
        this.status = status;
        this.message = message;
        this.usersData = usersData;
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

    public List<UsersData> getUsersData() {
        return usersData;
    }

    public void setUsersData(List<UsersData> usersData) {
        this.usersData = usersData;
    }

    public class UsersData {
        @SerializedName("name")
        @Expose
        private String name;


        @SerializedName("phone")
        @Expose
        private String phone;


        @SerializedName("address")
        @Expose
        private String address;

        @SerializedName("email_id")
        @Expose
        private String email_id;

        @SerializedName("password")
        @Expose
        private String password;

        @SerializedName("id")
        @Expose
        private String id;

        public UsersData(String name, String phone, String address, String email_id, String password, String id) {
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.email_id = email_id;
            this.password = password;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


}
