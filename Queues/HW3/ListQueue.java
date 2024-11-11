import java.util.Iterator;
import java.util.NoSuchElementException;

//Marc Sulsenti
//I pledge my honor that I have abided by the Stevens Honor System.
public class ListQueue <E> {
    Node<E> front;
    int size;

    public class Node<E> {
       private  E data;
       private  Node<E> next;
       private  int priority;

        public Node(E dataItem) {
            this.data = dataItem;
            this.next = null;
            this.priority =  Integer.MAX_VALUE; //LOW PRIORITY VARIABLE DOESNT WORK
        }

        public Node(E dataItem, int priority) {
            this.data = dataItem;
            this.next = null;
            this.priority = priority;
        }

        public Node(E dataItem, Node<E> nextNode, int priority) {
            this.data = dataItem;
            this.next = nextNode;
            this.priority = priority;
        }

        public E getData() {
            return this.data;
        }

        public int getPriority(){
            return this.priority;

        }

        public Node<E> getNext(){
            return next;
        }


    }

    private class Iter implements Iterator<E> {
        private Node<E> current;

        public Iter(){
            current = front;
        }

        public boolean hasNext() {
            return current.next != null;
        }

        public E next() {
            if (current == null) {
                throw new NoSuchElementException("No next");
            }
            E data = current.data;
            current = current.next;
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public ListQueue(){
        front = null;
        size=0;
    }

    public ListQueue(Node<E> first){
        front=first;
        size=1;
    }

    //Getters and Setters
    public Node<E> getFront() {
        return front;
    }

    public void setFront(Node<E> front) {
        this.front = front;
    }

    public int getSize() {
        return size;
    }



    //Queue methods

    public E peek() {
        //Returns the first item's data in the queue
        if (front == null) {
            return null;
        }
        return front.getData();
    }



    public boolean offer(E item, int priority) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }

        Node<E> newNode = new Node<>(item, priority);

        if (front == null || priority < front.getPriority()) {
            newNode.next =front;
            front = newNode;
            size++;
            return true;
        }

        Node<E> current = front;
        while (current.getNext() != null && priority >= current.getNext().getPriority()) {
            current = current.getNext();
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
        return true;
    }

    public boolean addRear(E item) {
        //adds an item to the very rear of the queue (lowest priority ).
        if (item == null) {
            throw new NullPointerException("Cannot add null item");
        }

        Node<E> newNode = new Node<E>(item, Integer.MAX_VALUE);
        size++;

        if (front == null) {
            front = newNode;
            return true;
        }

        Node<E> current = front;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        return true;
    }

    //poll
    public E poll() {
        //Poll removes and returns the item from the front of the queue
        Node<E> current = front;
        if (front == null) {
            throw new NullPointerException("Cannot remove from empty queue");
        }

        E item = front.getData();
        front = current.next;
        size--;
        return item;
    }

    public boolean remove(Node<E> toBeRemoved) {
        //Removes the specified node from the queue
        Node<E> current = front;
        if (toBeRemoved == null || front == null) {
            //Do nothing if the front of the Queue is empty, or no Node is given
            return false;
        }

        if (front == toBeRemoved) {
            front = current.next;
            size--;
            return true;
        }

        while (current.next != null) {
            if (current.next == toBeRemoved) {
                current.next = toBeRemoved.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public Iterator<E> iterator() {
        return new Iter();
    }





}
