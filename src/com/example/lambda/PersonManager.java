package com.example.lambda;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.example.lambda.model.Person;

public class PersonManager {

	Map<Integer,Person> persons = new HashMap<Integer,Person>();

	public PersonManager() {
		persons.put(1, new Person("Ula", "Sawicka", 1990));
		persons.put(2, new Person("Kasia", "Kowalska", 1990));
		persons.put(3, new Person("Tomasz", "Nowak", 2001));
	}
	
	public void add(IntFunction<Person> p){
		persons.put(persons.size()+1, p.apply(0));
	}
	
	public void edit(UnaryOperator<Person> p){
		java.util.Iterator<Entry<Integer, Person>> i = persons.entrySet().iterator();
		while(i.hasNext()){
			Entry<Integer, Person> e = i.next();
			e.setValue(p.apply(e.getValue()));
		}
	}
	
	public Person get(int key){
		return persons.get(key);
	}
	
	public void remove(Predicate<Person> predicate){
		java.util.Iterator<Entry<Integer, Person>> i = persons.entrySet().iterator();
		ArrayList<Integer> keys = new ArrayList<Integer>();
		while(i.hasNext()){
			Entry<Integer, Person> e = i.next();
			if(predicate.test(e.getValue())){
				keys.add(e.getKey());
			}
		}
		
		for(Integer k : keys){
			persons.remove(k);
		}
	}
	
	public Person find(Predicate<Person> predicate){
		java.util.Iterator<Entry<Integer, Person>> i = persons.entrySet().iterator();
		while(i.hasNext()){
			Person p = (Person) i.next().getValue();
			if(predicate.test(p)){
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Person> findAll(Predicate<Person> predicate){
		java.util.Iterator<Entry<Integer, Person>> i = persons.entrySet().iterator();
		ArrayList<Person> pList = new ArrayList<Person>();
		while(i.hasNext()){
			Person p = (Person) i.next().getValue();
			if(predicate.test(p)){
				pList.add(p);
			}
		}
		return pList;
	}

}
