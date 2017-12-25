package com.keerthi.simpragma.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.keerthi.simpragma.Activities.DetailsActivity;
import com.keerthi.simpragma.Model.RecepieModel;
import com.keerthi.simpragma.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    List<RecepieModel.Result> recipeList;
    Context context;
    private static LayoutInflater inflater=null;


    public CustomListAdapter(Context mcontext, List<RecepieModel.Result> recipeList) {
        this.recipeList = recipeList;
        context = mcontext;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tvTitle;
        ImageView ivImage;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;

        final RecepieModel.Result recepieModel =  recipeList.get(position);

        rowView = inflater.inflate(R.layout.custom_list_view_row, null);
        holder.tvTitle = (TextView) rowView.findViewById(R.id.custom_list_textView);
        holder.ivImage = (ImageView) rowView.findViewById(R.id.custom_list_imageView);

        holder.tvTitle.setText(recepieModel.getTitle());
        if (recepieModel.getThumbnail() != null && !recepieModel.getThumbnail().equals("")) {

            String receivedString = recepieModel.getThumbnail();
            String formattedString = receivedString.replaceAll("\"", "\"");
            Picasso.with(context).load(formattedString).error(R.drawable.placeholder)
                    .into(holder.ivImage);
        }

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title", recepieModel.getTitle());
                intent.putExtra("ingredients",recepieModel.getIngredients());
                intent.putExtra("thumbnail", recepieModel.getThumbnail());
                intent.putExtra("href", recepieModel.getHref());
                context.startActivity(intent);
            }
        });

        return rowView;
    }

}