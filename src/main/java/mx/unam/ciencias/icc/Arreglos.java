package mx.unam.ciencias.icc;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        for(int i = 0; i < arreglo.length; i++) {
          for(int j = i+1; j < arreglo.length; j++) {
            if(comparador.compare(arreglo[j],arreglo[i]) < 0)
              swap(arreglo, i, j);
          }
        }
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        if(arreglo == null || arreglo.length == 0) {
          return;
        }
        int i = 0;
        int f = arreglo.length-1;
        int mid = (i+f)/2;
        if(i >= f) {
          return;
        }
        T piv = arreglo[mid];
          while(i <= f) {
            while(comparador.compare(arreglo[i], piv) < 0) {
              i++;
            }
            while(comparador.compare(arreglo[f], piv) > 0) {
              f--;
            }
            if(i <= f) {
              swap(arreglo, i, f);
              i++;
              f--;
            }
          }
          if(0 < f) {
            quickSortAux(arreglo, comparador, 0, f);
          }
          if(arreglo.length-1 > i) {
            quickSortAux(arreglo, comparador, i, arreglo.length-1);
          }
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        int i = 0;
      int f = arreglo.length-1;
      int piv;
        while (i <= f) {
          piv = (i+f)/2;
          if (comparador.compare(arreglo[piv], elemento) == 0) {
              return piv;
          } else {
            if (comparador.compare(arreglo[piv], elemento) < 0) {
              i = piv+1;
          } else {
              f = piv-1;
         }
       }
     }
        return -1;
    }

    private static <T> void swap(T[] arreglo, int i, int j) {
      T t = arreglo[i];
      arreglo[i] = arreglo[j];
      arreglo[j] = t;
    }

    private static <T> void
    quickSortAux(T[] arreglo, Comparator<T> comparador, int inicio, int fin) {
      int i = inicio;
      int f = fin;
      if(i >= f) {
        return;
      }
      int mid = (i+f)/2;
      T piv = arreglo[mid];
      while(i <= f) {
        while(comparador.compare(arreglo[i], piv) < 0) {
          i++;
        }
        while(comparador.compare(arreglo[f], piv) > 0) {
          f--;
        }
        if(i <= f) {
          swap(arreglo, i, f);
          i++;
          f--;
        }
      }
      if(inicio < f) {
        quickSortAux(arreglo, comparador, inicio, f);
      }
      if(fin > i) {
        quickSortAux(arreglo, comparador, i, fin);
      }
    }
}
