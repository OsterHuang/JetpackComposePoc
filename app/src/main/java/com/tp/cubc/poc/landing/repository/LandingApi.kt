package com.tp.cubc.poc.landing.repository

import com.mbanking.cubc.login.repository.dataModel.LoginRequestBody
import com.tp.cubc.poc.landing.repository.dataModel.AccessTokenResponse
import com.tp.cubc.poc.landing.repository.dataModel.LoginResponseBodyResult
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LandingApi {
    // 未登入取得Token
    @POST("api/auth/accessToken")
    suspend fun accessToken(
        @Body requestBody: HttpRequestBody
    ): Response<HttpResponseBody<AccessTokenResponse>>

//    // 註冊裝置
//    @POST("api/device/registerDevice")
//    suspend fun registerDevice(
//        @Body registerDeviceRequest: RegisterDeviceRequest
//    ): Response<HttpResponseBody<Any>>
//
//    // 取得APP版本
//    @POST("api/app/queryVersion")
//    suspend fun queryVersion(
//        @Body queryVersionRequest: QueryVersionRequest
//    ): Response<HttpResponseBody<QueryVersionResponse>>
//
//    // 查詢系統參數
//    @POST("api/system/querySystemConfig")
//    suspend fun querySystemConfig(
//        @Body querySystemConfigRequest: QuerySystemConfigRequest
//    ): Response<HttpResponseBody<QuerySystemConfigResponse>>
//
//    // 查詢系統公告
//    @POST("api/system/querySystemContent")
//    suspend fun querySystemContent(
//        @Body apiRequest: ApiRequest
//    ): Response<HttpResponseBody<QuerySystemContentResponse>>
//
    // 登入
    @POST("api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequestBody
    ): Response<HttpResponseBody<LoginResponseBodyResult>>
//
//    // 查詢客戶資訊
//    @POST("api/person/queryCustInfo")
//    suspend fun queryCustInfo(
//        @Body queryCustInfoRequest: QueryCustInfoRequest
//    ): Response<HttpResponseBody<QueryCustInfoResponse>>
//
//    // 發送SMS OTP(登入後使用)
//    @POST("api/otp/otherSendOTP")
//    suspend fun otherSendOTP(
//        @Body otherSendOtpRequest: OtherSendOtpRequest
//    ): Response<HttpResponseBody<OtherSendOtpResponse>>
//
//    // 驗證SMS OTP(登入後使用)
//    @POST("api/otp/otherVerifyOTP")
//    suspend fun otherVerifyOTP(
//        @Body otherVerifyOtpRequest: OtherVerifyOtpRequest
//    ): Response<HttpResponseBody<Any>>
//
//    // 臨櫃照與人臉辨識比對(既有戶首登)
//    @POST("api/register/verifyFirstLoginWithFace")
//    suspend fun verifyFirstLoginWithFace(
//        @Body verifyFirstLoginWithFaceRequest: VerifyFirstLoginWithFaceRequest
//    ): Response<HttpResponseBody<Any>>
//
//    // 申辦人臉註冊(既有戶首登)
//    @POST("api/register/applyForFirstLogin")
//    suspend fun applyForFirstLogin(
//        @Body applyForFirstLoginRequest: ApplyForFirstLoginRequest
//    ): Response<HttpResponseBody<Any>>
//
//    // 更新fido狀態(快速登入)
//    @POST("api/person/updateFidoStatus")
//    suspend fun updateFidoStatus(
//        @Body updateFidoStatusRequest: UpdateFidoStatusRequest
//    ): Response<HttpResponseBody<Any>>
//
//    // 確認手機綁定狀態(快速登入)
//    @POST("api/device/bindDeviceStatus")
//    suspend fun bindDeviceStatus(
//        @Body bindDeviceStatusRequest: BindDeviceStatusRequest
//    ): Response<HttpResponseBody<BindDeviceStatusResponse>>
}
