package com.bagzag.app.data

import okhttp3.HttpUrl

object URLFactory {

    // server details
    private const val IS_LOCAL = true
    private const val SCHEME = "http"
//    private val HOST = if (IS_LOCAL) "192.168.1.61" else "skkyn.com"
private val HOST = if (IS_LOCAL) "10.0.2.2" else "skkyn.com"
    private val API_PATH = if (IS_LOCAL) "v1/" else "websitedata/api/v2/"
    private val PORT = if (IS_LOCAL) 8081 else 8081

    fun provideHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .port(PORT)
                .addPathSegments(API_PATH)
                .build()
    }

    // API Methods
    object Method {
        const val SIGNUP = "user/signup"
        const val HEADER = "api-key: bagzag-api-key"
    }

}
