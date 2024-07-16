package com.alfacentauri.mokito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.alfacentauri.mockito.Callback;
import com.alfacentauri.mockito.Login;
import com.alfacentauri.mockito.WebServices;

public class LoginTest {
	
	@InjectMocks
	private Login login;
	
	 @Mock
	 private WebServices webServices;
	 
	 @Captor
	 private ArgumentCaptor<Callback> captorCallback;
	 
	 @BeforeEach
	 public void setUp() {
		MockitoAnnotations.initMocks(this);
	 }
	 
	 @Test
	 public void doLogin() {
		// Crear una respuesta
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				// Traer los argumentos del metodo
				String user = (String) invocation.getArgument(0);
				assertEquals("user", user);
				String password = (String) invocation.getArgument(1);
				assertEquals("123", password);
				Callback callback = (Callback) invocation.getArgument(2);
				
				callback.onSuccess("OK");				
				
				return null;
			}
		}).when(webServices).login(anyString(), anyString(), any(Callback.class));
		
		login.doLogin();
		
		verify(webServices, times(1)).login(anyString(), anyString(), any(Callback.class));
		assertTrue(login.isLogin);
	}
	
	 @Test
	 public void doLoginfAIL() {
		// Crear una respuesta
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				// Traer los argumentos del metodo
				String user = (String) invocation.getArgument(0);
				assertEquals("user", user);
				String password = (String) invocation.getArgument(1);
				assertEquals("123", password);
				Callback callback = (Callback) invocation.getArgument(2);
				
				callback.onFail("Error");				
				
				return null;
			}
		}).when(webServices).login(anyString(), anyString(), any(Callback.class));
		
		login.doLogin();
		
		verify(webServices, times(1)).login(anyString(), anyString(), any(Callback.class));
		assertFalse(login.isLogin);
	}
	 
	 @Test
	 public void doLoginCaptorTest() {
		login.doLogin();
		verify(webServices).login(anyString(), anyString(), captorCallback.capture());
		assertFalse(login.isLogin);
		
		Callback callback = captorCallback.getValue();
		
		callback.onSuccess("OK");
		assertTrue(login.isLogin);
		
		callback.onFail("ERROR");
		assertFalse(login.isLogin);
	}
}
