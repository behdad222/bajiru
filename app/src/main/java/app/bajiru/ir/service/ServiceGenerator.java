package app.bajiru.ir.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.bajiru.ir.MainAplication;
import app.bajiru.ir.StaticFields;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
	private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

	private static Retrofit.Builder builder =
			new Retrofit.Builder()
					.baseUrl(StaticFields.SERVER_URL)
					.addConverterFactory(GsonConverterFactory.create());

	public static <S> S createServiceWithAccessToken(Class<S> serviceClass) {
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		httpClient.connectTimeout(30, TimeUnit.SECONDS);
		httpClient.readTimeout(30, TimeUnit.SECONDS);
		httpClient.writeTimeout(30, TimeUnit.SECONDS);
		httpClient.interceptors().clear();
		httpClient.addInterceptor(logging);
		httpClient.interceptors().add(new Interceptor() {
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException {
				Request original = chain.request();

				Request.Builder requestBuilder = original.newBuilder()
						.header(StaticFields.HEADER_AUTHORIZATION, MainAplication.token)
						.method(original.method(), original.body());

				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		});

		OkHttpClient client = httpClient.build();
		Retrofit retrofit = builder.client(client).build();
		return retrofit.create(serviceClass);
	}

	public static <S> S createServiceBasicAuthentication(Class<S> serviceClass) {
		httpClient.interceptors().clear();
		httpClient.interceptors().add(new Interceptor() {
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException {
				Request original = chain.request();

				Request.Builder requestBuilder = original.newBuilder()
						.method(original.method(), original.body());

				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		});

		OkHttpClient client = httpClient.build();
		Retrofit retrofit = builder.client(client).build();
		return retrofit.create(serviceClass);
	}
}
