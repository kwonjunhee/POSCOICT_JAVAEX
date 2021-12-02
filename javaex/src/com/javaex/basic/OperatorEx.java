package com.javaex.basic;

public class OperatorEx {

	public static void main(String[] args) {
		arithOperEx();
	}
	
	//	산술연산자
	private static void arithOperEx() {
		int a = 7;
		int b = 3;
		
		//	부호 연산자(+, -)
		System.out.println(-a); //	-> -1 * a
		
		//	산술연산(+, -, *, /, %)
		System.out.println(a / b);	//	int / int -> int(나눗셈의 몫)
		System.out.println(a % b);	//	정수 나눗셈의 나머지
		
//		System.out.println(a / 0);	//	int / 0
		System.out.println(7.0 / 0);	//	Infinity
		System.out.println(7.0 / 0 + 100);	//	Infinity가 포함된 산술식 -> Infinity
		
		//	Infinity 체크
		System.out.println(Double.isInfinite(7.0 / 0));	//	Infinity 체크
		//	데이터가 NaN인지 확인
		System.out.println(0.0 / 0.0);
		System.out.println(Double.isNaN(0.0 / 0.0));
		System.out.println(Double.isNaN(7.0 / 0.0));
	}

}
