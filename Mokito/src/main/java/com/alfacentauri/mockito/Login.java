package com.alfacentauri.mockito;

public class Login {
	
	public WebServices webServices;
	public boolean isLogin;
	
	public Login(WebServices webServices) {
		this.webServices = webServices;
		this.isLogin = false;
	}
	
	
	public void doLogin() {
		webServices.login("user", "123", new Callback() {
			
			@Override
			public void onSuccess(String response) {
				System.out.println(response);
				isLogin = true;
			}
			
			@Override
			public void onFail(String response) {
				System.out.println(response);
				isLogin = false;
			}
		});
	}
	
	
}
