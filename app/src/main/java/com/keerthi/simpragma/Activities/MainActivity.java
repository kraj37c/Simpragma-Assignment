package com.keerthi.simpragma.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.keerthi.simpragma.Adapter.CustomListAdapter;
import com.keerthi.simpragma.Model.RecepieModel;
import com.keerthi.simpragma.R;
import com.keerthi.simpragma.Utilities.Util;
import com.keerthi.simpragma.WebServices.ApiCLient;
import com.keerthi.simpragma.WebServices.ApiInterface;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView lvRecipe;
    private List<RecepieModel.Result> recepieList;
    CustomListAdapter customListAdapter;
    int listValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvRecipe = (ListView) findViewById(R.id.main_activity_listView);
        lvRecipe.setOnScrollListener(onScrollListener());

        //Initializing our list
        recepieList = new ArrayList<>();

        getRecepieList(listValue);

    }


    private void getRecepieList(int value) {

        listValue++;
        Util.showProgressDialog(MainActivity.this, "Loading...", "Please Wait...", false);
        ApiInterface apiService =
                ApiCLient.getClient().create(ApiInterface.class);

        Call<RecepieModel> call = apiService.getRecepie(value);

        call.enqueue(new Callback<RecepieModel>() {
            @Override
            public void onResponse(Call<RecepieModel> call, Response<RecepieModel> response) {
                Util.dismissProgressDialog();


                for(int i=0;i<response.body().getResults().size();i++){
                    RecepieModel.Result recepieModel = new RecepieModel.Result();

                    recepieModel.setTitle(response.body().getResults().get(i).getTitle());
                    recepieModel.setHref(response.body().getResults().get(i).getHref());
                    recepieModel.setIngredients(response.body().getResults().get(i).getIngredients());
                    recepieModel.setThumbnail(response.body().getResults().get(i).getThumbnail());

                    recepieList.add(recepieModel);
                }

                if(listValue==2){
                    customListAdapter = new CustomListAdapter(MainActivity.this, recepieList);
                    lvRecipe.setAdapter(customListAdapter);
                }
                else {
                    customListAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<RecepieModel> call, Throwable t) {
                Util.dismissProgressDialog();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }



    private OnScrollListener onScrollListener() {
        return new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = lvRecipe.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (lvRecipe.getLastVisiblePosition() >= count - threshold) {
                        // LoadMoreData
                        getRecepieList(listValue);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }

        };
    }
}
