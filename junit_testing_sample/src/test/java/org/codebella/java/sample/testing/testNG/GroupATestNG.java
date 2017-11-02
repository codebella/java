package org.codebella.java.sample.testing.testNG;

import org.testng.annotations.Test;

public class GroupATestNG {

	@Test
	public void test1() {
		int count = 0;
		while ((count+=1) < 5) {
			System.out.println("TestNG Slow running test in from group A");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	@Test
	public void test2() {
		int count = 0;
		while ((count+=1) < 5) {
			System.out.println("TestNG Slow running test in group 2.A");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
	}

}
