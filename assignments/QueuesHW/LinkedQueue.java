package QueuesHW;

import java.util.*;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
	
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

	@Override
	public E first() {
		if (isEmpty()) return null;
		return list.first();
	}

	@Override
	public E dequeue() {
		if (isEmpty()) return null;
		return list.removeFirst();
	}

}


