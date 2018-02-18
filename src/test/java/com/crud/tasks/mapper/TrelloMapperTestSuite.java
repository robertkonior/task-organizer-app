package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "testedList", true);
        List<TrelloListDto> listToTest = Arrays.asList(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1.1", "testedBoard", listToTest);
        List<TrelloBoardDto> trelloBoardToTest = Arrays.asList(trelloBoardDto);
        //When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoards(trelloBoardToTest);
        //Then
        Assert.assertEquals(trelloBoardToTest.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(trelloBoardToTest.get(0).getName(), mappedList.get(0).getName());
        Assert.assertTrue(trelloBoardToTest.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trello = new TrelloList("1", "testedList", true);
        List<TrelloList> trelloLists = Arrays.asList(trello);
        TrelloBoard trelloBoard = new TrelloBoard("1.1", "testedBoard", trelloLists);
        List<TrelloBoard> trelloBoardToTest = Arrays.asList(trelloBoard);
        //When
        List<TrelloBoardDto> mappedList = trelloMapper.mapToBoardsDto(trelloBoardToTest);
        //Then
        Assert.assertEquals(trelloBoardToTest.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(trelloBoardToTest.get(0).getName(), mappedList.get(0).getName());
        Assert.assertTrue(trelloBoardToTest.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "testedList", true);
        List<TrelloListDto> listToTest = Arrays.asList(trelloListDto);
        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(listToTest);
        //Then
        Assert.assertEquals(listToTest.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(listToTest.get(0).getName(), mappedList.get(0).getName());
        Assert.assertTrue(listToTest.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "testedList", true);
        List<TrelloList> listToTest = Arrays.asList(trelloList);
        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(listToTest);
        //Then
        Assert.assertEquals(listToTest.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(listToTest.get(0).getName(), mappedList.get(0).getName());
        Assert.assertTrue(listToTest.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard cardToTest = new TrelloCard("1", "something", "top", "22");
        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(cardToTest);
        //Then
        Assert.assertEquals("1", mappedCard.getName());
        Assert.assertEquals("something", mappedCard.getDescription());
        Assert.assertEquals("top", mappedCard.getPos());
        Assert.assertEquals("22", mappedCard.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardToTest = new TrelloCardDto("1", "something", "top", "22");
        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(cardToTest);
        //Then
        Assert.assertEquals("1", mappedCard.getName());
        Assert.assertEquals("something", mappedCard.getDescription());
        Assert.assertEquals("top", mappedCard.getPos());
        Assert.assertEquals("22", mappedCard.getListId());
    }
}