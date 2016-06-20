package com.daykm.ebaybook.service;


import com.daykm.ebaybook.data.BookListing;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Service interface for grabbing books. For retrofit to create an implementation
 */
public interface BookListService {
    @GET("books.json")
    Call<BookListing[]> getBookListing();
}
