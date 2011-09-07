package gui;

public class ListaCircular {
	private Nodo _primero;
	private Nodo _ultimo;
	private int _lon;

	public ListaCircular() {
		_primero = null;
		_ultimo = null;
		_lon = 0;
	}

	public boolean isEmpty() {
		return (_primero == null);
	}

	public int lenght() {
		return _lon;
	}

	public void addPrimero(Object element) {
		_primero = new Nodo(element, null, null);
		_primero.setAnterior(_primero);
		_primero.setSiguiente(_primero);
		_ultimo = _primero;
		_lon++;
	}

	public void add(Object element) {
		if (isEmpty()) {
			addPrimero(element);
		} else {
			Nodo nuevo = new Nodo(element, _primero, _ultimo);
			_ultimo.setSiguiente(nuevo);
			_primero.setAnterior(nuevo);
			_ultimo = nuevo;
			_lon++;
		}
	}

	public Object borrarPrimero() {
		if (isEmpty()) {
			return null;
		} else {
			Object temp = _primero.getElement();
			_primero = _primero.getSiguiente();
			_lon--;
			return temp;
		}
	}

	public Object borrar() {
		if (isEmpty()) {
			return null;
		} else {
			if (_primero.getSiguiente() == _primero) {
				return borrarPrimero();
			} else {
				Object temp = _ultimo.getElement();
				_ultimo = _ultimo.getAnterior();
				_ultimo.setSiguiente(_primero);
				_lon--;
				return temp;
			}
		}
	}

	public Nodo getElement() {
		return _primero;
	}

	public Nodo getElement(int n) {
		Nodo temp = _primero;
		if (n > lenght()) {
			return null;
		} else {
			for (int i = 1; i < n; i++) {
				temp = temp.getSiguiente();
			}
			return temp;
		}
	}
	
	public class Nodo {
		private Object _element;
		private Nodo _siguiente;
		private Nodo _anterior;

		public Nodo(Object element, Nodo siguiente, Nodo anterior) {
			_element = element;
			_siguiente = siguiente;
			_anterior = anterior;
		}

		public Nodo getAnterior() {
			return _anterior;
		}

		public Nodo getSiguiente() {
			return _siguiente;
		}

		public void setAnterior(Nodo anterior) {
			_anterior = anterior;
		}

		public void setSiguiente(Nodo siguiente) {
			_siguiente = siguiente;
		}

		public Object getElement() {
			return _element;
		}

		public void setElement(Object element) {
			_element = element;
		}
	}
}