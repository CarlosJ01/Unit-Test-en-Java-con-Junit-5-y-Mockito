package com.alfacentauri.mokito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alfacentauri.mockito.ValidNumber;

public class ValidNumberTest {
	
	private ValidNumber validNumber;
	
	@BeforeEach
	public void setUp() {
		validNumber = new ValidNumber();
	}
	
	@AfterEach
	public void tearDown() {
		validNumber = null;
	}
	
	@Test
	public void checkTest() {
		assertEquals(true, validNumber.check(5));
	}
	
	@Test
	public void checkNegativeTest() {
		assertEquals(false, validNumber.check(-5));
	}
	
	@Test
	public void checkStringTest() {
		assertEquals(false, validNumber.check("1"));
	}
	
	@Test
	public void checZeroTest() {
		assertEquals(true, validNumber.checkZero(-5));
	}
	@Test
	public void checZeroStringTest() {
		assertEquals(false, validNumber.checkZero("5"));
	}
	@Test
	public void checZero0Test() {
		assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
	}
	
	public void doubleInitTest() {
		assertEquals(9, validNumber.doubleToInt(9.999));
	}
	public void doubleInitErrorTest() {
		assertEquals(0, validNumber.doubleToInt("9.99"));
	}
}
