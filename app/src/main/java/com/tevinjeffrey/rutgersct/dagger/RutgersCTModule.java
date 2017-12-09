package com.tevinjeffrey.rutgersct.dagger;

import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;
import com.tevinjeffrey.rmp.common.RMPModule;
import com.tevinjeffrey.rutgersct.BuildConfig;
import com.tevinjeffrey.rutgersct.RutgersCTApp;
import com.tevinjeffrey.rutgersct.utils.AndroidMainThread;
import com.tevinjeffrey.rutgersct.utils.BackgroundThread;
import com.tevinjeffrey.rutgersct.utils.PreferenceUtils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module (includes = {
    DataModule.class,
    RMPModule.class
})
public class RutgersCTModule {

  private static final long CONNECT_TIMEOUT_MILLIS = 15000;
  private static final long READ_TIMEOUT_MILLIS = 20000;

  private static OkHttpClient.Builder getUnsafeOkHttpClient() {
    try {
      // Create a trust manager that does not validate certificate chains
      final TrustManager[] trustAllCerts = new TrustManager[] {
          new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                java.security.cert.X509Certificate[] chain,
                String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                java.security.cert.X509Certificate[] chain,
                String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new java.security.cert.X509Certificate[] { };
            }
          }
      };

      // Install the all-trusting trust manager
      final SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      // Create an ssl socket factory with our all-trusting manager
      final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.sslSocketFactory(sslSocketFactory);
      builder.hostnameVerifier(new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
          return true;
        }
      });

      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
      builder.addInterceptor(interceptor);

      return builder;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Provides
  @AndroidMainThread
  public Scheduler provideAndroidMainThread() {
    return AndroidSchedulers.mainThread();
  }

  @Provides
  @BackgroundThread
  public Scheduler provideBackgroundThread() {
    return Schedulers.io();
  }

  @Provides
  public Bus providesEventBus() {
    return new Bus();
  }

  @Provides
  public Gson providesGson() {
    return new GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .create();
  }

  @Provides
  public OkHttpClient providesOkHttpClient(UserAgentInterceptor userAgentInterceptor) {
    OkHttpClient.Builder client = new OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

    if (BuildConfig.DEBUG) {
      client = getUnsafeOkHttpClient();
    }

    client.addNetworkInterceptor(userAgentInterceptor);
    return client.build();
  }

  @Provides
  public OkHttpClient.Builder providesOkHttpClientBuilder(UserAgentInterceptor userAgentInterceptor) {
    OkHttpClient.Builder client = new OkHttpClient.Builder();

    if (BuildConfig.DEBUG) {
      client = getUnsafeOkHttpClient();
    }

    client.addNetworkInterceptor(userAgentInterceptor);
    return client;
  }

  @Provides
  public PreferenceUtils providesPreferenceUtils(Context context) {
    return new PreferenceUtils(context);
  }

  @Provides
  public UserAgentInterceptor providesUserAgentInterceptor(Context context) {
    String str = context.getString(context.getApplicationInfo().labelRes);
    StringBuilder sb = new StringBuilder();
    sb.append(str)
        .append("/")
        .append(BuildConfig.APPLICATION_ID)
        .append(" ")
        .append("(")
        .append(BuildConfig.VERSION_NAME)
        .append("; Android ")
        .append(Build.VERSION.SDK_INT)
        .append(")");
    return new UserAgentInterceptor(sb.toString());
  }

  @Provides Context provideContext(RutgersCTApp application) {
    return application.getApplicationContext();
  }

  /* This interceptor adds a custom User-Agent. */
  public class UserAgentInterceptor implements Interceptor {

    private final String userAgent;

    public UserAgentInterceptor(String userAgent) {
      this.userAgent = userAgent;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
      Request originalRequest = chain.request();
      Request requestWithUserAgent = originalRequest.newBuilder()
          .header("User-Agent", userAgent)
          .build();
      return chain.proceed(requestWithUserAgent);
    }
  }
}
