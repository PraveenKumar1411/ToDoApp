package com.wipro.springboot.assignments.todo.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.springboot.assignments.todo.model.TodoRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wipro.springboot.assignments.todo.model.ToDo;
import com.wipro.springboot.assignments.todo.service.TodoService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class TodoServiceTest {

	@InjectMocks
	private TodoService todoService;

	@Mock
	private TodoRepository todoRepository;

	@Rule
	public ExpectedException expectation = ExpectedException.none();

	@Test
	public void addTaskTest(){
		expectation.expect(NullPointerException.class);
		assertThrows(NullPointerException.class
		, () -> todoService.addTask(null));
	}

	@Test
	public void getUserTasksBasedOnStatusTrueTest() throws Exception {
		when(todoRepository.findByuserNameAndStatusAllIgnoreCase("praveen", true))
				.thenReturn(Arrays.asList(
						new ToDo(1, "praveen", "rent", true),
						new ToDo(2, "praveen", "power", true)));

		List<ToDo> list = todoService.getUserTasksBasedOnStatus("praveen", true);

		assertEquals(list.get(0).getId(), 1 );
	}

	@Test
	public void getUserTasksBasedOnStatusFalseTest() throws Exception {
		when(todoRepository.findByuserNameAndStatusAllIgnoreCase("praveen", false))
				.thenReturn(Arrays.asList(
						new ToDo(1, "praveen", "rent", false),
						new ToDo(2, "praveen", "power", false)));

		List<ToDo> list = todoService.getUserTasksBasedOnStatus("praveen", false);

		assertEquals(list.get(1).getId(), 2);
	}

	@Test
	public void getAllTasksTest(){
		when(todoRepository.findAll()).thenReturn(Arrays.asList(
				new ToDo(1, "praveen", "water plan", true),
				new ToDo(2, "praveen", "pay rent", true)
		));

		List<ToDo> list = todoService.getAllTasks("praveen");

		assertEquals(list.get(0).getTaskName(), "water plan");
	}


	@Test
	public void deleteTaskTest() {
		TodoService todoService = mock(TodoService.class);
		doNothing().when(todoService).deleteUserTasks(isA(String.class));
		todoService.deleteUserTasks( "");

		verify(todoService, times(1)).deleteUserTasks( "");
	}

	@Test
	public void deleteTaskTestTwo() {
		TodoService todoService = mock(TodoService.class);
		doNothing().when(todoService).deleteUserTasks(isA(String.class));
		todoService.deleteUserTasks( "1");

		verify(todoService, times(1)).deleteUserTasks( "1");
	}


		public String readTestJson(String fileName) throws JsonProcessingException {
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
