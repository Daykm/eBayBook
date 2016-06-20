package com.daykm.ebaybook;

import android.app.Application;

import com.daykm.ebaybook.service.BookListService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Application context to use for storing objects so that they survive reconfiguration changes
 */
public class BookApplication extends Application {

    // Keep retrofit builders because construction is expensive
    private Retrofit retrofit;
    // Keep generated services because their construction is also expensive
    private BookListService service;

    // Endpoint for grabbing book listings
    static final String BASE_URL = "http://de-coding-test.s3.amazonaws.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BookListService.class);
    }

    public BookListService getService() {
        return service;
    }
}
