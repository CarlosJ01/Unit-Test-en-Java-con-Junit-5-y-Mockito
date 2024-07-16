package com.alfacentauri.mockito;

public class ValidNumber {
	
	public boolean check(Object o) {
		if(o instanceof Integer) {
			Integer num = (Integer)o;
			if(num >= 0 && num < 10)
				return true;
		}
		return false;
	}
	
	
	public boolean checkZero(Object o) {
		if(o instanceof Integer) {
			Integer num = (Integer)o;
			if(num  == 0)
				throw new ArithmeticException("No acepta cero como parametro");
			else
				return true;
		}
		return false;
	}
	
	public int doubleToInt(Object o) {
		if(o instanceof Double) {
			return ((Double)o).intValue();
		}
		return 0;
	}
}
