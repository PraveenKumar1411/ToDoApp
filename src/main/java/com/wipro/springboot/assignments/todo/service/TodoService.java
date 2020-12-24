package com.wipro.springboot.assignments.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.springboot.assignments.todo.model.ToDo;
import com.wipro.springboot.assignments.todo.model.TodoRepository;


@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepo;
	
	//add method
	public long addTask(ToDo todo) {
		return todoRepo.save(todo).getId();
	}

	//get all user specifictasks
	public List<ToDo> getAllTasks(String userName) {
		List<ToDo> allTasks = new ArrayList<>();
		todoRepo.findAll()
				.forEach(allTasks::add);

		return allTasks;
	}
	//get user specific  tasks based on status
	public List<ToDo> getUserTasksBasedOnStatus(String userName, boolean status){
		return todoRepo.findByuserNameAndStatusAllIgnoreCase(userName, status);
	}
	
	//delete tasks
	public void deleteUserTasks(String uniqueId) {
		long id = Long.valueOf(uniqueId);
		todoRepo.deleteById(id);
	}
}
