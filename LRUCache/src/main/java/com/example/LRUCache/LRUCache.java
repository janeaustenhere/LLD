package com.example.LRUCache;

import com.example.LRUCache.dataStructure.DoublyLinkedList;
import com.example.LRUCache.model.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class LRUCache <K,V>{

    private final int capacity;
    private final DoublyLinkedList<K,V> doublyLinkedList;
    private Map<K, Node<K,V>> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.map = new HashMap<>();
    }

    public synchronized V get(K key){

        if(map.containsKey(key)){
            doublyLinkedList.moveNodeToHead(map.get(key));
            return map.get(key).value;

        }else{
            return null;
        }

    }

    public synchronized void put(K key, V value){

        if(map.containsKey(key)){

            Node<K,V> node =  map.get(key);
            node.value = value;
            doublyLinkedList.moveNodeToHead(node);

        }else{
            if(map.size() >= capacity){
               Node<K,V> lastNode = doublyLinkedList.getTail();
               doublyLinkedList.removeNodeFromTail();
               map.remove(lastNode.key);
                Node<K, V> node = new Node<>(key, value);
                doublyLinkedList.addToHead(node);
                map.put(key, node);

            }else {
                Node<K, V> node = new Node<>(key, value);
                doublyLinkedList.addToHead(node);
                map.put(key, node);
            }
        }

    }

    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner(",","{","}");
        Node<K,V> current = doublyLinkedList.getHead();
        while (current.next != null){
           joiner.add(current.key + " = " + current.value);
           current = current.next;
        }

        return joiner.toString();


    }
}
