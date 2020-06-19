package com.beyondworlds.wanandroid.net

import com.beyondworlds.wanandroid.net.bean.Artical
import com.beyondworlds.wanandroid.net.bean.DataList
import com.beyondworlds.wanandroid.net.bean.User
import com.beyondworlds.wanandroid.net.bean.BaseResponse
import retrofit2.http.*

interface ApiService {
    /**
     * 获取首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArtical(@Path("page") page: Int): BaseResponse<DataList<Artical>>

    /**
     * 登录
     * 必须加 @FormUrlEncoded注解
     * 请求头Content-Type的值为 multipart/form-data
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): BaseResponse<User>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") userName: String,
        @Field("password") passWord: String,
        @Field("repassword") rePassWord: String
    ): BaseResponse<User>

    /**
     * 获取收藏文章列表
     */
    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page: Int): BaseResponse<DataList<Artical>>

    /**
     * 收藏站内文章
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): BaseResponse<String>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") originId: Int): BaseResponse<String>
}