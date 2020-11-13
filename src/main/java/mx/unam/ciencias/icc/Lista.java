package mx.unam.ciencias.icc;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
            this.siguiente = cabeza;
            this.anterior = null;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
            if(this.siguiente != null) {
              return true;
            }
            return false;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            // Aquí va su código.
            if(!hasNext()) {
              throw new NoSuchElementException();
            } else{
              T e = this.siguiente.elemento;
              this.anterior = this.siguiente;
              this.siguiente = this.siguiente.siguiente;
              return e;
            }
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            // Aquí va su código.
            if(this.anterior != null) {
              return true;
            }
            return false;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            // Aquí va su código.
            if(!hasPrevious()) {
              throw new NoSuchElementException();
            } else{
              T e = this.anterior.elemento;
              this.siguiente = this.anterior;
              this.anterior = this.anterior.anterior;
              return e;
            }
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            // Aquí va su código.
            this.siguiente = cabeza;
            this.anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            // Aquí va su código.
            this.anterior = rabo;
            this.siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        int i = 0;
    Nodo n = this.cabeza;
    while(n != null) {
      i++;
      n = n.siguiente;
    }
    return i;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
        if(longitud == 0) {
          return true;
        }
        return false;

    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        if (elemento == null) {
        throw new IllegalArgumentException();
      }
      Nodo n = new Nodo(elemento);
      if(esVacia()) {
        cabeza = rabo = n;
      } else{
        n.anterior = rabo;
        rabo.siguiente = n;
        rabo = n;
      } longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        if(elemento == null) {
        throw new IllegalArgumentException();
      }
      Nodo n = new Nodo(elemento);
      if(esVacia()) {
        cabeza = rabo = n;
      } else{
        n.siguiente = cabeza;
        cabeza.anterior = n;
        cabeza = n;
      } longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
        if(elemento == null) {
      throw new IllegalArgumentException();
        } else{
      if (i <= 0) {
          this.agregaInicio(elemento);
        } else
        if(i >= this.getLongitud()) {
          this.agregaFinal(elemento);
        } else{
          Nodo m = new Nodo(elemento);
          Nodo n = this.buscaNodo(this.get(i));
          n.anterior.siguiente = m;
          m.anterior = n.anterior;
          m.siguiente = n;
          n.anterior = m;
         longitud++;
       }
    }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        // Aquí va su código.
        Nodo n = this.buscaNodo(elemento);
    if(n!=null) {
      if(n == this.cabeza) {
        this.eliminaPrimero();
      } else{
        if(n == this.rabo) {
          this.eliminaUltimo();
        } else{
          n.anterior.siguiente = n.siguiente;
          n.siguiente.anterior = n.anterior;
          this.longitud--;
        }
      }
    }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        if(esVacia()) {
          throw new NoSuchElementException();
        }
        Nodo n = this.cabeza;
        T e = this.cabeza.elemento;
        if(this.cabeza == this.rabo) {
          cabeza = rabo = null;
        } else{
          cabeza = cabeza.siguiente;
          cabeza.anterior = null;
        }
        longitud--;
        return e;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if(esVacia()) {
          throw new NoSuchElementException();
        }
        Nodo n = this.rabo;
        T e = this.rabo.elemento;
        if(this.cabeza == this.rabo) {
          cabeza = rabo = null;
        } else{
          rabo = rabo.anterior;
          rabo.siguiente = null;
        }
        longitud--;
        return e;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(T elemento) {
        // Aquí va su código.
        return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
        Lista<T> l = new Lista<T>();
        Nodo n = this.cabeza;
        while(n != null) {
          l.agregaInicio(n.elemento);
          n = n.siguiente;
        }
        return l;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
        Lista<T> l = new Lista<T>();
        Nodo n = this.cabeza;
        while(n != null) {
          l.agregaFinal(n.elemento);
          n = n.siguiente;
        }
        return l;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
        if(esVacia()) {
          return;
        }
        if(longitud == 1) {
          cabeza = rabo = null;
        } else{
          Nodo n = this.cabeza;
          while(n != null) {
            this.eliminaPrimero();
            n = n.siguiente;
          }
        }
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        if(esVacia()) {
          throw new NoSuchElementException();
        }
        T e = this.cabeza.elemento;
        return e;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        if(esVacia()) {
          throw new NoSuchElementException();
        }
        T e = this.rabo.elemento;
        return e;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
        if(i < 0 || i >= getLongitud()) {
          throw new ExcepcionIndiceInvalido();
        }
        Nodo n = this.cabeza;
        while(i-- > 0) {
          n = n.siguiente;
        }
        return n.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
        int i = 0;
      Nodo n = this.cabeza;
      while(n!=null) {
        if(n.elemento.equals(elemento)) {
          return i;
      }
        n = n.siguiente;
        i++;
      }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
        if(esVacia()) {
          return "[]";
        }
        Nodo n = this.cabeza;
        return auxString(n, "[");
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
        if(lista == null) {
          return false;
        }
        if(this == null) {
          return false;
        }
        if(lista.esVacia() && this.esVacia()) {
          return true;
        }
        if(lista.esVacia() && this.noEsVacia()) {
          return false;
        }
        if(lista.noEsVacia() && this.esVacia()) {
          return false;
        }
        if(lista.longitud != this.longitud) {
          return false;
        }
        Nodo n = this.cabeza;
        Nodo m = lista.cabeza;
        return equalsAux(n, m);
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        // Aquí va su código.
        return mergeSort(this, comparador);
    }

    private Lista<T> mergeSort(Lista<T> l, Comparator<T> comparador) {
      if(l.cabeza == l.rabo) {
        return l.copia();
      }
      Lista<T> lizquierda = new Lista<>();
      Lista<T> lderecha = new Lista<>();
      int i = 0;
      for(T t : l) {
        Lista<T> ll = (i++ < l.getLongitud()/2) ?
                                                lizquierda :
                                                          lderecha;
        ll.agregaFinal(t);
      }
      return merge(mergeSort(lizquierda, comparador), mergeSort(lderecha, comparador), comparador);
    }

    private static <T>
    Lista<T> merge(Lista <T> lderecha, Lista<T> lizquierda, Comparator<T> comparador) {
      Lista<T>.Nodo n1 = lizquierda.cabeza;
      Lista<T>.Nodo n2 = lderecha.cabeza;
      Lista<T> l = new Lista<>();
      while(n1 != null && n2 != null) {
        if(comparador.compare(n1.elemento, n2.elemento) < 0) {
          l.agregaFinal(n1.elemento);
          n1 = n1.siguiente;
        }else{
          l.agregaFinal(n2.elemento);
          n2 = n2.siguiente;
        }
      }
      Lista<T>.Nodo n = (n1 != null) ? n1 :
                                           n2;
      while(n != null) {
              l.agregaFinal(n.elemento);
              n = n.siguiente;
        }
        return l;
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <tt>true</tt> si el elemento está contenido en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        Nodo n = this.cabeza;
          while(n != null) {
            if(comparador.compare(elemento, n.elemento) == 0) {
              return true;
            }
            n = n.siguiente;
          }
          return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <tt>true</tt> si el elemento está contenido en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }

    private String auxString(Nodo nodo, String s) {
      if(nodo == this.rabo)
        return s + nodo.elemento + "]";
      return auxString(nodo.siguiente, s+ nodo.elemento+ ", ");
    }

    private boolean equalsAux(Nodo n, Nodo m) {
      if(n == null)
        return true;
      if(n.elemento.equals(m.elemento)) {
        return equalsAux(n.siguiente, m.siguiente);
      } else{
        return false;
      }
    }

    private boolean noEsVacia() {
      if(longitud != 0) {
        return true;
      } else{
        return false;
      }
    }

    private Nodo buscaNodo(T elemento) {
      Nodo n = cabeza;
        return buscaNodoAux(elemento, n);
    }

    private Nodo buscaNodoAux(T elemento, Nodo n) {
      return n==null ? null
                     : n.elemento.equals(elemento) ? n
                                                   : buscaNodoAux(elemento, n.siguiente);
    }
}
