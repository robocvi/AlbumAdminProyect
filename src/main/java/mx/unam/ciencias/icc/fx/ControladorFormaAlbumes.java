package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mx.unam.ciencias.icc.Album;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * albumes.
 */
public class ControladorFormaAlbumes extends ControladorForma {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para el año. */
    @FXML private EntradaVerificable entradaAño;
    /* La entrada verificable para la calificacion. */
    @FXML private EntradaVerificable entradaCalificacion;
    /* La entrada verificable para las canciones. */
    @FXML private EntradaVerificable entradaCanciones;
    /* La entrada verificable para el artista. */
    @FXML private EntradaVerificable entradaArtista;

    /* El valor del nombre. */
    private String nombre;
    /* El valor del año. */
    private int año;
    /* El valor de la calificacion. */
    private double calificacion;
    /* El valor de las canciones. */
    private int canciones;
    /* El valor del artista */
    private String artista;

    /* El album creado o editado. */
    private Album album;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaAño.setVerificador(c -> verificaAño(c));
        entradaCalificacion.setVerificador(p -> verificaCalificacion(p));
        entradaCanciones.setVerificador(e -> verificaCanciones(e));
        entradaArtista.setVerificador(a -> verificaArtista(a));

        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaAlbum());
        entradaAño.textProperty().addListener(
            (o, v, n) -> verificaAlbum());
        entradaCalificacion.textProperty().addListener(
            (o, v, n) -> verificaAlbum());
        entradaCanciones.textProperty().addListener(
            (o, v, n) -> verificaAlbum());
        entradaArtista.textProperty().addListener(
            (o, v, n) -> verificaAlbum());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaAlbum();
        aceptado = true;
        escenario.close();
    }

    /**
     * Define el album del diálogo.
     * @param album el nuevo album del diálogo.
     */
    public void setAlbum(Album album) {
        this.album = album;
        if (album == null)
            return;
        this.album = new Album(null, 0, 0, 0, null);
        this.album.actualiza(album);
        entradaNombre.setText(album.getNombre());
        String c = String.format("%04d", album.getAño());
        entradaAño.setText(c);
        String p = String.format("%2.2f", album.getCalificacion());
        entradaCalificacion.setText(p);
        entradaCanciones.setText(String.valueOf(album.getCanciones()));
        entradaArtista.setText(album.getArtista());
    }

    /* Verifica que los cinco campos sean válidos. */
    private void verificaAlbum() {
        boolean n = entradaNombre.esValida();
        boolean c = entradaAño.esValida();
        boolean p = entradaCalificacion.esValida();
        boolean e = entradaCanciones.esValida();
        boolean a = entradaArtista.esValida();
        botonAceptar.setDisable(!n || !c || !p || !e || !a);
    }

    /* Verifica que el nombre sea válido. */
    private boolean verificaNombre(String n) {
        if (n == null || n.isEmpty())
            return false;
        nombre = n;
        return true;
    }

    /* Verifica que el año sea válido. */
    private boolean verificaAño(String c) {
        if (c == null || c.isEmpty())
            return false;
        try {
            año = Integer.parseInt(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return año >= 1000 && año < 9999;
    }

    /* Verifica que la calificacion sea válido. */
    private boolean verificaCalificacion(String p) {
        if (p == null || p.isEmpty())
            return false;
        try {
            calificacion = Double.parseDouble(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return calificacion >= 0.0 && calificacion <= 10.0;
    }

    /* Verifica que el numero de canciones sea válido. */
    private boolean verificaCanciones(String e) {
        if (e == null || e.isEmpty())
            return false;
        try {
            canciones = Integer.parseInt(e);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return canciones >= 1 && canciones <= 99;
    }

    /* Verifica que el artista sea válido. */
    private boolean verificaArtista(String a) {
        if (a == null || a.isEmpty())
            return false;
        artista = a;
        return true;
    }

    /* Actualiza al album, o lo crea si no existe. */
    private void actualizaAlbum() {
        if (album != null) {
            album.setNombre(nombre);
            album.setAño(año);
            album.setCalificacion(calificacion);
            album.setCanciones(canciones);
            album.setArtista(artista);
        } else {
            album = new Album(nombre, año, calificacion, canciones, artista);
        }
    }

    /**
     * Regresa el album del diálogo.
     * @return el album del diálogo.
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaNombre.requestFocus();
    }
}
