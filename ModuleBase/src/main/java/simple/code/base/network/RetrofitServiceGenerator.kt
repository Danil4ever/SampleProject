package simple.code.base.network

import androidx.annotation.Nullable
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit


object RetrofitServiceGenerator {

    fun <S> createService(serviceClass: Class<S>, url: String): S {

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()

            .addCallAdapterFactory(ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(io()))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)

        return retrofitBuilder.baseUrl(url).build().create(serviceClass)
    }

    @Suppress("UNCHECKED_CAST")
    internal class ObserveOnMainCallAdapterFactory(val scheduler: Scheduler) :
        CallAdapter.Factory() {

        @Nullable
        override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, Observable<*>>? {
            if (getRawType(returnType) != Observable::class.java) {
                return null
            }

            val delegate = retrofit.nextCallAdapter(
                this, returnType,
                annotations
            ) as CallAdapter<Any, Observable<*>>

            return object : CallAdapter<Any, Observable<*>> {
                override fun adapt(call: Call<Any>): Observable<*> {
                    val o = delegate.adapt(call)
                    return o.observeOn(scheduler)
                }

                override fun responseType(): Type {
                    return delegate.responseType()
                }
            }
        }
    }

}