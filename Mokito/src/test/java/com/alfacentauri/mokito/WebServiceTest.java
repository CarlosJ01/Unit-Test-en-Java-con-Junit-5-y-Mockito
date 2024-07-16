package com.alfacentauri.mokito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alfacentauri.mockito.Callback;
import com.alfacentauri.mockito.WebServices;

public class WebServiceTest {
	
	private WebServices webServices;
	
	@Mock
	Callback callback;
	
	@BeforeEach
	public void setUp() {
		webServices = new WebServices();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void checkLoginTest() {
		assertTrue(webServices.checkLogin("user", "123"));
	}
	
	@Test
	public void checkLoginError() {
		assertFalse(webServices.checkLogin("user", "4123"));
	}
	
	@Test
	public void loginTest() {
		webServices.login("user", "123", callback);
		verify(callback).onSuccess("Usuario Correcto");
	}
	
	@Test
	public void loginErrorTest() {
		webServices.login("user", "15654", callback);
		verify(callback).onFail("Error");
	}
}
