package sv.nerio.yanci.mqchat_.ActivFragUI.actividades;

/**
 * Created by YANCI on 30/06/2015.
 */

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import sv.nerio.yanci.mqchat_.ActivFragUI.fragmentos.insertarFragment;

import sv.nerio.yanci.mqchat_.ActivFragUI.fragmentos.ConfirmDialogFragment;
import sv.nerio.yanci.mqchat_.accesoWS.accesosWS;
import sv.nerio.yanci.mqchat_.R;

public class insertar extends AppCompatActivity implements ConfirmDialogFragment.ConfirmDialogListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_done);

        // Creación del fragmento de inserción
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor, new insertarFragment(), "insertarFragment")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }



    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        insertarFragment insertFragment = (insertarFragment)
                getSupportFragmentManager().findFragmentByTag("insertarFragment");

        if (insertFragment != null) {
            finish(); // Finalizar actividad descartando cambios
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        insertarFragment insertFragment = (insertarFragment)
                getSupportFragmentManager().findFragmentByTag("insertarFragment");

        if (insertFragment != null) {
            // Nada por el momento
        }
    }
}

