import java.util.Scanner;

public class ListaEnlazada {

    static class Nodo {
        int dato;
        Nodo anterior;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }
    }

    static class Lista {
        Nodo cabeza;
        Nodo cola;
        int tamanio;

        public Lista() {
            this.cabeza = null;
            this.cola = null;
            this.tamanio = 0;
        }

        public void insertarAlInicio(int dato) {
            Nodo nuevoNodo = new Nodo(dato);
            if (cabeza == null) {
                cabeza = nuevoNodo;
                cola = nuevoNodo;
            } else {
                nuevoNodo.siguiente = cabeza;
                cabeza.anterior = nuevoNodo;
                cabeza = nuevoNodo;
            }
            tamanio++;
            System.out.println("Elemento " + dato + " insertado al inicio.");
        }

        public void insertarAlFinal(int dato) {
            Nodo nuevoNodo = new Nodo(dato);
            if (cola == null) {
                cabeza = nuevoNodo;
                cola = nuevoNodo;
            } else {
                nuevoNodo.anterior = cola;
                cola.siguiente = nuevoNodo;
                cola = nuevoNodo;
            }
            tamanio++;
            System.out.println("Elemento " + dato + " insertado al final.");
        }

        public void recorrerHaciaAdelante() {
            if (cabeza == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            System.out.print("Recorrido hacia adelante: ");
            Nodo actual = cabeza;
            while (actual != null) {
                System.out.print("[" + actual.dato + "]");
                if (actual.siguiente != null) System.out.print(" <-> ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        public void recorrerHaciaAtras() {
            if (cola == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            System.out.print("Recorrido hacia atras: ");
            Nodo actual = cola;
            while (actual != null) {
                System.out.print("[" + actual.dato + "]");
                if (actual.anterior != null) System.out.print(" <-> ");
                actual = actual.anterior;
            }
            System.out.println();
        }

        public void mostrarTamanio() {
            System.out.println("Tamaño de la lista: " + tamanio + " elemento(s).");
        }

        public void mostrarSiEstaVacia() {
            if (tamanio == 0) {
                System.out.println("La lista esta vacia");
            } else {
                System.out.println("La lista NO esta vacia. Contiene " + tamanio + " elemento(s).");
            }
        }

        public void buscarPorValor(int valor) {
            if (cabeza == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            Nodo actual = cabeza;
            int indice = 0;
            boolean encontrado = false;
            while (actual != null) {
                if (actual.dato == valor) {
                    System.out.println("Elemento " + valor + " encontrado en el indice " + indice + ".");
                    encontrado = true;
                }
                actual = actual.siguiente;
                indice++;
            }
            if (!encontrado) {
                System.out.println("Elemento " + valor + " no encontrado en la lista.");
            }
        }

        public void buscarPorIndice(int indice) {
            if (cabeza == null) {
                System.out.println("La lista esta vacia.");
                return;
            }
            if (indice < 0 || indice >= tamanio) {
                System.out.println("Indice " + indice + " fuera de rango. La lista tiene " + tamanio + " elemento(s). (indices: 0 a " + (tamanio - 1) + ")");
                return;
            }
            Nodo actual = cabeza;
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
            System.out.println("Elemento en el indice " + indice + ": " + actual.dato);
        }

        public void borrarElemento(int valor) {
            if (cabeza == null) {
                System.out.println("La lista esta vacia. No se puede borrar.");
                return;
            }
            Nodo actual = cabeza;
            while (actual != null) {
                if (actual.dato == valor) {
                    if (actual.anterior != null) {
                        actual.anterior.siguiente = actual.siguiente;
                    } else {
                        cabeza = actual.siguiente;
                    }
                    if (actual.siguiente != null) {
                        actual.siguiente.anterior = actual.anterior;
                    } else {
                        cola = actual.anterior;
                    }
                    tamanio--;
                    System.out.println("Elemento " + valor + " eliminado de la lista.");
                    return;
                }
                actual = actual.siguiente;
            }
            System.out.println("Elemento " + valor + " NO encontrado. No se elimino nada.");
        }
    }

    public static void main(String[] args) {
        Lista lista = new Lista();

        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("     LISTA DOBLEMENTE ENLAZADA        ");
                System.out.println("  1. Insertar al inicio               ");
                System.out.println("  2. Insertar al final                ");
                System.out.println("  3. Recorrer hacia adelante          ");
                System.out.println("  4. Recorrer hacia atras             ");
                System.out.println("  5. Mostrar tamaño de la lista       ");
                System.out.println("  6. Mostrar si la lista esta vacia   ");
                System.out.println("  7. Buscar elemento por valor        ");
                System.out.println("  8. Buscar elemento por indice       ");
                System.out.println("  9. Borrar un elemento               ");
                System.out.println("  0. Salir                            ");
                System.out.print("Seleccione una opcion: ");

                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese el valor a insertar al inicio: ");
                        lista.insertarAlInicio(scanner.nextInt());
                    }
                    case 2 -> {
                        System.out.print("Ingrese el valor a insertar al final: ");
                        lista.insertarAlFinal(scanner.nextInt());
                    }
                    case 3 -> lista.recorrerHaciaAdelante();
                    case 4 -> lista.recorrerHaciaAtras();
                    case 5 -> lista.mostrarTamanio();
                    case 6 -> lista.mostrarSiEstaVacia();
                    case 7 -> {
                        System.out.print("Ingrese el valor a buscar: ");
                        lista.buscarPorValor(scanner.nextInt());
                    }
                    case 8 -> {
                        System.out.print("Ingrese el indice a buscar: ");
                        lista.buscarPorIndice(scanner.nextInt());
                    }
                    case 9 -> {
                        System.out.print("Ingrese el valor del elemento a borrar: ");
                        lista.borrarElemento(scanner.nextInt());
                    }
                    case 0 -> System.out.println("Saliendo del programa.");
                    default -> System.out.println("Opcion no valida. Intente de nuevo.");
                }
            } while (opcion != 0);
        }
    }
}