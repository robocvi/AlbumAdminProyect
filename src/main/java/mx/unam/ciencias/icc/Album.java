package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar albumes. Un album tiene nombre, año
 * calificacion, canciones y nombre del artista. La clase implementa {@link Registro}, por lo que
 * puede cargarse y guardarse utilizando objetos de las clases {@link
 * BufferedReader} y {@link BufferedWriter} como entrada y salida
 * respectivamente, además de determinar si sus campos cazan valores
 * arbitrarios y actualizarse con los valores de otro estudiante.
 */
public class Album implements Registro<Album, CampoAlbum> {

    /* Nombre del album. */
    private StringProperty nombre;
    /* Año de lanzamiento. */
    private IntegerProperty año;
    /* Calificacion del album.*/
    private DoubleProperty calificacion;
    /* Numero de canciones.*/
    private IntegerProperty canciones;
    /* Nombre del artista.*/
    private StringProperty artista;

    /**
     * Define el estado inicial de un album.
     * @param nombre el nombre del album.
     * @param año el año de lanzamiento del album.
     * @param calificacion la calificacion del album.
     * @param canciones el numero de canciones del album.
     * @param artista el nombre del artista que hizo el album.
     */
    public Album(String nombre,
                      int    año,
                      double calificacion,
                      int    canciones,
                      String artista) {
        this.nombre = new SimpleStringProperty(nombre);
        // Aquí va su código.
        this.año = new SimpleIntegerProperty(año);
        this.calificacion = new SimpleDoubleProperty(calificacion);
        this.canciones = new SimpleIntegerProperty(canciones);
        this.artista = new SimpleStringProperty(artista);
    }

    /**
     * Regresa el nombre del album.
     * @return el nombre del album.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre del album.
     * @param nombre el nuevo nombre del album.
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty nombreProperty() {
        return this.nombre;
    }

    /**
     * Regresa el año del album.
     * @return el al año del album.
     */
    public int getAño() {
        // Aquí va su código.
        return año.get();
    }

    /**
     * Define el año de lanzamiento del album.
     * @param año el nuevo año de lanzamiento del album.
     */
    public void setAño(int año) {
        // Aquí va su código.
        this.año.set(año);
    }

    /**
     * Regresa la propiedad del año del album.
     * @return la propiedad del año del album.
     */
    public IntegerProperty añoProperty() {
        // Aquí va su código.
        return this.año;
    }

    /**
     * Regresa la calificacion del album.
     * @return la calificacion del album.
     */
    public double getCalificacion() {
        // Aquí va su código.
        return calificacion.get();
    }

    /**
     * Define la claificacion del album.
     * @param calificacion la nueva calificacion del estudiante.
     */
    public void setCalificacion(double calificacion) {
        // Aquí va su código.
        this.calificacion.set(calificacion);
    }

    /**
     * Regresa la propiedad de la calificacion.
     * @return la propiedad de la calificacion.
     */
    public DoubleProperty calificacionProperty() {
        // Aquí va su código.
        return this.calificacion;
    }

    /**
     * Regresa el numero de canciones del album.
     * @return el numero de canciones del album.
     */
    public int getCanciones() {
        // Aquí va su código.
        return canciones.get();
    }

    /**
     * Define el numero de canciones del album.
     * @param canciones el numero de canciones del album.
     */
    public void setCanciones(int canciones) {
        // Aquí va su código.
        this.canciones.set(canciones);
    }

    /**
     * Regresa la propiedad del numero de canciones.
     * @return la propiedad del numero de canciones.
     */
    public IntegerProperty cancionesProperty() {
        // Aquí va su código.
        return this.canciones;
    }

    /**
     * Regresa el nombre de artista.
     * @return el nombre del artista.
     */
    public String getArtista() {
        // Aquí va su código.
        return artista.get();
    }

    /**
     * Define el nombre del artista.
     * @param artista el nombre del artista.
     */
    public void setArtista(String artista) {
        // Aquí va su código.
        this.artista.set(artista);
    }

    /**
     * Regresa la propiedad del nombre del artista.
     * @return la propiedad del nombre del artista.
     */
    public StringProperty artistaProperty() {
        // Aquí va su código.
        return this.artista;
    }

    /**
     * Regresa una representación en cadena del album.
     * @return una representación en cadena del album.
     */
    @Override public String toString() {
        // Aquí va su código.
        String cadena = String.format("Nombre   : %s\n" +
                             "Año   : %04d\n" +
                             "Calificacion : %2.2f\n" +
                             "Canciones     : %d\n" +
                             "Artista       : %s",
                             this.getNombre(), this.getAño(), this.getCalificacion(), this.getCanciones(), this.getArtista());
      return cadena;
    }

    /**
     * Nos dice si el objeto recibido es un album igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el album se comparará.
     * @return <tt>true</tt> si el objeto o es un album con las mismas
     *         propiedades que el objeto que manda llamar al método,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Album))
            return false;
        Album album = (Album)objeto;
        // Aquí va su código.
        if(this.getNombre().equals(album.getNombre()) && this.getAño() == album.getAño()
           && this.getCalificacion() == album.getCalificacion() && this.getCanciones() == album.getCanciones()
           && this.getArtista().equals(album.getArtista())) {
             return true;
           }
           return false;
    }

    /**
     * Guarda al album en la salida recibida.
     * @param out la salida dónde hay que guardar al album.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    @Override public void guarda(BufferedWriter out) throws IOException {
        // Aquí va su código.
        String s = String.format("%s\t%d\t%2.2f\t%d\t%s\n",
                             this.getNombre(), this.getAño(), this.getCalificacion(), this.getCanciones(), this.getArtista());
      out.write(s);
    }

    /**
     * Carga al album de la entrada recibida.
     * @param in la entrada de dónde hay que cargar al album.
     * @return <tt>true</tt> si el método carga un album válido,
     *         <tt>false</tt> en otro caso.
     * @throws IOException si un error de entrada/salida ocurre, o si la entrada
     *         recibida no contiene a un album.
     */
    @Override public boolean carga(BufferedReader in) throws IOException {
        // Aquí va su código.
        String l = in.readLine();
        if(l == null) {
          return false;
        }
        l = l.trim();
        if(l.equals("")) {
          return false;
        }
        String[] t = l.split("\t");
        if(t.length != 5) {
          throw new IOException("Eso no se puede bro");
        }

        try {
          this.setNombre(t[0]);
          this.setAño(Integer.parseInt(t[1]));
          this.setCalificacion(Double.parseDouble(t[2]));
          this.setCanciones(Integer.parseInt(t[3]));
          this.setArtista(t[4]);
        } catch(NumberFormatException nfe) {
          throw new IOException("Indice invalido bro");
        }
        return true;
    }

    /**
     * Nos dice si el album caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <tt>true</tt> si:
     *         <ul>
     *           <li><tt>campo</tt> es {@link CampoAlbum#NOMBRE} y
     *              <tt>valor</tt> es instancia de {@link String} y es una
     *              subcadena del nombre del album.</li>
     *           <li><tt>campo</tt> es {@link CampoAlbum#AÑO} y
     *              <tt>valor</tt> es instancia de {@link Integer} y su
     *              valor entero es mayor o igual a el año de lanzamiento
     *              del disco.</li>
     *           <li><tt>campo</tt> es {@link CampoAlbum#CALIFICACION} y
     *              <tt>valor</tt> es instancia de {@link Double} y su
     *              valor doble es mayor o igual a la calificacion del
     *              album.</li>
     *           <li><tt>campo</tt> es {@link CampoAlbum#CANCIONES} y
     *              <tt>valor</tt> es instancia de {@link Integer} y su
     *              valor entero es mayor o igual a la cantidad de canciones
     *              del album.</li>
                 <li><tt>campo</tt> es {@link CampoAlbum#ARTISTA} y
     *              <tt>valor</tt> es instancia de {@link String} y es una
     *              subcadena del artista.</li>
     *         </ul>
     *         <tt>false</tt> en otro caso.
     * @throws IllegalArgumentException si el campo no es instancia de
     *         {@link CampoAlbum}.
     */
    @Override public boolean caza(CampoAlbum campo, Object valor) {
        // Aquí va su código.
        if (!(campo instanceof CampoAlbum))
        throw new IllegalArgumentException("El campo debe ser" + "CampoAlbum");
        CampoAlbum c = (CampoAlbum)campo;

        switch(c) {
          case NOMBRE:
           return cazaNombre(valor);
          case AÑO:
           return cazaAño(valor);
          case CALIFICACION:
           return cazaCalificacion(valor);
          case CANCIONES:
           return cazaCanciones(valor);
          case ARTISTA:
           return cazaArtista(valor);
          default:
           return false;
        }
    }

    /**
     * Actualiza los valores del album con los del registro recibido.
     * @param album el album con el cual actualizar los valores.
     */
    @Override public void actualiza(Album album) {
        setNombre(album.getNombre());
        // Aquí va su código.
        setAño(album.getAño());
        setCalificacion(album.getCalificacion());
        setCanciones(album.getCanciones());
        setArtista(album.getArtista());
    }

    private boolean cazaAño(Object valor) {
    if(valor instanceof Integer && (int)valor <= this.getAño()) {
      return true;
    }
    return false;
  }

    private boolean cazaCalificacion(Object valor) {
    if(valor instanceof Double && (double)valor <= this.getCalificacion()) {
      return true;
    }
    return false;
  }

    private boolean cazaCanciones(Object valor) {
    if(valor instanceof Integer && (int)valor <= this.getCanciones()) {
      return true;
    }
    return false;
  }

    private boolean cazaNombre(Object valor) {
    if(valor instanceof String && this.getNombre().contains((String)valor) && !valor.equals("")) {
      return true;
    }
    return false;
  }

    private boolean cazaArtista(Object valor) {
    if(valor instanceof String && this.getArtista().contains((String)valor) && !valor.equals("")) {
      return true;
    }
    return false;
  }
}
