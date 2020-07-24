package com.ngyu.civihacker.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngyu.civihacker.R;
import com.ngyu.civihacker.lib.service.MpDisinfectionInfo;
import com.ngyu.civihacker.lib.service.RetrofitApi;
import com.ngyu.civihacker.lib.service.RetrofitRepo;
import com.ngyu.civihacker.lib.service.Row;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener{

    GoogleMap mMap;

    String TAG = "CODE TEST ########## ";

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    private Gson mGson;
    private Retrofit mRetrofit;
    private Call<RetrofitRepo> mCallRowList;
    private RetrofitApi mRetrofitAPI;

    private String BaseUrl = "http://openapi.mapo.go.kr:8088/";

    double lat, lot;


    String[] REQUIRED_PERMISSIONS  = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

       final LocationManager lm = (LocationManager) root.getContext().getSystemService(Context.LOCATION_SERVICE);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        retrofitInit();


        Button button1 = root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsBtn(lm);
            }
        });


        return root;
    }

    public void retrofitInit(){


        mGson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitApi.class);

        Call<RetrofitRepo> call = mRetrofitAPI.getRowList();

        Log.e(TAG,"RETROFIT TEST");

        call.enqueue(new Callback<RetrofitRepo>() {
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
                List<Row> repo = response.body().getMpDisinfectionInfo().getRow();

                for(int i = 0 ; i < repo.size() ; i++){
                    String addr = repo.get(i).getADDR();
                    Log.d(TAG, addr);
                    geoCode(addr, repo.get(i).getRESIORGZNM(), repo.get(i).getTELNO());
                }

            }
            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {
                Log.e("ERROR" , t.toString());
            }
        });


    }

    private void geoCode(String addr, String title, String telNo){
        Geocoder geocoder = new Geocoder(getContext());
        try{
            List<Address> mResultLocation =
                    geocoder.getFromLocationName(addr,1);
            double mLat = mResultLocation.get(0).getLatitude();
            double mLng = mResultLocation.get(0).getLongitude();
            Log.d(TAG,"onComplate: 위경도" + mLat + mLng);
            onMapCustomMarker(mLat, mLng, title, telNo);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"onComplate: 주소변환 실패");
        }
    }


    private void gpsBtn(LocationManager lm) {
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getContext().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( getActivity(), new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    0 );
        }
        else{
            Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            lat = latitude;
            lot = longitude;


            Log.d("TEST Log","위치정보 : " + provider + "\n" +
                    "위도 : " + longitude + "\n" +
                    "경도 : " + latitude + "\n" +
                    "고도  : " + altitude);

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);

            onMapCustomMarker(lat, lot, "현위" , "");
            mapFocus(lat, lot);

        }
    }

    private void mapFocus(double lat, double lot){
        LatLng lng = new LatLng(lat, lot);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(lng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

    }

    private void onMapCustomMarker(double lat, double lot,String title, String telNo){
        LatLng lng = new LatLng(lat, lot);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions
                .position(lng)
                .alpha(1f);

        if(telNo != "") {
            markerOptions
                    .title(title)
                    .snippet(telNo);
        }
        mMap.addMarker(markerOptions).showInfoWindow();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        onMapCustomMarker(37.5561451,126.9470937,  "현위치", "");
        mapFocus(37.5561451 , 126.9470937);
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            Log.d("TEST Log","위치정보 : " + provider + "\n" +
                    "위도 : " + longitude + "\n" +
                    "경도 : " + latitude + "\n" +
                    "고도  : " + altitude);

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
