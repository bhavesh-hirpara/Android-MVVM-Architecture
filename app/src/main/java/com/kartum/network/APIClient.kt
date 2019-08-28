package com.kartum.network

import android.content.Context
import com.kartum.apputils.Constant
import com.kartum.apputils.Debug
import com.kartum.apputils.RequestParamsUtils
import com.kartum.apputils.Utils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*
import javax.security.cert.CertificateException


internal object APIClient {
    private const val baseURL: String = "https://reqres.in/"
//    private const val baseURL: String = "http://trichordal-staging.azurewebsites.net/api/"

//    fun getBaseUrl(): String {
//        if(Debug.SANDBOX_API_URL){
//            return baseURL
//        }else{
//            return baseURL
//        }
//    }

    @Throws(IOException::class)
    fun newRequestRetrofit(context: Context): Retrofit {
        Debug.e("BASE URL" , baseURL)
        val authToken = "Bearer "+ Utils.getUserAuthToken(context)
        val okHttpClient = getUnsafeOkHttpClient(context)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .header(RequestParamsUtils.CONTENT_TYPE, Constant.APP_JSON)
                            .method(original.method(), original.body())
                            .build()
                    val response = chain.proceed(request)
                    //String(response.body().bytes())
                    response
                }
                .build()
        /*val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .header(RequestParamsUtils.AUTHORIZATION,authToken)
                            .header(RequestParamsUtils.CONTENT_TYPE, Constant.APP_JSON)
                            .method(original.method(),original.body())
                            .build()
                    chain.proceed(request)
                }
                .build()*/
        val builder = Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Throws(IOException::class)
    fun newRequestRetrofit(): Retrofit {
        val builder = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()

    }

    private fun getUnsafeOkHttpClient(context: Context): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<out X509Certificate>? {
                    return arrayOf()
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.getSocketFactory()

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String, session: SSLSession): Boolean {
                    return true
                }
            })

            /* builder.addInterceptor(object : Interceptor {
                 override fun intercept(chain: Interceptor.Chain): Response {
                     val request = chain.request();
                     val response = chain.proceed(request)

                     if (response.isSuccessful) {
                         val str = response.body()

                         val res = String(str!!.bytes())


                         try {

                             var mCommonDataModel: CommonDataModel = Gson().fromJson(JSONObject(res).toString(), object :
                                     TypeToken<CommonDataModel?>() {}.type)!!

                             if (mCommonDataModel.authOK.equals(Constant.FALSE) && mCommonDataModel.errorcode.equals("503")) {
                                 Utils.logout(context)
                                 return response
                             }
                         } catch (e: java.lang.Exception) {
                             e.printStackTrace()

                         }
                     }

                     return response
                 }

             })
 */
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

}