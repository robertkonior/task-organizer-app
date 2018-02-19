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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDtoToMap = new TaskDto(11L, "to_test", "something");
        Task expectedTask = new Task(11L, "to_test", "something");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDtoToMap);
        //Then
        Assert.assertEquals(expectedTask, mappedTask);
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task taskToMap = new Task(11L, "to_test", "something");
        TaskDto expectedTask = new TaskDto(11L, "to_test", "something");
        //When
        TaskDto mappedTask = taskMapper.mapToTaskDto(taskToMap);
        //Then
        Assert.assertEquals(expectedTask, mappedTask);
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> listToMap = Arrays.asList(new Task(11L, "to_test", "something"));
        TaskDto expectedTask = new TaskDto(11L, "to_test", "something");
        List<TaskDto> expectedList = Arrays.asList(expectedTask);
        //When
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(listToMap);
        //Then
        Assert.assertEquals(expectedList, mappedList);
    }
}