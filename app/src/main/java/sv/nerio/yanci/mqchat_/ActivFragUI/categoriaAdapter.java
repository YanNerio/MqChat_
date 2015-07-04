package sv.nerio.yanci.mqchat_.ActivFragUI;

/**
 * Created by YANCI on 30/06/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sv.nerio.yanci.mqchat_.ActivFragUI.actividades.temasLista;
import sv.nerio.yanci.mqchat_.R;
import sv.nerio.yanci.mqchat_.modelo.Categorias;

import java.util.List;

/**
 * Adaptador del recycler view
 */
public class categoriaAdapter extends RecyclerView.Adapter<categoriaAdapter.MetaViewHolder>
        implements ItemClickListener {


    private List<Categorias> items;
    //private List<tema> items;

    /*
    Contexto donde actua el recycler view
     */
    private Context context;

    //tema por Categorias
    public categoriaAdapter(List<Categorias> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MetaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categoria_item_list, viewGroup, false);///item_list
        return new MetaViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(MetaViewHolder viewHolder, int i) {
       /* viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.estado.setText(items.get(i).getEstado());
        viewHolder.fechaCrea.setText(items.get(i).getFechaCrea());*/
        viewHolder.categoria.setText(items.get(i).getCategoria());

        switch (items.get(i).getCategoria()) {
            case "Deportes":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_sports);
                break;
            case "Moda y Belleza":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_fashion);
                break;
            case "Viajes":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_travel);
                break;
            case "Economía y Finanzas":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_economy);
                break;
            case "Religión":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_bible);
                break;
            case "Amor":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_love);
                break;
            case "Amistad":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_friends);
                break;
            case "Medio Ambiente":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_nature);
                break;
            case "Música":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_music);
                break;
            case "Comidas":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_food);
                break;
            case "Literatura":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_book);
                break;
            case "Desamor":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_broken);
                break;
            case "Varios":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_others);
                break;
            case "Video Juegos":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_games);
                break;
            case "Justicia, Derechos, Igualdad":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_justice);
                break;
            case "Fitness":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_fitness);
                break;
            case "Educación":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_book);
                break;
            case "Tecnología":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_programming);
                break;
            case "Ingeniería":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_eng);
                break;
            case "Medicina":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_medicine);
                break;
            case "Arquitectura":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_architecture);
                break;
            case "Idiomas":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_languages);
                break;
            case "Cultura":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_culture);
                break;
            case "Programación y Redes":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_programming);
                break;
            case "Familia":
                viewHolder.icono.setBackgroundResource(R.mipmap.ic_family);
                break;

        }
            //System.out.println("Soy salud"+ items.get(i).getCategoria());
    }

    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {
        temasLista.launch(
                //(Activity) context, items.get(position).getIdTema());
                (Activity) context, items.get(position).getidCategoria(), items.get(position).getCategoria());
        System.out.println(items.get(position).getCategoria());
    }


    public static class MetaViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView categoria;
        public ImageView icono;
        public ItemClickListener listener;

        public MetaViewHolder(View v, ItemClickListener listener) {
            super(v);
            categoria = (TextView) v.findViewById(R.id.categoria);
            icono = (ImageView) v.findViewById(R.id.imageView);
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}



