package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDtoToMap = new TaskDto(11L, "to_test", "something");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDtoToMap);
        //Then
        Assert.assertEquals(11L, mappedTask.getId(), 0);
        Assert.assertEquals("to_test", mappedTask.getTitle());
        Assert.assertEquals("something", mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task taskToMap = new Task(11L, "to_test", "something");
        //When
        TaskDto mappedTask = taskMapper.mapToTaskDto(taskToMap);
        //Then
        Assert.assertEquals(11L, mappedTask.getId(), 0);
        Assert.assertEquals("to_test", mappedTask.getTitle());
        Assert.assertEquals("something", mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task taskToMap = new Task(11L, "to_test", "something");
        List<Task> listToMap = Arrays.asList(taskToMap);
        //When
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(listToMap);
        //Then
        Assert.assertEquals(11L, mappedList.get(0).getId(), 0);
        Assert.assertEquals("to_test", mappedList.get(0).getTitle());
        Assert.assertEquals("something", mappedList.get(0).getContent());
    }
}