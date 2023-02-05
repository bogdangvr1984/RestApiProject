package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CargoCapacityChecker extends TypeSafeMatcher<Integer> {

	@Override
	public void describeTo(Description description) {
		description.appendText("Ship doesn't have cargo capacity: ");		
	}

	@Override
	protected boolean matchesSafely(Integer capacity) {
		return (capacity > 25000000);
	}
	
	public static Matcher<Integer> cargoCapacity() {
		
		return new CargoCapacityChecker();
	}

	

}
