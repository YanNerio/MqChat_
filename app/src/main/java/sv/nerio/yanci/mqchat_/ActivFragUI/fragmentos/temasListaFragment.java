package sv.nerio.yanci.mqchat_.ActivFragUI.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import sv.nerio.yanci.mqchat_.ActivFragUI.actividades.insertar;
import sv.nerio.yanci.mqchat_.ActivFragUI.temaAdapter;
import sv.nerio.yanci.mqchat_.R;
import sv.nerio.yanci.mqchat_.accesoWS.accesosWS;
import sv.nerio.yanci.mqchat_.web.volley;
import sv.nerio.yanci.mqchat_.modelo.tema;
import sv.nerio.yanci.mqchat_.modelo.Categorias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class temasListaFragment extends Fragment {

    /**
     * Etiqueta de depuración
     */
    private static final String TAG = temasListaFragment.class.getSimpleName();

    /*
    Instancias de Views
     */
    private ImageView icono;
    private TextView nombre;
    private TextView descripcion;
    private TextView estado;
    private TextView fechaCrea;
    private TextView categoria;
    private ImageButton editButton;
    private String extra;

    private String nombreCateg;
    com.melnykov.fab.FloatingActionButton fab;
    private Gson gson = new Gson();
    private temaAdapter adapter;
    private RecyclerView lista;
    private RecyclerView.LayoutManager lManager;

    public temasListaFragment() {
    }

    public static temasListaFragment createInstance(String idCate, String nombreCate) {
        temasListaFragment temasListaFragment = new temasListaFragment();
        Bundle bundle = new Bundle();
        bundle.putString(accesosWS.EXTRA_ID, idCate);
        bundle.putString("nombreCat", nombreCate);
        temasListaFragment.setArguments(bundle);
        return temasListaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_temas, container, false);
        icono = (ImageView) v.findViewById(R.id.imageView);
        lista = (RecyclerView) v.findViewById(R.id.reciclador);
        nombre = (TextView) v.findViewById(R.id.nombre);
        lista.setHasFixedSize(true);
        extra = getArguments().getString(accesosWS.EXTRA_ID);
        nombreCateg = getArguments().getString("nombreCat");
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        lista.setLayoutManager(lManager);
        nombre.setText(nombreCateg);
        // Cargar datos en el adaptador
        cargarDatos();
        // Obtener instancia del FAB
        fab = (com.melnykov.fab.FloatingActionButton) v.findViewById(R.id.fab);

        // Asignar escucha al FAB
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Iniciar actividad de inserción
                        getActivity().startActivityForResult(
                                new Intent(getActivity(), RecyclerView.class), 3);
                    }
                }
        );

        return v;
    }

    /**
     * Obtiene los datos desde el servidor
     */
    public void cargarDatos() {

        // Añadir parámetro a la URL del web service
        String newURL = accesosWS.GET_TEMAS_POR_CATE + "?idCate=" + extra;
        volley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        newURL,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta Json
                                procesarRespuesta(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }

                )
        );
    }


    private void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "1": // EXITO

                    JSONArray mensaje = response.getJSONArray("tema");//tema

                    tema[] tema = gson.fromJson(mensaje.toString(), tema[].class);


                    adapter = new temaAdapter(Arrays.asList(tema), getActivity());//tema

                    // Setear adaptador a la lista
                    lista.setAdapter(adapter);
                    break;
                case "2": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
