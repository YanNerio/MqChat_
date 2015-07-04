package sv.nerio.yanci.mqchat_.ActivFragUI;

/**
 * Created by YANCI on 30/06/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import sv.nerio.yanci.mqchat_.ActivFragUI.actividades.suscribir;
import sv.nerio.yanci.mqchat_.R;
import sv.nerio.yanci.mqchat_.modelo.tema;

import java.util.List;

/**
 * Adaptador del recycler view
 */
public class temaAdapter extends RecyclerView.Adapter<temaAdapter.TemaViewHolder>
        implements ItemClickListener {


    private List<tema> items;

    /*
    Contexto donde actua el recycler view
     */
    private Context context;

    private String idTema_;
    public temaAdapter(List<tema> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public TemaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.temas_item_list, viewGroup, false);
        return new TemaViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(final TemaViewHolder viewHolder, final int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.idTema.setText(items.get(i).getIdTema());

        viewHolder.detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Tema " + viewHolder.idTema.getText().toString() + "!", Toast.LENGTH_SHORT).show();
                suscribir.launch(
                        (Activity) context, items.get(i).getIdTema(), items.get(i).getNombre());
            }
        });

    }

    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {
        suscribir.launch(
                (Activity) context, items.get(position).getIdTema(),items.get(position).getNombre());
    }


    public static class TemaViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

             public TextView nombre;
             public TextView idTema;
             public Button detalles;
             public ItemClickListener listener;

        public TemaViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            idTema = (TextView) v.findViewById(R.id.idTema);
            detalles = (Button) v.findViewById(R.id.detalles);
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}



