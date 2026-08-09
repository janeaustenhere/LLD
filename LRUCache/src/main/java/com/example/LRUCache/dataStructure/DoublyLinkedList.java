package com.example.LRUCache.dataStructure;

import com.example.LRUCache.model.Node;

public class DoublyLinkedList <K,V>{

    private final Node<K,V> head;
    private final Node<K,V> tail;

    public DoublyLinkedList(){

        head = new Node<>(null,null);
        tail = new Node<>(null,null);
        head.next = tail;
        tail.prev = head;

    }

    public void addToHead(Node<K,V> node){
        node.next = head.next;
        node.prev = head.next.prev;
        head.next.prev = node;
        head.next = node;

    }

    public void removeNode(Node<K,V> node){

        node.prev.next = node.next;
        node.next.prev = node.prev;

    }

    public void removeNodeFromTail(){
        if(tail.prev == head){
            return;
        }
        Node<K,V> nodeToBeRemoved = tail.prev;
        nodeToBeRemoved.prev.next = tail;
        tail.prev = nodeToBeRemoved.prev;
    }

    public void moveNodeToHead(Node<K,V> node){

        removeNode(node);
        addToHead(node);
    }

    public Node<K,V> getHead(){
        return this.head.next;
    }

    public Node<K,V> getTail()
    {
        return this.tail.prev;
    }

}
