package com.example.lambda;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.junit.Before;
import org.junit.Test;

import com.example.lambda.model.Person;

public class PersonManagerTest {

	
	PersonManager pm;
	
	@Before
	public void init(){
		pm = new PersonManager();
	}
	
	@Test
	public void testfind(){
		assertSame("Ula", pm.get(1).getFirstName());
		Predicate<Person> predicate = (p) -> p.getFirstName()=="Ula";
		assertSame("Ula", pm.find(predicate).getFirstName());
	}
	
	@Test
	public void testRemove(){
		assertSame("Kasia", pm.get(2).getFirstName());
		Predicate<Person> predicate = (p) -> p.getYob()==1990;
		pm.remove(predicate);
		assertSame(null, pm.get(2));
	}
	
	@Test
	public void testCreate(){
		assertEquals(3, pm.persons.size());
		IntFunction<Person> newP = (p) -> new Person("p", "e", 1);
		pm.add(newP);
		assertEquals(4, pm.persons.size());
	}
	
	@Test
	public void testEdit(){
		int newYob = 2222;
		assertEquals(1990, pm.get(2).getYob());
		UnaryOperator<Person> newP = (p) -> new Person(p.getFirstName(), p.getLastName(), newYob);
		pm.edit(newP);
		assertEquals(newYob, pm.get(2).getYob());
	}
	
	@Test
	public void testFilter(){
		int yob = 1990;
		assertEquals(1990, pm.get(2).getYob());
		Predicate<Person> predicate = (p) -> p.getYob()==1990;;
		ArrayList<Person> persons = pm.findAll(predicate);
		for(Person p : persons){
			assertEquals(p.getYob(), yob);
		}
	}
}
