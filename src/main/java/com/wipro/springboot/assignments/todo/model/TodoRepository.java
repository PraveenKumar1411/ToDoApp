package com.wipro.springboot.assignments.todo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, Long>{

	List<ToDo> findByuserNameAndStatusAllIgnoreCase(String userName, boolean status);
	
}
