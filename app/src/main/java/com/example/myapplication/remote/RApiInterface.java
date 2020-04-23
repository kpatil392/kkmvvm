package com.example.myapplication.remote;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RApiInterface {

    String SIGN_UP = "Signup";

//    @Headers({"Authorization:Basic YXdhZGhlc2hrdW1hcjQ2NUBnbWFpbC5jb206d2ViZGVza3lAMjAxOQ==",
//            "consumer_key:cs_e8b051fa4274a5a996069c85cde60c9ba42fdf4b"})
//    @POST("login")
//    Call<ResponseBody> userLogin(@Body RequestBody requestBody);

//    @POST("userRegistration")
//    Call<ResponseBody> signUp(@Body RequestBody requestBody);

    @POST("editProfile")
    Call<ResponseBody> editProfile(@Body RequestBody requestBody);

    @POST("city")
    Call<ResponseBody> getcitylist(@Body RequestBody requestBody);

    @POST("sidebar")
    Call<ResponseBody> getCategory();

    @POST("getCategoryByPost")
    Call<ResponseBody> getProductList(@Body RequestBody requestBody);

//    @Headers({"Authorization:Basic YXdhZGhlc2hrdW1hcjQ2NUBnbWFpbC5jb206d2ViZGVza3lAMjAxOQ==",
//            "consumer_key:cs_e8b051fa4274a5a996069c85cde60c9ba42fdf4b","Content-Type:application/json",
//            "cache-control:no-cache",
//            "cookie"+"cookie value will goes here"})

    @POST("getPostDetail")
    Call<ResponseBody> getProductDetail(@Body RequestBody requestBody);

    @POST("getAllUserByAddress")
    Call<ResponseBody> getSelectAddressList(@Body RequestBody requestBody);

    @POST("addUserAddress")
    Call<ResponseBody> userAddNewAddress(@Body RequestBody requestBody);

    @POST("getCart")
    Call<ResponseBody> getmyCart(@Body RequestBody requestBody);


   /* @Multipart
    @POST(SIGN_UP)
    Call<ResponseBody> signUp(@Part("username") RequestBody userName, @Part("email_address") RequestBody email,
                              @Part("Country_code") RequestBody countryCode, @Part("mobile") RequestBody mobile,
                              @Part("password") RequestBody password, @Part("dob") RequestBody dob,
                              @Part("age") RequestBody age, @Part("sex") RequestBody sex,
                              @Part("gender") RequestBody gender, @Part("art") RequestBody art,
                              @Part("lattitude") RequestBody lattitude, @Part("longitude") RequestBody longitude,
                              @Part("otp") RequestBody otp, @Part("user_role") RequestBody userRole,
                              @Part("images[]") RequestBody imagesArrayId, @Part List<MultipartBody.Part> arrayImage);
*/
   @POST("mailCustomer")
   Call<ResponseBody> mailCustomerService(@Body RequestBody requestBody);

    @POST("forgotPassword")
    Call<ResponseBody> forgotPassword(@Body RequestBody requestBody);

    @POST("removeItem")
    Call<ResponseBody> removeItem(@Body RequestBody requestBody);

    @POST("customerOrderListing")
    Call<ResponseBody> getOrderList(@Body RequestBody requestBody);

    @POST("customerOrderDetail")
    Call<ResponseBody> getOrderDetail(@Body RequestBody requestBody);


    @POST("updateCartItem")
    Call<ResponseBody> updateCartQuantity(@Body RequestBody requestBody);

    @POST("deleteUserAddress")
    Call<ResponseBody> deleteAddress(@Body RequestBody requestBody);

   
    @FormUrlEncoded
    @POST("userRegistration")
    Call<ResponseBody> signUp(@Field("first_name") String first_name,
                            @Field("last_name") String last_name,
                            @Field("dob") String dob,
                            @Field("user_email") String user_email,
                            @Field("city_id") String city_id,
                            @Field("password") String password,
                            @Field("gender") String gender,
                            @Field("deviceid") String deviceid,
                            @Field("devicetype") String devicetype,
                            @Field("facebook_login_id") String fbUserId);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> signIn(@Field("user_email") String first_name,
                            @Field("password") String last_name,
                            @Field("deviceid") String dob,
                            @Field("devicetype") String user_email);
    /*@FormUrlEncoded
    @POST("verifyFbSocialLogin")
    Call<ResponseBody> getFbLoginUserStatus(@Field("user_email") String strFbUserEmail,
                                          @Field("facebook_login_id") String strFbUserId,
                                          @Field("deviceid") String strFcmToken,
                                          @Field("devicetype") String strDeviceType);

    @POST("allDealSubCategories")
    Call<ResponseBody> getSubCategory(@Body RequestBody requestBody);
*/
}
