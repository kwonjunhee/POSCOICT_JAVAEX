package com.javaex.basic;

public class OperatorEx {

	public static void main(String[] args) {
//		arithOperEx();
//		prefixIncrEx();
//		suffixIncrEx();
//		logicalOperEx();
//		bitOperEx();
		bitShiftOperEx();
	}
	//	비트시프트
	private static void bitShiftOperEx() {
		int val = 1;
		
		System.out.println(Integer.toBinaryString(val));
		System.out.println(Integer.toBinaryString(val << 3));
		
		val = 0b1000;
		System.out.println(Integer.toBinaryString(val));
		System.out.println(Integer.toBinaryString(val >> 2));	//	2비트 우측 시프트
	}
	//	비트연산자
	private static void bitOperEx() {
		//	int에서만
		//	비트 단위의 미세한 제어에 이용
		int b1 = 0b11011101;
		int mask = 0b10101010;
		
		System.out.println(Integer.toBinaryString(b1));
		System.out.println(Integer.toBinaryString(mask));
		
		System.out.println(Integer.toBinaryString(b1 & mask));	//	비트 논리곱
		System.out.println(Integer.toBinaryString(b1 | mask));	//	비트 논리합 
		System.out.println(Integer.toBinaryString(~b1));	//	비트 논리부정
		
	}
	
	//	비교, 논리연산
	private static void logicalOperEx() {
		//	비교연산자: >, >=, <, <=, ==(equal), !=(not equal)
		//	논리연산자: 논리곱(AND, &&), 논리합(OR, ||), 논리부정(NOT, !)
		int a = 5;
		//	a가 0초과, 10미만의 값?
		//	조건1: a > 0
		//	조건2: a < 10
		//	결과 : 조건1 AND 조건2
		boolean b1 = a > 0;	//	true
		boolean b2 = a < 10;	//	true
		
		boolean r = b1 && b2;	//	논리곱
		System.out.println("b1 && b2 == " + r);
		
		//	a가 10이하 이거나 10이상의 값?
		//	조건1: a <= 0
		//	조건2: a >= 10
		b1 = a <= 0;
		b2 = a >= 10;
		
		r = b1 || b2;
		System.out.println("b1 || b2 == " + r);
		
		//	논리 부정(NOT !)
		System.out.println("!r == " + !r);
		
	}
	
	//	후위증감
	private static void suffixIncrEx() {
		int a = 7;
		int b = 0;
		
		b = a++;
		System.out.println("b:" + b);
		System.out.println("a:" + a);
	}
	
	//	전위증감
	private static void prefixIncrEx() {
		int a = 7;
		int b = 0;
		
		b = ++a;
		System.out.println("b:" + b);
		System.out.println("a:" + a);
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
