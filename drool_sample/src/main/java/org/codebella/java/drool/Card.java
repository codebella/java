package org.codebella.java.drool;

/**
 * Hello world!
 *
 */
public class Card {
	String name;
	int point;

	public Card() {

	}

	public Card(String name, int point) {
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String showFaceValue() {
		return name + " " + point;
	}
}
