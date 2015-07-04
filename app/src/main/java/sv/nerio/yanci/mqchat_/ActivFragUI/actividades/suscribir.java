package sv.nerio.yanci.mqchat_.ActivFragUI.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import sv.nerio.yanci.mqchat_.ActivFragUI.fragmentos.suscribirFragment;
import sv.nerio.yanci.mqchat_.R;
import sv.nerio.yanci.mqchat_.accesoWS.accesosWS;

/**
 * Created by YANCI on 03/07/2015.
 */
public class suscribir extends AppCompatActivity {
    /**
     * Instancia global del tema a detallar
     */
    private String idTema;
    private String nombreCate;


    public static void launch(Activity activity, String idTema, String nombreCate) {
        Intent intent = getLaunchIntent(activity, idTema,  nombreCate);
        activity.startActivityForResult(intent, accesosWS.CODIGO_DETALLE);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de detalle.
     */
    public static Intent getLaunchIntent(Context context, String idTema,  String nombreCate) {
        Intent intent = new Intent(context, suscribir.class);
        intent.putExtra(accesosWS.EXTRA_ID, idTema);
        intent.putExtra("nombreCate", nombreCate);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            // Dehabilitar titulo de la actividad
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // Setear ícono "X" como Up button
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_close);
        }

        // Retener instancia
        if (getIntent().getStringExtra(accesosWS.EXTRA_ID) != null)
            idTema = getIntent().getStringExtra(accesosWS.EXTRA_ID);
            nombreCate = getIntent().getStringExtra("nombreCate");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor, suscribirFragment.createInstance(idTema, nombreCate), "suscribirFragment")
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == accesosWS.CODIGO_ACTUALIZACION) {
            if (resultCode == RESULT_OK) {
                suscribirFragment fragment = (suscribirFragment) getSupportFragmentManager().
                        findFragmentByTag("suscribirFragment");
                fragment.cargarDatos();

                setResult(RESULT_OK); // Propagar código para actualizar
            } else if (resultCode == 203) {
                setResult(203);
                finish();
            } else {
                setResult(RESULT_CANCELED);
            }
        }
    }
}

