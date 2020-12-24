package com.wipro.springboot.assignments.todo.datatest;

import com.wipro.springboot.assignments.todo.model.ToDo;
import com.wipro.springboot.assignments.todo.model.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ToDoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testFindAll(){
        List<ToDo> list = (List<ToDo>) todoRepository.findAll();
        assertEquals(0, list.size());
    }

}
