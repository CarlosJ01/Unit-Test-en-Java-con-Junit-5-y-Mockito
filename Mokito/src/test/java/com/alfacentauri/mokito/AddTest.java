package com.alfacentauri.mokito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.alfacentauri.mockito.Add;
import com.alfacentauri.mockito.Print;
import com.alfacentauri.mockito.ValidNumber;

public class AddTest {
	
	@Mock
	private ValidNumber validNumber;
	@Mock
	private Print print;
	
	@InjectMocks
	private Add add;
	
	@Captor //  
	private ArgumentCaptor<Integer> captor;
	
	@Spy // Crea un espia convinacion entre mock y uno real que se puede comprobar sus iteracciones
	List<String> spyList = new ArrayList<>();
	
	@Mock
	List<String> mockList = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		System.out.println("Iniciando Mocks");
	}
	
	@Test
	public void addTest() {
		// Sobre escribir el valor de retorno de un metodo en una situacion :)
		when(validNumber.check(3)).thenReturn(true);
		boolean check = validNumber.check(3);
		assertEquals(true, check);
		
		when(validNumber.check("a")).thenReturn(true);
		check = validNumber.check("a");
		assertEquals(true, check);
	}
	
	@Test
	public void addMockException() {
		// Retornar pero Excepciones
		when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No acepta cero como parametro"));
		
		Exception exception = null;
		try {
			validNumber.checkZero(0);
		} catch (ArithmeticException e) {
			exception = e;
		}
		
		assertNotNull(exception);
	}
	
	@Test
	public void addRealMethod() {
		// Llamar la verdadera funcion
		when(validNumber.check(3)).thenCallRealMethod();
		assertEquals(true, validNumber.check(3));
		
//		when(validNumber.check(3)).thenCallRealMethod();
//		assertEquals(false, validNumber.check(3));
	}
	
	@Test
	public void addDoubleToIntThenAnswer() {
		// Sobreescribir pero poniendo codigo 
		Answer<Integer> answer = new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				// Codigo
				return 7;
			}
		};
		when(validNumber.doubleToInt(7.8)).thenAnswer(answer);
		
		assertEquals(14, add.addInt(7.8));
	}
	
	/*
	 * Patron de pruebas 
	 * 	Arrange => Given
	 * 	Act => When
	 * 	Assert => then
	 */
	
	@Test
	public void patterTest() {
		// Arrange
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);
		// Act
		int result = add.add(4, 5);
		// Assert
		assertEquals(9, result);
	}
	
	@Test
	public void patterBDDTest() {
		// Given
		given(validNumber.check(4)).willReturn(true); // org.mockito.BDDMockito.given
		given(validNumber.check(5)).willReturn(true);
		// When
		int result = add.add(4, 5);
		// Then
		assertEquals(9, result);
	}
	
	@Test
	public void argumentMatcherTest() {
		// any() => Cualquier argumento, anyInt => Cualquier Entero
		given(validNumber.check(anyInt())).willReturn(true);
		assertEquals(9, add.add(4, 5));
	}
	
	@Test
	public void addPrintTest() {
		given(validNumber.check(4)).willReturn(true);
		given(validNumber.check(5)).willReturn(true);
		add.addPrint(4, 5);
		
		// Verifica que un metodo se llame solo una vez
		verify(validNumber).check(4);
		verify(validNumber).check(5);
		// Verifica que se llame 2 veces el metodo check con valor 4
//		verify(validNumber, times(2)).check(4);
		
		verify(validNumber, never()).check(99); // Que nunca se llamo
		verify(validNumber, atLeast(1)).check(4); // Que se llame por lo menos
		verify(validNumber, atMost(1)).check(5); // Que se llame a lo maximo
		
		verify(print).showMessage(9);
		verify(print, never()).showError();
	}
	
	
	@Test
	public void captorTest() {
		given(validNumber.check(4)).willReturn(true);
		given(validNumber.check(5)).willReturn(true);
		add.addPrint(4, 5);
		
		// Capturar el argumento de entrada
		verify(print).showMessage(captor.capture());
		assertEquals(captor.getValue().intValue(), 9);	
	}
	
	@Test
	public void spyTest() {
		spyList.add("1");
		
		verify(spyList).add("1");
		
		assertEquals(1, spyList.size());
	}
	
	@Test
	public void mockListTest() {
		mockList.add("1");
		mockList.add("2");
		
		verify(mockList).add("1");
		verify(mockList).add("2");
		
		given(mockList.size()).willReturn(2);
		assertEquals(2, mockList.size());
	}
	
	
	
}
