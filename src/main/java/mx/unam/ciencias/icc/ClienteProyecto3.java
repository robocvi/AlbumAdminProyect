package mx.unam.ciencias.icc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.fx.ControladorInterfazAlbumes;
import mx.unam.ciencias.icc.fx.ControladorTablaAlbumes;

/**
 * ClienteProyecto3: Parte del cliente para proyecto 3
 */
public class ClienteProyecto3 extends Application {

    /* Vista de la interfaz albumes. */
    private static final String INTERFAZ_ALBUMES_FXML =
        "fxml/interfaz-albumes.fxml";
    /* Vista de la tabla de albumes. */
    private static final String TABLA_ALBUMES_FXML =
        "fxml/tabla-albumes.fxml";
    /* Ícono. */
    private static final String ICONO_CIENCIAS =
        "icons/icono.png";

    /**
     * Inicia la aplicación.
     * @param escenario la ventana principal de la aplicación.
     * @throws Exception si algo sale mal. Literalmente así está definido.
     */
    @Override public void start(Stage escenario) throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        String url = cl.getResource(ICONO_CIENCIAS).toString();
        escenario.getIcons().add(new Image(url));
        escenario.setTitle("Administrador de Albumes");

        FXMLLoader cargador =
            new FXMLLoader(cl.getResource(TABLA_ALBUMES_FXML));
        GridPane tablaAlbum = (GridPane)cargador.load();
        ControladorTablaAlbumes controladorTablaAlbumes =
            cargador.getController();

        cargador = new FXMLLoader(cl.getResource(INTERFAZ_ALBUMES_FXML));
        BorderPane interfazAlbumes = (BorderPane)cargador.load();
        interfazAlbumes.setCenter(tablaAlbum);
        ControladorInterfazAlbumes controladorInterfazAlbumes =
            cargador.getController();
        controladorInterfazAlbumes.setEscenario(escenario);
        controladorInterfazAlbumes.setControladorTablaAlbumes(
            controladorTablaAlbumes);

        Scene escena = new Scene(interfazAlbumes);
        escenario.setScene(escena);
        escenario.setOnCloseRequest(e -> controladorInterfazAlbumes.salir(null));
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
