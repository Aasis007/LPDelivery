package com.Islington.lpdelivery.Retrofit;

import com.Islington.lpdelivery.Model.LoginResponseModel;
import com.Islington.lpdelivery.Model.OrdersModel;
import com.Islington.lpdelivery.Model.ProductsModel;
import com.Islington.lpdelivery.Model.Registerresponse;
import com.Islington.lpdelivery.Model.RequestOrderResponse;
import com.Islington.lpdelivery.Model.VendorModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers("x-api-key: pu@7_3w@6")
    @POST("login")
    Call<LoginResponseModel> login(@Body RequestBody body);



    @Headers("x-api-key: pu@7_3w@6")
    @POST("register")
    Call<Registerresponse> register(@Body RequestBody body);



    @Headers("x-api-key: pu@7_3w@6")
    @GET("getAllProduct")
    Call<ProductsModel> getproducts();


    @Headers("x-api-key: pu@7_3w@6")
    @GET("getVendorByProduct")
    Call<VendorModel> getVendorByproduct (@Query("name") String name);


    @Headers("x-api-key: pu@7_3w@6")
    @GET("getProductById")
    Call<ProductsModel> getproddesc (@Query("p_id") String p_id);


    @Headers("x-api-key: pu@7_3w@6")
    @POST("addOrder")
    Call<RequestOrderResponse> sendorder (@Body RequestBody body);

    @Headers("x-api-key: pu@7_3w@6")
    @GET("getVendorProduct")
    Call<ProductsModel> getvendorproducts (@Query("vendor_id") String vendor_id);



    @Headers("x-api-key: pu@7_3w@6")
    @POST("addProduct")
    Call<RequestOrderResponse> addproduct (@Body RequestBody body);


    @Headers("x-api-key: pu@7_3w@6")
    @GET("getVendorOrders")
    Call<OrdersModel> getvendororders (@Query("uid") String uid,
                                       @Query("status") String status);


    @Headers("x-api-key: pu@7_3w@6")
    @POST("approveOrder")
    Call<RequestOrderResponse> approveorder (@Body RequestBody body);



}
