package com.ankur.lahacks.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankur.lahacks.R;

import java.util.List;

/**
 * Created by Ankur on 02/04/17.
 */


public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mDataSet;

    public QuotesAdapter(Context context, List<String> list){
        mContext = context;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;

        public ViewHolder(View v){
            super(v);
            // Get the widget reference from the custom layout
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv);
        }
    }

    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.quotes_card, parent, false);
        ViewHolder vh = new ViewHolder(v);

        // Return the ViewHolder
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // Get the current color from the data set
        String quote = mDataSet.get(position);

        // Set the TextView widget text
        holder.mTextView.setText(quote);
    }

    @Override
    public int getItemCount(){
        // Count the items
        return mDataSet.size();
    }
}