package location.hive;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class DetailActivity extends AppCompatActivity /*implements OnMapReadyCallback*/ {

    private static final int DIALOG_REQUEST = 9001;

    private GoogleMap mMap;

    //@Override
    //public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
       // map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        String city = getIntent().getStringExtra("city");
        setTitle(getString(R.string.landon_hotel) + ", " + city);
        Hotel hotel = DataProvider.hotelMap.get(city);
        if (hotel == null) {
            Toast.makeText(this, getString(R.string.error_find_hotel) + ": "
                    + city, Toast.LENGTH_SHORT).show();
            return;
        }
        TextView cityText = (TextView) findViewById(R.id.cityText);
        cityText.setText(hotel.getCity());
        TextView neighborhoodText = (TextView) findViewById(R.id.neighborhoodText);
        neighborhoodText.setText(hotel.getNeighborhood());
        TextView descText = (TextView) findViewById(R.id.descriptionText);
        descText.setText(hotel.getDescription() + "\n");
        */

        if (servicesOK() /*&& initMap()*/) {
            setContentView(R.layout.activity_detail_with_map);
            //Geocoder gc = new Geocoder(this);
            //List<Address> list;
            try {
                //list = gc.getFromLocationName(hotel.getAddress(), 1);
                //Address address = list.get(0);

                double lat = 12.6;
                double lng = 12.6;
                LatLng latLong = new LatLng(lat, lng);
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLong, 5);
                mMap.moveCamera(update);

                /*MarkerOptions options = new MarkerOptions()
                        .title(getString(R.string.landon_hotel) + ", " + city)
                        .position(new LatLng(lat, lng));
                mMap.addMarker(options);*/
                //onMapReady(mMap);

            } /*catch (IOException e) {
                Toast.makeText(this, getString(R.string.error_finding_hotel), Toast.LENGTH_SHORT).show();
            }*/ catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(this.getLocalClassName(), e.getMessage());
            }

        }

    }

    public boolean servicesOK() {
        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(result)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(result, this, DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, getString(R.string.error_connect_to_services), Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFrag =
                    (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
            mMap = mapFrag.getMap();
            //mapFrag.getMapAsync((OnMapReadyCallback) this);
        }
        return (mMap != null);
    }
}