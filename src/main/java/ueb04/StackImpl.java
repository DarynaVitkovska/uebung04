package ueb04;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

class StackImpl<T> implements Stack<T> {
	private Element top;
	private int s = 0;

	/**
	 * Gibt einen Reverse-Iterator zurück, d.h. ein Iterator, welcher die Elemente
	 * in umgekehrter Reihenfolge zurück gibt. Also als erstes das letzte (also "unterste"),
	 * Element, dann das zweit-unterste etc.
	 */
	@Override
	public Iterator<T> iterator() {
		// Iterator implementieren...
		return new Iterator<T>() {
			List<Element> agenda = new LinkedList<>();
			{
				if(top != null){ // wenn es ein Element gibt, füge ihn in die Liste hinzu
					agenda.add(top);
				}

				Element it = top.next;
				while (it!= null){// wenn dieses Element nicht leer ist
					agenda.add(0, it); //  füge dies an der Stelle 0 hinzu
					it  = it.next;
				}

			}
			@Override
			public boolean hasNext() {
				return agenda.size()>0; // wenn die Liste nicht leer ist, gib true zurück
			}

			@Override
			public T next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				Element e = agenda.remove(0);
				return e.value;
			}

			};
		}

	@Override
	public void push(T c) {
		top = new Element(c, top);
		s++;
	}

	@Override
	public T pop() {
		if (top == null)
			throw new NoSuchElementException();
		T v = top.value;
		top = top.next;
		s--;
		return v;
	}

	@Override
	public int size() {
		return s;
	}

	private class Element {
		T value;
		Element next;
		Element(T value, Element next) {
			this.value = value;
			this.next = next;
		}
	}
}
