package com.ngyu.civihacker.ui;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngyu.civihacker.R;
import com.ngyu.civihacker.lib.adpater.Data;
import com.ngyu.civihacker.lib.adpater.RecyclerViewAdapter;
import com.ngyu.civihacker.lib.service.RetrofitApi;
import com.ngyu.civihacker.lib.service.RetrofitRepo;
import com.ngyu.civihacker.lib.service.Row;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {


    private RecyclerViewAdapter adapter;

    private List<Double> lngList = new ArrayList<>();

    private boolean flag = true;
    private Gson mGson;
    private Retrofit mRetrofit;
    private Call<RetrofitRepo> mCallRowList;
    private RetrofitApi mRetrofitAPI;

    private String BaseUrl = "http://openapi.mapo.go.kr:8088/";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        init(root);

        getData();

        return root;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }
        return (dist);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private void init(View v){
        RecyclerView recyclerView = v.findViewById(R.id.list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        final List<String> listTitle = new ArrayList<>();
        final List<String> listAddr = new ArrayList<>();
        final List<String> listContent = new ArrayList<>();


        mGson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitApi.class);

        Call<RetrofitRepo> call = mRetrofitAPI.getRowList();
        call.enqueue(new Callback<RetrofitRepo>() {
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {

                double distanceKiloMeter = 0;
                List<Row> repo = response.body().getMpDisinfectionInfo().getRow();

                for(int i = 0 ; i < repo.size() ; i++){

                    listTitle.add(repo.get(i).getRESIORGZNM().toString());
                    listAddr.add(repo.get(i).getADDR());
                    listContent.add(repo.get(i).getTELNO());

                    flag = true;
                    lngList = new ArrayList<>();
                    geoCode(repo.get(i).getADDR());

                    Data data = new Data();
                    data.setTitle(listTitle.get(i));
                    data.setAddr(listAddr.get(i));
                    data.setContent(listContent.get(i));

                    if(flag == true){
                        distanceKiloMeter = distance(37.5561451, 126.9470937, lngList.get(0), lngList.get(1), "kilometer");
                        Log.d("distance  : ", lngList.toString() );
                        data.setDis( String.format("%.2f", distanceKiloMeter) + " KM");
                    }
                    Log.e("error" , listTitle.get(i) + " 거리 : " +  distanceKiloMeter);


                    adapter.addItem(data);

                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.e("error" , t.toString());
            }
        });

    }

    private void geoCode(String addr){
        Geocoder geocoder = new Geocoder(getContext());
        try{
            List<Address> mResultLocation = geocoder.getFromLocationName(addr,1);

            Log.d("EST" , "sssssssssssssssssssssssssssssssssssssssssssssss");
            double mLat = mResultLocation.get(0).getLatitude();
            double mLng = mResultLocation.get(0).getLongitude();

            lngList.add(mLat);
            lngList.add(mLng);

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("EST" , "fffffffffffffffffffffffffffffffffffffffffffffff");
            flag = false;
        }
    }


}
