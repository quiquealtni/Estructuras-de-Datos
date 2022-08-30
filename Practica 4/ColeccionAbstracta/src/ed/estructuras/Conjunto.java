/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */
package ed.estructuras;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Implementación burda e ineficiente de una estructura tipo conjunto,
 * utilizada sólo con fines de prueba para otra clase.
 * @author blackzafiro
 */
public class Conjunto<E> extends ColeccionAbstracta<E> {
    
    private Object[] buffer = new Object[10];
    private int tam = 0;
    
    /**
     * Iterador que permite visitar todos los elementos del conjunto.
     */
    public class Iterador implements Iterator<E> {
        // Índice del elemento siguiente a visitar.
        private int i = 0;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return i < tam;
        }

        @Override
        public E next() {
            if(!hasNext()) throw new IllegalStateException("No hay elemento siguiente");
            E temp = (E)buffer[i];
            i++;
            canRemove = true;
            return temp;
        }
        
        /**
         * Elimina el elemento del conjunto que acaba de ser devuelto por
         * <code>next()</code>.
         * @throws IllegalStateException si <code>next()</code> no ha sido
         *         llamado.
         */
        @Override
        public void remove() {
            if(!canRemove) throw new IllegalStateException("next() no ha sido llamado y/o el conjunto está vacío.");
			// borro al que estaba en el valor anterior de i
			int shift = i - 1;
			while(shift < tam - 1) {
				// Pasar el elemento del final a esta posición
				buffer[shift] = buffer[shift + 1];
				shift++;
			}
            // Remover del final
            tam--;
			buffer[tam] = null;
            i--;
            
            canRemove = false;
        }
        
    }

    /** 
     * Devuelve el número de elementos en el conjunto.
     * @return número de elementos
     */
    @Override
    public int size() {
        return tam;
    }

    /**
     * Indica si el conjunto está vacío.
     * @return ¿vacío?
     */
    @Override
    public boolean isEmpty() {
        return tam == 0;
    }

    /**
     * Objeto que recorre el conjunto elemento por elemento
     * @return un iterador
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterador();
    }

    /**
     * Agrega el elemento <code>e</code> únicamente si no se encuentra uno igual,
     * de acuerdo a la definicón de método <code>equals</code> de <code>E</code>.
     * @param e Elemento que se quiere agregar.
     * @return Si el elemento fue agregado devuelve <code>true</code>,
     *         si ya estaba devuelve <code>false</code>.
     */
    @Override
    public boolean add(E e) {
        if(e == null) throw new NullPointerException("El conjunto no admite valores nulos.");
        for(int i = 0; i < tam; i++){
            if(buffer[i].equals(e)) return false; // No agrega
        }
        if(buffer.length == tam) {
            buffer = Arrays.copyOf(buffer, buffer.length * 2);
        }
        buffer[tam] = e;
        tam++;
        return true;
    }
    
    public static void main(String[] args){
        Conjunto<String> c = new Conjunto<String>();
        for(int i = 0; i<5;i++){
            c.add(""+i);
        }
        System.out.println(c.toString());
        Object[] x = c.toArray();
        System.out.println(x[4]);
        
    }
	
}
