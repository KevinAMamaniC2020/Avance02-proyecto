package com.example.avance02_proyecto.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.avance02_proyecto.Adapter.PlacesAdapter;
import com.example.avance02_proyecto.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private RecyclerView placesList;
    private ImageButton showListButton;
    //private Button showListButton;
    private boolean isListVisible = false;
    // Define una lista de lugares con coordenadas y nombres
    private final List<Place> places = new ArrayList<>();
    private final HashMap<String, Marker> markersMap = new HashMap<>();

    // Clase para representar un lugar con un nombre y ubicación
    public static class Place {
        LatLng location;
        public String name;

        Place(LatLng location, String name) {
            this.location = location;
            this.name = name;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        // Inicializar el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Agrega los lugares
        initializePlaces();

        // Configura el RecyclerView y el botón
        placesList = rootView.findViewById(R.id.places_list);
        showListButton = rootView.findViewById(R.id.show_list_button);
        showListButton.setOnClickListener(v -> togglePlacesList());

        placesList.setLayoutManager(new LinearLayoutManager(getContext()));
        PlacesAdapter adapter = new PlacesAdapter(places, this::zoomToPlace);
        placesList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Ubicación específica para centrar el mapa en Arequipa Peru
        LatLng centralLocation = new LatLng(-16.399207, -71.5388013);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centralLocation, 12)); // Zoom ajustado

        // Añadir los marcadores de los lugares definidos
        for (Place place : places) {
            mMap.addMarker(new MarkerOptions()
                    .position(place.location)
                    .title(place.name)
                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerIcon()))); // Icono personalizado
        }
    }

    // lista de los lugares definidos
    private void initializePlaces() {
        places.add(new Place(new LatLng(-16.3960952, -71.5365472), "Convento de Santa Catalina"));
        places.add(new Place(new LatLng(-16.3981263, -71.536634), "Catedral de Arequipa"));
        places.add(new Place(new LatLng(-16.3997944, -71.5364389), "Iglesia Compañia de Jesus"));
        places.add(new Place(new LatLng(-16.4006558, -71.5340966), "Iglesia de Santo Domingo"));
        places.add(new Place(new LatLng(-16.399956, -71.5377349), "Museo de Santuarios Andinos"));
        places.add(new Place(new LatLng(-16.3969042, -71.5378812), "Casa Moral"));
        places.add(new Place(new LatLng(-16.4567588, -71.4995764), "Molino de Sabandia"));
        places.add(new Place(new LatLng(-16.393241, -71.5335535), "Barrio San Lazaro"));
        places.add(new Place(new LatLng(-16.3978631, -71.5382018), "Iglesia de San Agustin"));
        places.add(new Place(new LatLng(-16.3980674, -71.5359609), "Casa Tristan del Pozo"));
    }

    // Método para obtener el icono personalizado del marcador
    private Bitmap getMarkerIcon() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marcador_icono);
        return Bitmap.createScaledBitmap(bitmap, 80, 80, false);
    }

    // Método para alternar la visibilidad de la lista de lugares
    private void togglePlacesList() {
        isListVisible = !isListVisible;
        placesList.setVisibility(isListVisible ? View.VISIBLE : View.GONE);
    }

    // Método para hacer zoom en el lugar seleccionado
    private void zoomToPlace(Place place) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.location, 18));

        // Obtener el marcador correspondiente al lugar y mostrar su ventana de información
        Marker marker = markersMap.get(place.name);
        if (marker != null) {
            marker.showInfoWindow();
        }

        togglePlacesList(); // Oculta la lista después de seleccionar un lugar
    }
}