package com.fabricgroup.spring4.component;

import org.springframework.stereotype.Component;

import com.fabricgroup.spring4.Person;

@Component
public class PersonService implements IPersonService {
	@Override
	public Person getPersonDetail(Integer id){
		Person p = new Person();
		p.setId(id);
		p.setLocation("Varanasi");
		p.setName("Ram");
		return p;
	}
	
	
	@Override
	public String getPersonName(){
		return "Charles";
	}
}
