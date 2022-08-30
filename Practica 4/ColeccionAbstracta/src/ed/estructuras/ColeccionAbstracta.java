package ed.estructuras;

import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;

public abstract class ColeccionAbstracta<E> implements Collection<E>{
    
    @Override
    public boolean contains(Object o){
        
        Iterator<E> it = iterator();
        
        if(o==null){
            throw new NullPointerException();
        }
        
        while(it.hasNext()){
            if(o.equals(it.next())){
                return true;
            }
        }
        
        return false;
    }    
    
    @Override
    public int size(){
        Iterator<E> it = iterator();
        int tam = 0;
        while(it.hasNext()){
            tam++;
        }
        return tam;
    }
    
    @Override
    public Object[] toArray(){
        Iterator<E> it = iterator();
        Object[] arreglo = new Object[size()];
        for(int i = 0;i < arreglo.length;i++){
            arreglo[i] = it.next();
        }
        
        return arreglo;
    }
    
    @Override
    public <T>  T[] toArray(T[] a){
        
        if(a == null){
            throw new NullPointerException();
        }

        if(a.length < size()){
            a = Arrays.copyOf(a,size());
        }
        
        if(a.length > size()){
            a[size()] = null;
        }
        
        Iterator<E> it = iterator();
        int i=0;
        while(it.hasNext()){
            a[i++] = (T)it.next();
        }
        
        return a;
    }
    
    @Override
    public boolean containsAll(Collection<?> c){
        
        Iterator<?> it = iterator();
        
        if(c == null){
            throw new NullPointerException();
        }
        
        while(it.hasNext()){
            if(contains(it.next()) == false){
                return false;
            }
        }
                
        return true;
    }
    
    @Override
    public boolean add(E e){
        Iterator<E> it = iterator();
        while(it.hasNext()){
            if(it.next().equals(e)){
                return true;
            }
        }
        return false;
    }
        
        
    
    @Override
    public boolean addAll(Collection<? extends E> c){
        
        Iterator<? extends E> it = c.iterator();
        
        if(c == null){
            throw new NullPointerException();
        }
        
        while(it.hasNext()){
            if(add(it.next()) == false){
                return false;
            }
        }
       
        return true;
    }
    
    @Override
    public boolean remove(Object o){
        
        Iterator<E> it = iterator();
        
        if(o==null){
            throw new NullPointerException();
        }
        
        while(it.hasNext()){
            if(o.equals(it.next())){
                it.remove();
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean removeAll(Collection<?> c){
    
        Iterator<?> it = iterator();
        boolean cambio = false;
        if(c == null){
            throw new NullPointerException();
        }
        
        while(it.hasNext()){
            if(c.contains(it.next())){
                it.remove();
                cambio = true;
            }
        }
        return cambio;
    }
    
    @Override
    public boolean retainAll(Collection<?> c){
        
        Iterator<?> it = iterator(); 
        boolean cambio = false;
        
        while(it.hasNext()){
            if(c.contains(it.next())==false){
                it.remove();
                cambio = true;
            }
        }
        return cambio;
    }
    
    @Override
    public void clear(){
        Iterator<E> it = iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
    
    @Override
    public String toString(){
        Iterator<E> it = iterator();
        String s = "";
        while(it.hasNext()){
            s = s + it.next().toString() + " ";
        }
        return s;
    }
}