package com.sample.khadamatfani.api

import com.sample.khadamatfani.api.response.DataResponse
import com.sample.khadamatfani.api.response.OtpResponse
import com.sample.khadamatfani.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET(HttpRequestsUrl.CATEGORIES)
    suspend fun categories(): DataResponse<Category>

    @GET(HttpRequestsUrl.PROVINCES)
    suspend fun provinces(): DataResponse<Province>

    @GET(HttpRequestsUrl.COOPERATIONS)
    suspend fun cooperations(): DataResponse<Same>

    @GET(HttpRequestsUrl.PMTHODS)
    suspend fun pmethods(): DataResponse<Same>

    @GET(HttpRequestsUrl.WORKING_HOURS)
    suspend fun workingHours(): DataResponse<Same>

    @GET(HttpRequestsUrl.POST)
    suspend fun posts(): DataResponse<Post>

    @GET(HttpRequestsUrl.WORKING_EXPERIENCES)
    suspend fun workingExperiences(): DataResponse<Same>

    @GET(HttpRequestsUrl.CITIES)
    suspend fun cities(@Path("province") province: Int): DataResponse<Province>

    @GET(HttpRequestsUrl.VERIFY_OTP)
    suspend fun confirmOTP(@Path("code") code: Int): Response<OtpResponse>

    @GET(HttpRequestsUrl.SEARCH)
    suspend fun search(@Query("q") q: String): DataResponse<Post>

    @GET(HttpRequestsUrl.CATEGORIES_ITEM)
    suspend fun getCategoriesPosts(@Path("category") category: Int): DataResponse<Category>

    @GET(HttpRequestsUrl.SINGLE_POST)
    suspend fun getSinglePost(@Path("post") post: Int): DataResponse<Post>

    @GET(HttpRequestsUrl.USER)
    suspend fun getUserProfile(): Response<User>

    @GET(HttpRequestsUrl.RECENT)
    suspend fun getRecent(): DataResponse<Post>

    @FormUrlEncoded
    @POST(HttpRequestsUrl.LOGIN)
    suspend fun login(@Field("phone") value: String): Response<OtpResponse>

    @FormUrlEncoded
    @POST(HttpRequestsUrl.USER)
    suspend fun updateUserProfile(@Field("name") name: String, @Field("phone") phone: String, @Field("location") location: String): Response<OtpResponse>

    @FormUrlEncoded
    @POST(HttpRequestsUrl.RECENT)
    suspend fun sendRecent(@Field("post_id") post_id: Int): Response<String>

    @FormUrlEncoded
    @POST(HttpRequestsUrl.DELETE_POST)
    suspend fun deletePost(@Field("post_id") post_id: Int): Response<OtpResponse>

    @FormUrlEncoded
    @POST(HttpRequestsUrl.POST)
    suspend fun createPost(
        @Field("category_id") categoryId: Int,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("p_c") p_c: String,
        @Field("working_experiences_id") working_experiences_id: Int,
        @Field("cooperation_id") cooperation_id: Int,
        @Field("pmethod_id") pmethod_id: Int,
        @Field("workinghours_id") workinghours_id: Int,
        @Field("insurance") insurance: Boolean,
        @Field("remote") remote: Boolean,
        @Field("military_service") military_service: Boolean,
        ): Response<OtpResponse> // use OtpResponse only for message and status

    @FormUrlEncoded
    @POST(HttpRequestsUrl.UPDATE_POST)
    suspend fun updatePost(
        @Field("post_id") postId: Int,
        @Field("category_name") categoryName: String,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("p_c") p_c: String,
        @Field("working_experiences_name") workingExperiencesName: String,
        @Field("cooperation_name") cooperationName: String,
        @Field("pmethod_name") pmethodName: String,
        @Field("workinghours_name") workinghoursName: String,
        @Field("insurance") insurance: Boolean,
        @Field("remote") remote: Boolean,
        @Field("military_service") military_service: Boolean,
        ): Response<OtpResponse> // use OtpResponse only for message and status
}