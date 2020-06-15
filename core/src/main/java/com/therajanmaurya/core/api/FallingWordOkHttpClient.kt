package com.therajanmaurya.core.api

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyStore
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object FallingWordOkHttpClient {

    val fallingWordOkHttpClient: OkHttpClient
        get() {

            val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls(0)
                    }
                })

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory

                val trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers = trustManagerFactory.trustManagers
                if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                    throw IllegalStateException(
                        "Unexpected default trust managers:" +
                                Arrays.toString(trustManagers)
                    )
                }
                val trustManager = trustManagers[0] as X509TrustManager

                //Set SSL certificate to OkHttpClient Builder
                okHttpBuilder.sslSocketFactory(sslSocketFactory, trustManager)

                okHttpBuilder.hostnameVerifier { _, _ -> true }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

            //Enable Full Body Logging
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            //Setting Timeout 120 Seconds
            okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
            okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
            okHttpBuilder.writeTimeout(60, TimeUnit.SECONDS)

            //Interceptor :> Full Body Logger and ApiRequest Header
            okHttpBuilder.addInterceptor(logger)

            return okHttpBuilder.build()
        }
}
