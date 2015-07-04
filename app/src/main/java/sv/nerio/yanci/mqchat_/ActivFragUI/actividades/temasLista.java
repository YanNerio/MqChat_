package sv.nerio.yanci.mqchat_.ActivFragUI.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import sv.nerio.yanci.mqchat_.ActivFragUI.fragmentos.temasListaFragment;
import sv.nerio.yanci.mqchat_.R;
import sv.nerio.yanci.mqchat_.accesoWS.accesosWS;

/**
 * Created by YANCI on 30/06/2015.
 */
public class temasLista extends AppCompatActivity {
    /**
     * Instancia global de los temas detallar
     */
    private String idCate;
    private String nombreCate;


    public static void launch(Activity activity, String idCate, String nombreCate) {
        Intent intent = getLaunchIntent(activity, idCate,nombreCate);

        activity.startActivityForResult(intent, accesosWS.CODIGO_DETALLE);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de temas.
     */
    public static Intent getLaunchIntent(Context context, String idCate, String nombreCate) {
        Intent intent = new Intent(context, temasLista.class);
        intent.putExtra(accesosWS.EXTRA_ID, idCate);
        intent.putExtra("nombreCat", nombreCate);
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
            idCate = getIntent().getStringExtra(accesosWS.EXTRA_ID);
            nombreCate = getIntent().getStringExtra("nombreCat");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor, temasListaFragment.createInstance(idCate,nombreCate), "temasListaFragment")
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == accesosWS.CODIGO_ACTUALIZACION) {
            if (resultCode == RESULT_OK) {
                temasListaFragment fragment = (temasListaFragment) getSupportFragmentManager().
                        findFragmentByTag("temasListaFragment");
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
