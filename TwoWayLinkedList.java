import java.util.*;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    /** Create an empty list */
    public TwoWayLinkedList() {
    }

    /** Create a list from an array of objects */
    public TwoWayLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // Link the new node with the head
        if (head != null) {
            head.previous = newNode; // The previous head node's previous pointer should point to the new node
        }
        head = newNode; // Make the new node the head
        if (tail == null) { // If the list was empty, the new node is also the tail
            tail = head;
        }
        size++;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        if (tail != null) {
            tail.next = newNode; // Link the new node with the tail
            newNode.previous = tail; // Link back from the new node to the tail
        }
        tail = newNode; // Make the new node the tail
        if (head == null) { // If the list was empty, the new node is also the head
            head = tail;
        }
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(e);
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;
            size++;
        }
    }

    /** Remove the head node and return the object that is contained in the removed node */
    public E removeFirst() {
        if (size == 0) return null;
        E temp = head.element;
        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null; // List becomes empty
        }
        size--;
        return temp;
    }

    /** Remove the last node and return the object that is contained in the removed node */
    public E removeLast() {
        if (size == 0) return null;
        E temp = tail.element;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null; // List becomes empty
        }
        size--;
        return temp;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        return current.element;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e) != -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) return null;
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int indexOf(Object e) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) return null;
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldElement = current.element;
        current.element = e;
        return oldElement;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public ListIterator<E> listIterator() {
        return new LinkedListIterator(0);
    }

    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index);
    }

    private class LinkedListIterator implements ListIterator<E> {
        private Node<E> current;
        private int currentIndex;

        public LinkedListIterator() {
            this(0);
        }

        public LinkedListIterator(int index) {
            current = head;
            for (int i = 0; i < index && current != null; i++) {
                current = current.next;
            }
            currentIndex = index;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E element = current.element;
            current = current.next;
            currentIndex++;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return current != null && current.previous != null;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.previous;
            currentIndex--;
            return current.element;
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("set not supported");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("add not supported");
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }
}
