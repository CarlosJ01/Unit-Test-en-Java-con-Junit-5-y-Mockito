package com.alfacentauri.mokito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.alfacentauri.mockito.Add;
import com.alfacentauri.mockito.ValidNumber;

public class AddCreateMock1Test {
	
	private Add add;
	private ValidNumber validNumber;
	
	@BeforeEach
	public void setUp() {
		// Mockiar las dependencias
		validNumber = Mockito.mock(ValidNumber.class); // Crear objeto Mock
		add = new Add(validNumber);
	}
	
	@AfterEach
	public void tearDown() {
		validNumber = null;
		add = null;
	}
	
	@Test
	public void addTest() {
		// Probar
		add.add(3, 2);
		// Valiar
		Mockito.verify(validNumber).check(3); // Se verifica que sucedio el comportamiento (Se llamo check con 3)
		// Por defecto un mock si no sobrescribes el metodo devuelve datos por default
	}
	
}
