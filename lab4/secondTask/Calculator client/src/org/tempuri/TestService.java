package org.tempuri;

import java.rmi.RemoteException;
import java.util.Scanner;

import org.tempuri.CalculatorStub.Add;
import org.tempuri.CalculatorStub.AddResponse;
import org.tempuri.CalculatorStub.Subtract;
import org.tempuri.CalculatorStub.SubtractResponse;

public class TestService {

	public static void main(String[] args) throws RemoteException {
        Scanner in = new Scanner(System.in);
		CalculatorStub stub = new CalculatorStub("http://www.dneonline.com/calculator.asmx?WSDL");
		System.out.println("Print first integer");
	    int a = in.nextInt();
	    System.out.println("You second integer");
	    int b = in.nextInt();
	    System.out.println("You third integer");
	    int c = in.nextInt();
		
		Subtract s = new Subtract();
		s.setIntA(a);
		s.setIntB(b);
		SubtractResponse response= stub.subtract(s);
		int subResult = response.getSubtractResult();
		
		Add add = new Add();
		add.setIntA(subResult);
		add.setIntB(c);
		AddResponse response2= stub.add(add);
		int result = response2.getAddResult();
		System.out.println("Result "+a+" - "+b+" + " +c+ " is " + result);
	}

}
