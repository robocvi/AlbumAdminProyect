package mx.unam.ciencias.icc.fx;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import mx.unam.ciencias.icc.Album;
import mx.unam.ciencias.icc.Lista;

/**
 * Clase para el controlador de la tabla para mostrar la base de datos.
 */
public class ControladorTablaAlbumes {

    /* La tabla. */
    @FXML private TableView<Album> tabla;

    /* La columna del nombre. */
    @FXML private TableColumn<Album, String> columnaNombre;
    /* La columna del año del album. */
    @FXML private TableColumn<Album, Number> columnaAño;
    /* La columna de la calificacion. */
    @FXML private TableColumn<Album, Number> columnaCalificacion;
    /* La columna del numero de canciones. */
    @FXML private TableColumn<Album, Number> columnaCanciones;
    /* La columna del nombre del artista. */
    @FXML private TableColumn<Album, String> columnaArtista;

    /* El modelo de la selección. */
    TableView.TableViewSelectionModel<Album> modeloSeleccion;
    /* La selección. */
    private ObservableList<TablePosition> seleccion;
    /* Lista de escuchas de selección. */
    private Lista<EscuchaSeleccion> escuchas;
    /* Los renglones en la tabla. */
    private ObservableList<Album> renglones;

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        renglones = tabla.getItems();
        modeloSeleccion = tabla.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.MULTIPLE);
        seleccion = modeloSeleccion.getSelectedCells();
        ListChangeListener<TablePosition> lcl = c -> cambioEnSeleccion();
        seleccion.addListener(lcl);
        columnaNombre.setCellValueFactory(c -> c.getValue().nombreProperty());
        columnaAño.setCellValueFactory(c -> c.getValue().añoProperty());
        columnaCalificacion.setCellValueFactory(
            c -> c.getValue().calificacionProperty());
        columnaCanciones.setCellValueFactory(c -> c.getValue().cancionesProperty());
        columnaArtista.setCellValueFactory(c -> c.getValue().artistaProperty());
        escuchas = new Lista<EscuchaSeleccion>();
    }

    /* Notifica a los escuchas que hubo un cambio en la selección. */
    private void cambioEnSeleccion() {
        for (EscuchaSeleccion escucha : escuchas)
            escucha.renglonesSeleccionados(seleccion.size());
    }

    /**
     * Limpia la tabla.
     */
    public void limpiaTabla() {
        renglones.clear();
    }

    /**
     * Agrega un renglón a la tabla.
     * @param album el renglón a agregar.
     */
    public void agregaRenglon(Album album) {
        renglones.add(album);
        tabla.sort();
    }

    /**
     * Elimina un renglón de la tabla.
     * @param album el renglón a eliminar.
     */
    public void eliminaRenglon(Album album) {
        renglones.remove(album);
        tabla.sort();
    }

    /**
     * Selecciona renglones de la tabla.
     * @param albumes los renglones a seleccionar.
     */
    public void seleccionaRenglones(Lista<Album> albumes) {
        modeloSeleccion.clearSelection();
        for (Album album : albumes)
            modeloSeleccion.select(album);
    }

    /**
     * Regresa una lista con los registros seleccionados en la tabla.
     * @return una lista con los registros seleccionados en la tabla.
     */
    public Lista<Album> getSeleccion() {
        Lista<Album> seleccionados = new Lista<Album>();
        for (TablePosition tp : seleccion) {
            int r = tp.getRow();
            seleccionados.agregaFinal(renglones.get(r));
        }
        return seleccionados;
    }

    /**
     * Regresa el album seleccionado en la tabla.
     * @return el album seleccionado en la tabla.
     */
    public Album getRenglonSeleccionado() {
        int r = seleccion.get(0).getRow();
        return renglones.get(r);
    }

    /**
     * Agrega un escucha de selección.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscuchaSeleccion(EscuchaSeleccion escucha) {
        escuchas.agregaFinal(escucha);
    }

    /**
     * Fuerza un reordenamiento de la tabla.
     */
    public void reordena() {
        tabla.sort();
    }

    /**
     * Enfoca la tabla.
     */
    public void enfocaTabla() {
        tabla.requestFocus();
    }
}
