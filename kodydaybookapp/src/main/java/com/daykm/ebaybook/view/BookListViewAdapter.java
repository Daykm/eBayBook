package com.daykm.ebaybook.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daykm.ebaybook.R;
import com.daykm.ebaybook.data.BookListing;
import com.squareup.picasso.Picasso;

/**
 * RecyclerView list adapter to display book listings.
 */
public class BookListViewAdapter extends RecyclerView.Adapter<BookListViewAdapter.BookViewHolder> {

    private BookListing[] books;
    // This is set when books are no longer expected, so that a error view can be displayed
    private boolean booksAvailable = true;

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        if (booksAvailable) {
            holder.title.setText(books[position].getTitle());
            Picasso.with(holder.image.getContext())
                    .load(books[position].getImageURL())
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .placeholder(R.drawable.ic_photo_black_24dp)
                    .into(holder.image);
        } else {
            holder.title.setVisibility(View.INVISIBLE);
            holder.image.setVisibility(View.INVISIBLE);
            holder.errorMessage.setVisibility(View.VISIBLE);
        }
    }

    public void setBooks(BookListing[] books) {
        this.books = books;
    }

    public void setBooksAvailable(boolean booksAvailable) {
        this.booksAvailable = booksAvailable;
    }

    @Override
    public int getItemCount() {
        // return 1 at least so that a error view can be displayed
        return books != null ? books.length : 1;
    }

    protected static class BookViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public TextView errorMessage;

        public BookViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            errorMessage = (TextView) itemView.findViewById(R.id.error_message);
        }


    }
}
