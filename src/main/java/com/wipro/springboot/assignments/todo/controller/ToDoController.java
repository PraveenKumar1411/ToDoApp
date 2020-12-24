package com.wipro.springboot.assignments.todo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;
import com.wipro.springboot.assignments.todo.model.ToDo;
import com.wipro.springboot.assignments.todo.service.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@Api(value="Todo", tags= {"todo-controller"})
@RestController
@RequestMapping("/todo")
public class ToDoController {

	@Autowired
	private TodoService todoService;
	
	//1. Should be able to store user specific information
	
	//2. User should be able to add a task
	//POST /todo
	@ResponseBody
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Record Added successfully"),
			@ApiResponse(code = 400, message = "Bad request error"),
			@ApiResponse(code = 404, message = "Resource Not found"),
			@ApiResponse(code = 500, message = "Unexpected Server error occured")
	})
	public long addTask(@RequestBody @NotNull ToDo todo) {
		return todoService.addTask(todo);
		
	}
	
	//put /todo/update
	//3. User should be able to mark as task as completed and vice versa
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Success response" ),
			@ApiResponse(code = 400, message = "Bad request error" ),
			@ApiResponse(code = 404, message = "Resource Not found" ),
			@ApiResponse(code = 500, message = "Unexpected Server error occured")
	})
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateTask(@RequestBody @NotNull ToDo todo) {
		 todoService.addTask(todo);
	}
	
	//4. User should be able to retrieve all his added tasks
	//GET /todo/{userName}
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Success response" ),
			@ApiResponse(code = 400, message = "Bad request error"),
			@ApiResponse(code = 404, message = "Resource Not found")
	})
	@GetMapping("/{id}/fetchAll")
	public List<ToDo> getAllUserTask(@PathVariable("id") String userName) {
		return todoService.getAllTasks(userName);
	}
	
	/*
	 * //5. User should be able to retrieve all his completed tasks //GET
	 * /todo/{userName}/comp
	 * 
	 * @GetMapping("{id}/fetchComp") public List<ToDo>
	 * getCompletedUserTask(@PathVariable("id") String userName) { return
	 * todoService.getUserTasksBasedOnStatus(userName, true);
	 * 
	 * }
	 * 
	 * //GET /todo/{UserName}/uncomp //6. User should be able to retrieve all his
	 * uncompleted tasks
	 * 
	 * @GetMapping("{id}/fetchUnComp") public List<ToDo>
	 * getUnCompletedTask(@PathVariable("id") String userName) { return
	 * todoService.getUserTasksBasedOnStatus(userName, false);
	 * 
	 * }
	 */
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Success response"),
			@ApiResponse(code = 400, message = "Bad request error" ),
			@ApiResponse(code = 404, message = "Resource Not found" ),
			@ApiResponse(code = 500, message = "Unexpected Server error occured" )
	})
	@GetMapping("{id}/fetchTasks")
	public List<ToDo> getUserTaskByStatus(@PathVariable("id") @NotNull String userName, @PathParam("status") @NotNull boolean status) {
		return todoService.getUserTasksBasedOnStatus(userName, status);
	}
	
	
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Success response" ),
			@ApiResponse(code = 400, message = "Bad request error"),
			@ApiResponse(code = 404, message = "Resource Not found"),
			@ApiResponse(code = 500, message = "Unexpected Server error occured")
	})
	//DEL /todo/{userName}/del/{TaskId}
	//7.  User should be able to delete a task
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/del/{id}")
	public void delUserTask(@PathVariable("id") String id) {
		todoService.deleteUserTasks(id);
	}
	
}
