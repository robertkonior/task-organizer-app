package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        List<Task> listTask = Arrays.asList(
                new Task(11L, "to_test", "something"),
                new Task(12L, "to_test2", "something2"));

        List<TaskDto> listTaskDto = Arrays.asList(
                new TaskDto(11L, "to_test", "something"),
                new TaskDto(12L, "to_test2", "something2"));

        when(service.getAllTasks()).thenReturn(listTask);
        when(taskMapper.mapToTaskDtoList(listTask)).thenReturn(listTaskDto);

        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(11L, "to_test", "something");

        TaskDto taskDto = new TaskDto(11L, "to_test", "something");
        when(service.getTask(11L)).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When&Then
        mockMvc.perform(get("/v1/task/getTask").
                contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "11")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(11)))
                .andExpect(jsonPath("$.title", is("to_test")))
                .andExpect(jsonPath("$.content", is("something")));
    }

    @Test
    public void shouldThrowTaskNotFoundException() throws Exception {
        //Given
        when(service.getTask(anyLong())).thenReturn(Optional.empty());

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "11"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(11L, "to_test", "something");
        when(service.getTask(11L)).thenReturn(Optional.of(task));
        //When&Then
        mockMvc.perform(delete("/v1/task/deleteTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "11"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(11L);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(11L, "to_test", "something");
        Task task = new Task(11L, "to_test", "something");
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When&Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(11)))
                .andExpect(jsonPath("$.title", is("to_test")))
                .andExpect(jsonPath("$.content", is("something")));
    }

    @Test
    public void createTask() throws Exception {
        //Given
        Task task = new Task(11L, "to_test", "something");
        TaskDto taskDto = new TaskDto(11L, "to_test", "something");
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}