package utils;

public class Triplet <T,U,V>{

	T first;
	U second;
	V third;
	
	public Triplet() {
	}
	
	
	public Triplet(T first, U second,V third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public T getFirst() {
		return first;
	}

	public V getThird() {
		return third;
	}


	public void setThird(V third) {
		this.third = third;
	}


	public void setFirst(T first) {
		this.first = first;
	}
	public U getSecond() {
		return second;
	}
	public void setSecond(U second) {
		this.second = second;
	}
	
	
	
}


