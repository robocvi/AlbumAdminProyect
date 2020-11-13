package mx.unam.ciencias.icc.red;

import java.io.IOException;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosAlbumes;
import mx.unam.ciencias.icc.CampoAlbum;
import mx.unam.ciencias.icc.Album;

/**
 * Clase para servidores de bases de datos de albumes.
 */
public class ServidorBaseDeDatosAlbumes
    extends ServidorBaseDeDatos<Album> {

    /**
     * Construye un servidor de base de datos de albumes.
     * @param puerto el puerto dónde escuchar por conexiones.
     * @param archivo el archivo en el disco del cual cargar/guardar la base de
     *                datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatosAlbumes(int puerto, String archivo)
        throws IOException {
        // Aquí va su código.
        super(puerto, archivo);
    }

    /**
     * Crea una base de datos de albumes.
     * @return una base de datos de albumes.
     */
    @Override public
    BaseDeDatos<Album, CampoAlbum> creaBaseDeDatos() {
        BaseDeDatosAlbumes bdd = new BaseDeDatosAlbumes();
        return bdd;
    }
}
