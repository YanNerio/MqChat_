package sv.nerio.yanci.mqchat_.modelo;

import android.support.v7.widget.RecyclerView;

/**
 * Created by YANCI on 01/07/2015.
 */
public class Categorias {
    private static final String TAG = Categorias.class.getSimpleName();
    /*
        Atributos
         */
    private String id_categoria;
    private String categoria;
    private String descripcion;
    private String fecha_creacion;

    public Categorias(String categoria, String descripcion, String fecha_creacion,String id_categoria) {

        this.id_categoria = id_categoria;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;

    }


    public String getDescripcion() {
        return descripcion;
    }


    public String getFechaCrea() {
        return fecha_creacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getidCategoria() {
        return id_categoria;
    }

    /**
     * Compara los atributos de dos categorias
     *
     * @return true si son iguales, false si hay cambios
     */
    public boolean compararCon(Categorias Categorias) {
        return
                this.id_categoria.compareTo(Categorias.id_categoria) == 0 &&
                this.categoria.compareTo(Categorias.categoria) == 0 &&
                this.descripcion.compareTo(Categorias.descripcion) == 0 &&
                this.fecha_creacion.compareTo(Categorias.fecha_creacion) == 0;
    }
}
