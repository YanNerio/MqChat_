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
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import sv.nerio.yanci.mqchat_.ActivFragUI.categoriaAdapter;
import sv.nerio.yanci.mqchat_.modelo.tema;
import sv.nerio.yanci.mqchat_.modelo.Categorias;
import sv.nerio.yanci.mqchat_.R;
import sv.nerio.yanci.mqchat_.accesoWS.accesosWS;
import sv.nerio.yanci.mqchat_.web.volley;
import sv.nerio.yanci.mqchat_.ActivFragUI.temaAdapter;
import sv.nerio.yanci.mqchat_.ActivFragUI.actividades.insertar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


/**
 * Fragmento principal que contiene la lista de los temas
 */
public class categoriaFragment extends Fragment {

    private static final String TAG = categoriaFragment.class.getSimpleName();

    private categoriaAdapter adapter;

    private RecyclerView lista;

    private RecyclerView.LayoutManager lManager;

    com.melnykov.fab.FloatingActionButton fab;
    private Gson gson = new Gson();
    private ImageView icono;
    public categoriaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categorias, container, false);
        icono = (ImageView) v.findViewById(R.id.imageView);
        lista = (RecyclerView) v.findViewById(R.id.reciclador);
        lista.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        lista.setLayoutManager(lManager);

        // Cargar datos en el adaptador
        cargarAdaptador();

        // Obtener instancia del FAB
        fab = (com.melnykov.fab.FloatingActionButton) v.findViewById(R.id.fab);

        // Asignar escucha al FAB
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Iniciar actividad de inserción
                        getActivity().startActivityForResult(
                                new Intent(getActivity(), insertar.class), 3);
                    }
                }
        );

        return v;
    }

    /**
     * Carga el adaptador con las categorias obtenids
     * en la respuesta
     */
    public void cargarAdaptador() {
        // Petición GET
        volley.getInstance(getActivity()).addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                accesosWS.GET_CATE,
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

    /**
     * Interpreta los resultados de la respuesta y así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
    private void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "1": // EXITO

                    JSONArray mensaje = response.getJSONArray("categoria");//tema

                    Categorias[] cate = gson.fromJson(mensaje.toString(), Categorias[].class);

                    adapter = new categoriaAdapter(Arrays.asList(cate), getActivity());//tema

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
