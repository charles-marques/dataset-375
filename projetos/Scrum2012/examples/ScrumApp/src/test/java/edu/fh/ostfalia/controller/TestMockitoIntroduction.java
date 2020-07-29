package edu.fh.ostfalia.controller;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class TestMockitoIntroduction {

	@Test
	public void testList() throws Exception {
		List list = Mockito.mock(List.class);
		list.add("A");
		list.clear();
		
		Mockito.verify(list).add("A");
		Mockito.verify(list).clear();
	}
	
	
	@Test
	public void testLinkedList() throws Exception {
		List list = Mockito.mock(LinkedList.class);
		Mockito.when(list.get(0)).thenReturn("Hello");
		System.out.println(list.get(0));
	}
}
