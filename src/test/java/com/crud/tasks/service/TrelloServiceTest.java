package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.BaseStubbing;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void fetchTrelloBoards() {
        //Given
        List<TrelloListDto> listToTest = Arrays.asList(new TrelloListDto("1", "testedList", true));
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1.1", "testedBoard", listToTest);
        List<TrelloBoardDto> trelloBoardToTest = Arrays.asList(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardToTest);
        //When
        List<TrelloBoardDto> fetchedList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(trelloBoardToTest, fetchedList);
    }

    @Test
    public void createdTrelloCard() {
        //Given
        TrelloCardDto cardToTest = new TrelloCardDto("1", "something", "top", "22");
        CreatedTrelloCardDto createdToTest = new CreatedTrelloCardDto("1", "1", "some_url");
        when(trelloClient.createNewCard(cardToTest)).thenReturn(createdToTest);
        when(adminConfig.getAdminMail()).thenReturn("admin@admin.com");

        //When
        CreatedTrelloCardDto createdCard = trelloService.createdTrelloCard(cardToTest);
        //Then
        assertEquals(createdToTest, createdCard);
        verify(emailService, times(1)).send(SimpleEmailService.NEW_TRELLO_CARD,new Mail("admin@admin.com","Task: New Trello card ","New card: " +cardToTest.getName()+ "has been created on your Trello account"));
    }

    @Test
    public void shouldNotSendEmail() {
        //Given
        //When
        trelloService.createdTrelloCard(null);
        //Then
        verify(emailService, times(0)).send(emailService.NEW_TRELLO_CARD ,null);
    }

}