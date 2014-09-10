package com.fabricgroup.spring4.component;

import com.fabricgroup.spring4.Person;

public interface IPersonService {
	public String getPersonName();
  public Person getPersonDetail(Integer id);
}
