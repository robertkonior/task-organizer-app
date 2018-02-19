package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> givenList = Arrays.asList(
                new Task(11L, "to_test", "something"),
                new Task(12L, "to_test2", "something2"));
        when(taskRepository.findAll()).thenReturn(givenList);
        //When
        List<Task> receivedList = dbService.getAllTasks();
        assertEquals(givenList,receivedList);
    }

    @Test
    public void testGetNoExistTask() {
        //Given
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        //When
        Optional<Task> result = dbService.getTask(1L);
        //Then
        assertEquals(Optional.empty(),result);
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(11L, "to_test", "something");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task savedTask = dbService.saveTask(task);
        //Then
        assertEquals(task,savedTask);
    }

    @Test
    public void testDeleteTask() {
        //Given
        //When
        dbService.deleteTask(1L);
        //Then
        verify(taskRepository, times(1)).deleteById(1L);
    }
}