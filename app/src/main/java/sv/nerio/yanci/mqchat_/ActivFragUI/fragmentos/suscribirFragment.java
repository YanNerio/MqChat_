package sv.nerio.yanci.mqchat_.ActivFragUI.fragmentos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import sv.nerio.yanci.mqchat_.ActivFragUI.actividades.suscribir;
import sv.nerio.yanci.mqchat_.R;
import sv.nerio.yanci.mqchat_.accesoWS.accesosWS;
import sv.nerio.yanci.mqchat_.web.volley;
import sv.nerio.yanci.mqchat_.modelo.tema;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class suscribirFragment extends Fragment {

    /**
     * Etiqueta de depuración
     */
    private static final String TAG = suscribirFragment.class.getSimpleName();

    /*
    Instancias de Views
     */
    private ImageView cabecera;
    private TextView nombre;
    private TextView descripcion;
    private TextView suscripciones;
    private TextView fechaCrea;
    private TextView categoria;
    private Button suscribirBtn;

    private String idTema;
    private Gson gson = new Gson();

    public suscribirFragment() {
    }

    public static suscribirFragment createInstance(String idTema, String cate) {
        suscribirFragment suscribirFragment = new suscribirFragment();
        Bundle bundle = new Bundle();
        bundle.putString(accesosWS.EXTRA_ID, idTema);
        bundle.putString("nombreCate", cate);
        suscribirFragment.setArguments(bundle);
        return suscribirFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle, container, false);

        // Obtención de views
        cabecera = (ImageView) v.findViewById(R.id.cabecera);
        nombre = (TextView) v.findViewById(R.id.nombre);
        descripcion = (TextView) v.findViewById(R.id.descripcion);
        suscripciones = (TextView) v.findViewById(R.id.suscripciones);
        fechaCrea = (TextView) v.findViewById(R.id.fecha);
        categoria = (TextView) v.findViewById(R.id.categoria);
        suscribirBtn   = (Button) v.findViewById(R.id.suscribir);

        // Obtener idTema del intent de envío
        idTema = getArguments().getString(accesosWS.EXTRA_ID);

        // Setear escucha para el fab
        System.out.println("soy id  "+ idTema);
        suscribirBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Iniciar actividad de actualización
                        suscribirTema();
                        Intent i = new Intent(getActivity(), suscribir.class);
                        //i.putExtra(accesosWS.EXTRA_ID, idTema);
                        getActivity().startActivity(i);
                    }
                }
        );

        if (idTema!= null) {
            cargarDatos();
        }

        return v;
    }

    /**
     * Obtiene los datos desde el servidor
     */
    public void cargarDatos() {

        // Añadir parámetro a la URL del web service
        String newURL = accesosWS.GET_BY_ID + "?idTema=" + idTema;

        // Realizar petición GET_BY_ID
        volley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        newURL,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar respuesta Json
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
            // Obtener atributo "mensaje"
            String mensaje = response.getString("estado");

            switch (mensaje) {
                case "1":
                    // Obtener objeto "tema"
                    JSONObject object = response.getJSONObject("tema");

                    //Parsear objeto 
                    tema tema = gson.fromJson(object.toString(), tema.class);
                    System.out.println(tema.getContador());
                    // Seteando valores en los views
                    nombre.setText(tema.getNombre());
                    descripcion.setText(tema.getDescripcion());
                    suscripciones.setText(tema.getContador());
                    fechaCrea.setText(tema.getFechaCrea());


                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /*** hace una suscripción
    */
    private void suscribirTema() {
        final String suscripcionesNum = suscripciones.getText().toString();
        HashMap<String, String> map = new HashMap<>();// Mapeo previo

        map.put("idTema", idTema);
        map.put("suscripcionesNum", suscripcionesNum);


        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d(TAG, jobject.toString());

        // Actualizar datos en el servidor
        volley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        accesosWS.SUSCRIBIR,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarRespuestaSuscripcion(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }

                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );

    }
    /**
     * Procesa la respuesta de suscripción obtenida desde el sevidor
     */
    private void procesarRespuestaSuscripcion(JSONObject response) {

        try {
            // Obtener estado
            String estado = response.getString("estado");
            // Obtener mensaje
            String mensaje = response.getString("mensaje");

            switch (estado) {
                case "1":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de éxito
                    getActivity().setResult(Activity.RESULT_OK);
                    // Terminar actividad
                    getActivity().finish();
                    break;

                case "2":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de falla
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    // Terminar actividad
                    getActivity().finish();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
