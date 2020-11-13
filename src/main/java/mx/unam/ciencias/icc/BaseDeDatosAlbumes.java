package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de albumes.
 */
public class BaseDeDatosAlbumes
    extends BaseDeDatos<Album, CampoAlbum> {

    /**
     * Crea un album en blanco.
     * @return un album en blanco.
     */
    @Override public Album creaRegistro() {
        // Aquí va su código.
        Album a = new Album(null, 0, 0.0, 0, null);
        return a;
    }
}
