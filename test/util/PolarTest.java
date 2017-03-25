package util;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team4308.util.Polar;

public class PolarTest {

	private static final int randomAngleRange = 360;
	private static final int randomRadiusRange = 10;

	private Polar polar;
	Random random;

	@Before
	public void setUp() throws Exception {
		random = new Random();
		polar = new Polar(random.nextDouble() * randomAngleRange, random.nextDouble() * randomRadiusRange);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
