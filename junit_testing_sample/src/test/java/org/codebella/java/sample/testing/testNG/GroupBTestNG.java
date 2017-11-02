package org.codebella.java.sample.testing.testNG;

import org.testng.annotations.Test;

public class GroupBTestNG {

	@Test
	public void test1() {
		int count = 0;
		while ((count+=1) < 5) {
			System.out.println("TestNG Quick running test in from group B");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
	@Test
	public void test2() {
		int count = 0;
		while ((count+=1) < 5) {
			System.out.println("TestNG Quick running test in from group 2.B");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}
}
