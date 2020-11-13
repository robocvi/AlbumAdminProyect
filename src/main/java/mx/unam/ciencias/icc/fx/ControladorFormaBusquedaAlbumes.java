package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoAlbum;

/**
 * Clase para el controlador del contenido del diálogo para buscar albumes.
 */
public class ControladorFormaBusquedaAlbumes extends ControladorForma {

    /* El combo del campo. */
    @FXML private ComboBox<CampoAlbum> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        String s = entradaValor.getText();
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case NOMBRE:
            m = "Buscar por nombre necesita al menos un carácter";
            break;
        case AÑO:
            m = "Buscar por año necesita un número entre " +
                "1000 y 9999";
            break;
        case CALIFICACION:
            m = "Buscar por calificacion necesita un número entre 0.0 y 10.0";
            break;
        case CANCIONES:
            m = "Buscar por canciones necesita un número entre 1 y 99";
            break;
        case ARTISTA:
            m = "Buscar por artista necesita al menos un carácter";
            break;
        }
        return new Tooltip(m);
    }

    /* Verifica el valor. */
    private boolean verificaValor(String s) {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:       return verificaNombre(s);
        case AÑO:          return verificaAño(s);
        case CALIFICACION: return verificaCalificacion(s);
        case CANCIONES:    return verificaCanciones(s);
        case ARTISTA:      return verificaArtista(s);
        default:           return false;
        }
    }

    /* Verifica que el nombre a buscar sea válido. */
    private boolean verificaNombre(String n) {
        return n != null && !n.isEmpty();
    }

    /* Verifica que el año a buscar sea válido. */
    private boolean verificaAño(String c) {
        if (c == null || c.isEmpty())
            return false;
        int año = -1;
        try {
            año = Integer.parseInt(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return año >= 1000 && año < 9999;
    }

    /* Verifica que la calificacion a buscar sea válido. */
    private boolean verificaCalificacion(String p) {
        if (p == null || p.isEmpty())
            return false;
        double cal = -1.0;
        try {
            cal = Double.parseDouble(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return cal >= 0.0 && cal <= 10.0;
    }

    /* Verifica que el numero de canciones a buscar sea válida. */
    private boolean verificaCanciones(String e) {
        if (e == null || e.isEmpty())
            return false;
        int can = -1;
        try {
            can = Integer.parseInt(e);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return can >= 1 && can <= 99;
    }

    /* Verifica que el artista a buscar sea válido. */
    private boolean verificaArtista(String a) {
        return a != null && !a.isEmpty();
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoAlbum getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:       return entradaValor.getText();
        case AÑO:          return Integer.parseInt(entradaValor.getText());
        case CALIFICACION: return Double.parseDouble(entradaValor.getText());
        case CANCIONES:    return Integer.parseInt(entradaValor.getText());
        case ARTISTA:      return entradaValor.getText();
        default:           return entradaValor.getText(); // No debería ocurrir.
        }
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
