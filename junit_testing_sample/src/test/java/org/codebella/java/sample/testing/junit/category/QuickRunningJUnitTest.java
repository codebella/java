package org.codebella.java.sample.testing.junit.category;

import java.util.stream.Stream;

import org.codebella.java.sample.testing.junit.category.type.QuickCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(QuickCategory.class)
public class QuickRunningJUnitTest {
	@Test
	public void test1() {
		Runnable myTask = () -> {
			System.out.println("QickRunningTest :: Test #1 is running");
			try {
				Thread.sleep(200);
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
			System.out.println("QickRunningTest :: Test #2 is running");
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
		};
		Stream.of(1, 2, 3, 4, 5).forEach(e -> {
			new Thread(myTask).start();
		});
	}
}
