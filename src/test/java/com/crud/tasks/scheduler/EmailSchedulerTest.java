package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldSendInformationEmailForOneTask() {
        //Given
        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn("admin@admin.com");
        //When
        emailScheduler.sendInformationEmail();
        //Then
        verify(simpleEmailService, times(1)).send(new Mail("admin@admin.com", "Tasks: Once a day email", "Currently in database you got: 1 task"));
    }

    @Test
    public void shouldSendInformationEmailForManyTasks() {
        //Given
        when(taskRepository.count()).thenReturn(5L);
        when(adminConfig.getAdminMail()).thenReturn("admin@admin.com");
        //When
        emailScheduler.sendInformationEmail();
        //Then
        verify(simpleEmailService, times(1)).send(new Mail("admin@admin.com", "Tasks: Once a day email", "Currently in database you got: 5 tasks"));
    }

}