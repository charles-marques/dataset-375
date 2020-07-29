package br.com.levymoreira.testes;

import junit.framework.TestSuite;

public class Teste {
	public static TestSuite Test() {  
		   TestSuite suite = new TestSuite();  
		   suite.addTest(new TesteContaDAO ("testeSalvarParcela1"));  
		   suite.addTest(new TesteContaDAO ("testeSalvarConta"));  
		   return suite;  
	} 
}
