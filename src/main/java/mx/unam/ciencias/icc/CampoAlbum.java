package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Estudiante}.
 */
public enum CampoAlbum {

    /** El nombre del album. */
    NOMBRE,
    /** El año del album. */
    AÑO,
    /** La calificacion del album. */
    CALIFICACION,
    /** El numero de canciones del album. */
    CANCIONES,
    /** El nombre del artista. */
    ARTISTA;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        switch(this) {
          case NOMBRE:
            return "Nombre";
          case AÑO:
            return "Año";
          case CALIFICACION:
            return "Calificacion";
          case CANCIONES:
            return "Canciones";
          case ARTISTA:
            return "Artista";
        }
        return "";
    }
}
