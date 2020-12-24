package com.wipro.springboot.assignments.todo.controllertest;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.springboot.assignments.todo.controller.ToDoController;
import com.wipro.springboot.assignments.todo.model.ToDo;
import com.wipro.springboot.assignments.todo.service.TodoService;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoController.class)
class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TodoService todoService;
	
	@Test	
	public void getAllTaskFailTest() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/todo//fetchAll")
				.contentType(MediaType.APPLICATION_JSON);
		
		final ResultActions resultActions = mockMvc.perform(request);
		
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void getAllTaskPassTest() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/todo/praveen/fetchAll")
				.contentType(MediaType.APPLICATION_JSON);

		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isOk());
	}
	
	@Test
	public void addTaskPass() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/todo/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(readTestJson("addPass.json"));
		
		final ResultActions resultActions = mockMvc.perform(request);
		
		resultActions.andExpect(status().isCreated());
	}

	@Test
	public void addTaskFail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/todo/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("");
		
		final ResultActions resultActions = mockMvc.perform(request);
		
		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	public void updateTaskFail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.put("/todo/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("");

		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	public void updateTaskPass() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.put("/todo/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(readTestJson("updatePass.json"));

		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isCreated());
	}

	@Test
	public void delTaskFail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/todo/del")
				.contentType(MediaType.APPLICATION_JSON);
		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().is4xxClientError());
	}
	@Test
	public void delTaskPass() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/todo/del/1")
				.contentType(MediaType.APPLICATION_JSON);
		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isOk());
	}

	@Test
	public void getUserTaskByStatusPass() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/todo/praveen/fetchTasks")
				.contentType(MediaType.APPLICATION_JSON);
		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isOk());
	}

	@Test
	public void getUserTaskByStatusFalsePass() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/todo/praveen/fetchTasks")
				.contentType(MediaType.APPLICATION_JSON)
				.param("status", "false");
		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isOk());
	}


	@Test
	public void getUserTaskByStatusTruePass() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/todo/praveen/fetchTasks")
				.contentType(MediaType.APPLICATION_JSON)
				.param("status", "true");
		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isOk());
	}


	@Test
	public void getUserTaskByStatusFail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/todo/praveen/fetchTasks")
				.contentType(MediaType.APPLICATION_JSON)
				.param("status", "match");
		final ResultActions resultActions = mockMvc.perform(request);

		resultActions.andExpect(status().isBadRequest());
	}






		public String readTestJson(String fileName) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper ();
		
		ToDo todoObject = null;
		
		try {
			todoObject = objectMapper.readValue(new File("src/test/resources/"+fileName), ToDo.class);
		}
		catch(IOException e) {
			todoObject = new ToDo();
		}
		
		return objectMapper.writeValueAsString(todoObject);
	}

}
