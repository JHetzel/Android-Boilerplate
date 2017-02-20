package de.juliushetzel.boilerplate.data.network;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static final String REST_API_URL = "http://10.0.2.2:8080/";

    private static OkHttpClient.Builder sHttpClientBuilder;

    private static Retrofit.Builder sRetrofitBuilder;

    static {

        sHttpClientBuilder = new OkHttpClient.Builder();

        sRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(REST_API_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static <T> T createService(Class<T> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <T> T createService(Class<T> serviceClass, final String authenticationToken){
        if(authenticationToken != null){
            sHttpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();

                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("Authorization", authenticationToken)
                            .method(originalRequest.method(), originalRequest.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = sHttpClientBuilder.build();
        Retrofit retrofit = sRetrofitBuilder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
