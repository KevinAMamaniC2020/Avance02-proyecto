package com.example.avance02_proyecto.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.avance02_proyecto.Adapter.DataClass;
import com.example.avance02_proyecto.Adapter.MyAdapter;
import com.example.avance02_proyecto.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    Chip cJava, cKotlin, cMuseo;
    RecyclerView recyclerView;
    List<DataClass> dataList;
    List<DataClass> TemporalList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Referencias a las vistas usando la vista inflada
        cJava = view.findViewById(R.id.cJava);
        cKotlin = view.findViewById(R.id.cKotlin);
        cMuseo = view.findViewById(R.id.cMuseo);
        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.search);

        View searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        if (searchPlate != null) {
            searchPlate.setBackgroundColor(Color.TRANSPARENT); // Hace transparente el fondo de la línea
        }

        // Configuración de la lista y adaptador
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Datos de ejemplo
        dataList = new ArrayList<>();
        dataList.add(new DataClass("Convento de Santa Catalina", R.string.Catalina, "Arquitectura", R.drawable.catalina_convento));
        dataList.add(new DataClass("Catedral de Arequipa", R.string.Catedral, "Arquitectura", R.drawable.catedral_arequipa));
        dataList.add(new DataClass("Iglesia Compañia de Jesus", R.string.IglesiaJesus, "Iglesia", R.drawable.iglesia_jesus));
        dataList.add(new DataClass("Iglesia de Santo Domingo", R.string.Domingo, "Iglesia", R.drawable.iglesia_santo_domingo));
        dataList.add(new DataClass("Museo de Santuarios Andinos", R.string.Santuarios, "Museo", R.drawable.museo_sa));
        dataList.add(new DataClass("Casa Moral", R.string.moral, "Museo", R.drawable.casa_moral));
        dataList.add(new DataClass("Molino de Sabandía", R.string.molino, "Arquitectura", R.drawable.molino_sabandia));
        dataList.add(new DataClass("Barrio San Lazaro", R.string.barrio, "Arquitectura", R.drawable.barrio_san_lazaro));
        dataList.add(new DataClass("Iglesia de San Agustín", R.string.agustin, "Iglesia", R.drawable.iglesia_san_agustin));
        dataList.add(new DataClass("Casa Tristán del Pozo", R.string.pozo, "Museo", R.drawable.tristan_pozo));


        adapter = new MyAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);

        TemporalList = new ArrayList<>();

        // Configuración de los chips al hacer clic
        cJava.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mostrarFiltrado(cJava.getText().toString());
            } else {
                adapter.setSearchList(dataList);
            }
        });

        cKotlin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mostrarFiltrado(cKotlin.getText().toString());
            } else {
                adapter.setSearchList(dataList);
            }
        });

        cMuseo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mostrarFiltrado(cMuseo.getText().toString());
            } else {
                adapter.setSearchList(dataList);
            }
        });

        return view; // Retornar la vista inflada
    }

    public void mostrarFiltrado(String filtro) {
        TemporalList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataLang().equals(filtro)) {
                TemporalList.add(data);
            }
        }
        adapter.setSearchList(TemporalList);
    }

    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(getContext(), "Lo que busca no Existe", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
