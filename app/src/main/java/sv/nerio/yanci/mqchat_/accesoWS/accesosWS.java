package sv.nerio.yanci.mqchat_.accesoWS;

/**
 * Created by YANCI on 30/06/2015.
 */
public class accesosWS {
    public static final int CODIGO_DETALLE = 100;
    public static final int CODIGO_ACTUALIZACION = 101;
     /**
     * URLs del WebService
     */


    public static final String GET = "http://mqchatws.hol.es/obtener_temas.php";
    public static final String GET_BY_ID = "http://mqchatws.hol.es/obtener_tema_por_id.php";
    public static final String UPDATE = "http://mqchatws.hol.es/actualizar_tema.php";
    public static final String DELETE = "http://mqchatws.hol.es/borrar_tema.php";
    public static final String INSERT = "http://mqchatws.hol.es/insertar_tema.php";
    public static final String GET_CATE = "http://mqchatws.hol.es/obtener_categorias.php";
    public static final String GET_TEMAS_POR_CATE = "http://mqchatws.hol.es/obtener_tema_por_cate.php";
    public static final String SUSCRIBIR = "http://mqchatws.hol.es/suscribir.php";
    /**
     * ID para el valor extra que representa al identificador de un tema
     */
    public static final String EXTRA_ID = "IDEXTRA";

}
