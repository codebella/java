package org.codebella.java.sample.testing.junit.category;

import java.util.stream.Stream;

import org.codebella.java.sample.testing.junit.category.type.SlowCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(SlowCategory.class)
public class SlowRunningJUnitTest {
	@Test
	public void test1() {
		Runnable myTask = () -> {
			System.out.println("SlowRunningTest :: Test #1 is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		};
		Stream.of(1, 2, 3, 4, 5).forEach(e -> {
			new Thread(myTask).start();
		});
	}

	@Test
	public void test2() {
		Runnable myTask = () -> {
			System.out.println("SlowRunningTest :: Test #2 is running");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		};
		Stream.of(1, 2, 3, 4, 5).forEach(e -> {
			new Thread(myTask).start();
		});
	}
	
}
