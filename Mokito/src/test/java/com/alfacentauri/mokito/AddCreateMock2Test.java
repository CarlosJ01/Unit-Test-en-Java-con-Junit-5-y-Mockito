package com.alfacentauri.mokito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alfacentauri.mockito.Add;
import com.alfacentauri.mockito.ValidNumber;

public class AddCreateMock2Test {
	
	@Mock // Va a ser un objeto Mock
	private ValidNumber validNumber;
	@InjectMocks // Injecta a los objetos marcados con @Mock
	private Add add;
	
	@BeforeEach
	public void setUp() {
		// Inicializa los objetos Mock como los que dependen
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTest() {
		add.add(3, 2);
		Mockito.verify(validNumber).check(3);
	}
	
}
