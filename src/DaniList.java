import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Predicate;

public class DaniList<E> implements Iterable<E>{
    private E[] elements;
    private int size = 0;
    public DaniList(){
        elements = (E[]) new Object[size];
    }
    //------------------------------------methods-----------------------------------------------//
    public void add(E obj){
        size++;
        E[] initElements = elements;
        elements = (E[]) new Object[size];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = initElements[i];
        }
        elements[size - 1] = obj;
    }
    public void add(int index , E obj){
        E[] initElementsBack = (E[])new Object[index];
        E[] initElementsNext = (E[])new Object[size - index];
        for (int i = 0; i < index; i++) {
            initElementsBack[i] = elements[i];
        }
        for (int i = index; i < size; i++) {
            initElementsNext[i - index] = elements[i];
        }
        size++;
        elements = (E[])new Object[size];
        for (int i = 0; i < index; i++) {
            elements[i] = initElementsBack[i];
        }
        elements[index] = obj;
        for (int i = index; i < size - 1; i++) {
            elements[i + 1] = initElementsNext[i - index];
        }
    }
    public void remove(E obj){
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(obj)){
                index = i;
            }
        }
        size--;
        E[] initElements = elements;
        elements = (E[])new Object[size];
        for (int i = 0; i < size + 1; i++) {
            if (i < index){
                elements[i] = initElements[i];
            }
            else if (i > index){
                elements[i - 1] = initElements[i];
            }
        }
    }
    public void remove(int index){
        size--;
        E[] initElements = elements;
        elements = (E[])new Object[size];
        for (int i = 0; i < size + 1; i++) {
            if (i < index){
                elements[i] = initElements[i];
            }
            else if (i > index){
                elements[i - 1] = initElements[i];
            }
        }
    }
    public E get(int index){
        return elements[index];
    }
    public int indexOf(E obj){
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(obj)){
                index = i;
            }
        }
        return index;
    }
    public boolean contains(E obj){
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(obj)){
                return true;
            }
        }
        return false;
    }
    public int size(){
        return size;
    }
    public Iterator<E> iterator(){
        return new DaniIterator();
    }
    public ListIterator<E> listIterator() {
        return new DaniListIterator(0);
    }
    public ListIterator<E> listIterator(int index) {
        return new DaniListIterator(index);
    }
    public void sort(Comparator<? super E> comparator){
        for (int i = 0; i < size - 1; i++) {
            if (comparator.compare(elements[i] , elements[i + 1]) >= 0){
                E[] initElements = (E[]) new Object[size];
                for (int j = 0; j < i; j++) {
                    initElements[j] = elements[j];
                }
                initElements[i] = elements[i + 1];
                initElements[i + 1] = elements[i];
                for (int j = i + 2; j < size; j++) {
                    initElements[j] = elements[j];
                }
                elements = initElements;
            }
        }
    }
    public void addAll(DaniList<E> daniList){
        E[] initElements = (E[])new Object[size + daniList.size()];
        for (int i = 0; i < size; i++) {
            initElements[i] = elements[i];
        }
        for (int i = size; i < size + daniList.size(); i++) {
            initElements[i] = daniList.get(i - size);
        }
        size += daniList.size();
        elements = initElements;
    }
    public void removeIf(Predicate<? super E> predicate){
        for (int i = 0; i < size; i++) {
            if (predicate.test(elements[i])){
                remove(i);
            }
        }
    }
    private void setElement(int index , E obj){
        elements[index] = obj;
    }
    //--------------------------------------classes---------------------------------------------//
    private class DaniIterator implements Iterator<E>{
        int init1 = 0;
        int init2 = 0;
        int lastItem = -1;
        @Override
        public boolean hasNext() {
            init1++;
            return init1 <= size;
        }
        @Override
        public E next() {
            init2++;
            if (init2 <= size){
                lastItem = init2 - 1;
                return elements[init2 - 1];
            }
            else {
                return null;
            }
        }
    }
    private class DaniListIterator extends DaniIterator implements ListIterator<E>{
        private E[] elements;
        private int size;
        private int init3;
        private int init4;
        public DaniListIterator(int index){
            this.elements = (E[]) new Object[DaniList.this.size - index];
            for (int i = 0; i < elements.length; i++) {
                elements[i] = DaniList.this.elements[i + index];
            }
            this.size = DaniList.this.size - index;
            init1 = index;
            init2 = index;
            init3 = this.size;
            init4 = this.size;
        }
        @Override
        public boolean hasPrevious() {
            init3--;
            return init3 >= 0;
        }
        @Override
        public E previous() {
            init4--;
            if (init4 >= 0){
                lastItem = init4;
                return elements[init4];
            }
            else {
                return null;
            }
        }
        @Override
        public int nextIndex() {
            return init2;
        }
        @Override
        public int previousIndex() {
            return init4;
        }
        @Override
        public void remove() {
            DaniList.this.remove(lastItem);
        }
        @Override
        public void set(E e) {
            DaniList.this.setElement(lastItem , e);
        }
        @Override
        public void add(E e) {
            DaniList.this.add(lastItem , e);
        }
    }
}
