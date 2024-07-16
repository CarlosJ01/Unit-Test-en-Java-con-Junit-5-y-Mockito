package com.alfacentauri.mockito;

public class WebServices {
	
	public boolean checkLogin(String user, String password) {
		if (user.equals("user") && password.equals("123")) {
			return true;
		}
		return false;
	}
	
	public void login(String user, String password, Callback callback) {
		if (checkLogin(user, password)) {
			callback.onSuccess("Usuario Correcto");
		} else {
			callback.onFail("Error");
		}
	}
	
}
