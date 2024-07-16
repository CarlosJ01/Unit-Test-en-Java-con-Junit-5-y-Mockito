package com.alfacentauri.mockito;

public interface Callback {
	void onSuccess(String response);
	void onFail(String response);
}
