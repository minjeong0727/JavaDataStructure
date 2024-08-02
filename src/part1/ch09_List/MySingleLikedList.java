package part1.ch09_List;

import org.w3c.dom.Node;

public class MySingleLikedList<E>{
    private int size = 0;
    private Node<E> firstNode = null;
    private Node<E> lastNode = null;

    public static class Node<E>{
        E item;
        Node<E> next;
        Node(E element, Node <E> next){
            this.item  = element;
            this.next = next;
        }
    }

    //add-list의 앞뒤 추가 방향 선택가능
    public void add (E element){
        Node <E> newNode = new Node<>(element, null);
        if(size == 0)firstNode = newNode;
        else lastNode.next = newNode;
        lastNode = newNode;
        size++;

    }

    public void addFirst(E element){
        Node<E> newNode = new Node<>(element, firstNode);
        if(size == 0) lastNode = newNode;
        firstNode = newNode;
        size++;
    }
    //get,set - 접근과 변경
    public Node<E>getNode(int idx){
        if(idx<0||idx>size)
            throw new IndexOutOfBoundsException("Index:" + idx + "Size" + size);
        Node<E>currentNode = firstNode;
        for(int i = 0; i < idx; i++){
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public E get(int idx){
        return getNode(idx).item;
    }

    public void set(int idx, E element){
        Node<E> targetNode = getNode(idx);
        targetNode.item = element;
    }


    //insert-삽입
    public void insert(Node<E>prevNode, E element){
        Node<E>newNode = new Node<>(element, prevNode.next);
        prevNode.next = newNode;
        if(newNode.next == null)lastNode = newNode;
        size++;
    }
    public void insert(int idx, E element){
        if(idx < 0 || idx > size){
            throw new IndexOutOfBoundsException("Index:" + idx +"Size" + size);

        }
        if(idx ==0)addFirst(element);
        else if(idx == size)add((element));
        else insert(getNode(idx-1), element);
    }

    //remove-삭제
    public void remove(int idx){
        if(idx<0 || idx>= size)throw new IndexOutOfBoundsException("Index" + idx + "Size" + size);
        if(idx ==0)removeFirst();
        else removeNext(getNode(idx-1));


    }
    public void removeFirst(){
        if(firstNode != null){
            firstNode = firstNode.next;
            if(firstNode == null)
                lastNode = null;
            size--;
        }
    }
    public void removeNext(Node<E>prevNode){
        if(prevNode.next != null){
            prevNode.next = prevNode.next.next;
            if(prevNode.next == null){
                lastNode = prevNode;
            }
            size--;
        }
    }
}
