package com.example.listviewdinamic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DinamicListView extends AppCompatActivity {

    TextView selection;
    ListView listView;
    private static final String[] items = {"Madrid", "Lugo", "Ibiza",
            "Palma de Mallorca", "Barcelona", "Valencia", "Valladolid", "Leon", "Burgos",
            "Ciudad Real", "Gijon", "Sevilla", "Alicante", "Malaga", "Murcia", "Cadiz",
            "Asturias", "Zaragoza", "Santiago", "Jaen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // identificamos elementos
        listView = findViewById(R.id.listView);
        selection = findViewById(R.id.tv_mostrar_elemento_selecionado);

        // añadimos adaptador personalizado a la lista
        //listView.setAdapter(new IconicAdapter());

        // probando adaptador con parametros
        listView.setAdapter(new IconicAdapter(this, R.layout.list_row, R.id.textview_item, items));

        // añadimos onItemClickListener a la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // en textview colocamos texto del item selecionado
                selection.setText(items[position]);
            }
        });

    }


    /**
     * Clase interna del adaptador personalizado
     * tiene que extender del ArrayAdapter<String>
     */
    class IconicAdapter extends ArrayAdapter<String> {

        // constructor sin parametros
        public IconicAdapter() {
            super(DinamicListView.this, // context
                    R.layout.list_row, // donde coje formato de la lista
                    R.id.textview_item, // textView donde muestra texto
                    items);             // array de string
        }

        // constructor con parametros
        public IconicAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull String[] objects) {
            super(context,            // context
                    resource,           // formato de un elemento de la listview
                    textViewResourceId, // textView donde muestra un elemento de la lista
                    objects);           // array de donde cojemos elementos
        }

        /**
         * Metodo sobreescrito getView
         *
         * @param position posicion del elemento
         * @param convertView vista
         * @param parent padre
         * @return devuelve una fila
         */
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // una fila llama al parde
            View row = super.getView(position, convertView, parent);

            // identificamos campo del imagen
            ImageView imageView = (ImageView) row.findViewById(R.id.imageview_item);

            // y colocamos imagen segun nuestros criterios usando if
            if (items[position].length() <= 6) {
                imageView.setImageResource(R.drawable.checkbox_false);
            } else {
                imageView.setImageResource(R.drawable.checkbox_true);
            }
            // hay que devolver una fila
            return row;
        }
    }
}

