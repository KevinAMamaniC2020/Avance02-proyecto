package com.example.avance02_proyecto.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.avance02_proyecto.R;

public class AreaFragment extends Fragment {

    private String areaName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);

        // Obtiene los elementos de UI
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        ImageView areaImageView = view.findViewById(R.id.areaImageView);
        ImageButton closeButton = view.findViewById(R.id.closeButton);

        // Obtén el nombre del área desde los argumentos
        if (getArguments() != null) {
            areaName = getArguments().getString("areaName");

            // Configura la UI de acuerdo con el área seleccionada
            switch (areaName) {
                case "Calle Granada":
                    titleTextView.setText("Calle Granada");
                    descriptionTextView.setText("A esta calle se puede llegar desde la calle Burgos o desde la calle Sevilla, todas con nombres españoles dentro del recinto del Monasterio de Santa Catalina. Esta calle Granada en concreto es algo más ancha que otras y desde la que se entraba a la humeda y oscura cocina comunitaria del monasterio. Un poco más allá, unas escaleras llevan hasta la Plaza de Zocodover.");
                    areaImageView.setImageResource(R.drawable.calle_granada); // Asegúrate de tener esta imagen en res/drawable
                    break;

                case "Claustro Novicias":
                    titleTextView.setText("Claustro Novicias");
                    descriptionTextView.setText("Marcado por un árbol de caucho en su entrada, este era un área donde las monjas debían prestar un juramento de silencio y dedicar sus vidas a la oración y el trabajo. Las monjas servían como novicias durante 4 años, período en el cual sus familias tenían que pagar una dote de 100 monedas de oro por año. Al final de los 4 años, la novicia podía elegir entre entrar en una vida completa de servicio o dejar el convento (lo que acarrearía vergüenza a su familia).");
                    areaImageView.setImageResource(R.drawable.claustro_novicias); // Imagen en res/drawable
                    break;

                case "Patio del Silencio":
                    titleTextView.setText("Patio del Silencio");
                    descriptionTextView.setText("El Patio del Silencio es el último tramo de transición desde el mundo exterior a la vida recogida entre estas rocas pesadas arrancadas con esfuerzo de la ladera del volcán. Su trazado en planta es algo más complejo que el anterior Patio de Labores, y está definido por dos cuadrados ligeramente desplazados a lo largo de su diagonal. Su función como espacio abierto es fundamental en la estructura organizativa del Convento de Santa Catalina.");
                    areaImageView.setImageResource(R.drawable.patio_del_silencio); // Imagen en res/drawable
                    break;
                case "Claustro de los Naranjos":
                    titleTextView.setText("Claustro de los Naranjos");
                    descriptionTextView.setText("En el Monasterio de Santa Catalina, las novicias graduadas pasaban a alojarse en el patio de los Naranjos, todo pintando de azul y cuyo patio contiene precisamente eso árboles que le dan nombre: naranjos. Es en este claustro donde está la puerta que da acceso a la sala Profundis, que es donde se instalaba la capilla ardiente de las monjas.");
                    areaImageView.setImageResource(R.drawable.claustro_naranjos); // Imagen en res/drawable
                    break;

                // Agrega más áreas según sea necesario
                default:
                    titleTextView.setText("Área Desconocida");
                    descriptionTextView.setText("No se encontró información para esta área.");
                    areaImageView.setImageResource(R.drawable.defecto); // Imagen predeterminada
                    break;
            }
        }

        // Configura el botón de cierre para que quite el fragmento
        closeButton.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }
}