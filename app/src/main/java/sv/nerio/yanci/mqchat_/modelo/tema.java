package sv.nerio.yanci.mqchat_.modelo;

/**
 * Created by YANCI on 30/06/2015.
 */
public class tema {
    private static final String TAG = tema.class.getSimpleName();
    /*
        Atributos
         */
    private String idTema;
    private String nombre;
    private String descripcion;
    private String estado;
    private String fechaCrea;
    private String id_categoria;
    private String contadorSuscripciones;

    public tema(String idTema,String nombre,String descripcion,String fechaCrea,String id_categoria,String estado, String contadorSuscripciones) {
        this.idTema = idTema;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.id_categoria = id_categoria;
        this.contadorSuscripciones = contadorSuscripciones;
    }

    public String getIdTema() {
        return idTema;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaCrea() {
        return fechaCrea;
    }

    public String getCategoria() {
        return id_categoria;
    }

    public String getContador() {
        return contadorSuscripciones;
    }
    /**
     * Compara los atributos de dos temas
     *
     * @return true si son iguales, false si hay cambios
     */
    public boolean compararCon(tema tema) {
        return this.nombre.compareTo(tema.nombre) == 0 &&
                this.descripcion.compareTo(tema.descripcion) == 0 &&
                this.fechaCrea.compareTo(tema.fechaCrea) == 0 &&
                this.id_categoria.compareTo(tema.id_categoria) == 0 &&
                this.estado.compareTo(tema.estado) == 0 ;
    }
}
