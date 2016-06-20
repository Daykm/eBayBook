package com.daykm.ebaybook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.daykm.ebaybook.data.BookListing;
import com.daykm.ebaybook.service.BookListService;
import com.daykm.ebaybook.view.BookListViewAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends AppCompatActivity {

    // Personal convention for tagging log messages
    public static final String TAG = BookListActivity.class.getSimpleName();

    private BookListService service;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (RecyclerView) findViewById(R.id.bookList);
        list.setLayoutManager(new LinearLayoutManager(this));

        service = ((BookApplication) getApplication()).getService();

        getMovies();
    }

    private void getMovies() {
        service.getBookListing().enqueue(new Callback<BookListing[]>() {
            @Override
            public void onResponse(Call<BookListing[]> call, Response<BookListing[]> response) {
                if (response.isSuccessful()) {
                    inflateMovies(response.body());
                } else {
                    inflateError();
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<BookListing[]> call, Throwable t) {
                inflateError();
                Log.e(TAG, "Service crash", t);
            }
        });
    }

    private void inflateMovies(BookListing[] books) {
        BookListViewAdapter adapter = new BookListViewAdapter();
        adapter.setBooks(books);
        list.setAdapter(adapter);
    }

    private void inflateError() {
        BookListViewAdapter adapter = new BookListViewAdapter();
        adapter.setBooksAvailable(false);
        list.setAdapter(adapter);
    }
}
