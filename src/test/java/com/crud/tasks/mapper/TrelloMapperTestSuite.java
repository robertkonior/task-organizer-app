package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
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
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> listToTest = Arrays.asList(new TrelloListDto("1", "testedList", true));
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1.1", "testedBoard", listToTest);
        List<TrelloBoardDto> trelloBoardToTest = Arrays.asList(trelloBoardDto);

        List<TrelloList> trelloLists = Arrays.asList(new TrelloList("1", "testedList", true));
        TrelloBoard trelloBoard = new TrelloBoard("1.1", "testedBoard", trelloLists);
        List<TrelloBoard> expectedList = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoards(trelloBoardToTest);
        //Then
        Assert.assertEquals(expectedList, mappedList);
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(new TrelloList("1", "testedList", true));
        TrelloBoard trelloBoard = new TrelloBoard("1.1", "testedBoard", trelloLists);
        List<TrelloBoard> trelloBoardToTest = Arrays.asList(trelloBoard);

        List<TrelloListDto> listToTest = Arrays.asList(new TrelloListDto("1", "testedList", true));
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1.1", "testedBoard", listToTest);
        List<TrelloBoardDto> expectedList = Arrays.asList(trelloBoardDto);
        //When
        List<TrelloBoardDto> mappedList = trelloMapper.mapToBoardsDto(trelloBoardToTest);
        //Then
        Assert.assertEquals(expectedList, mappedList);

    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listToTest = Arrays.asList(new TrelloListDto("1", "testedList", true));

        TrelloList trelloList = new TrelloList("1", "testedList", true);
        List<TrelloList> expectedList = Arrays.asList(trelloList);
        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(listToTest);
        //Then
        Assert.assertEquals(expectedList, mappedList);
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> listToTest = Arrays.asList(new TrelloList("1", "testedList", true));

        TrelloListDto trelloListDto = new TrelloListDto("1", "testedList", true);
        List<TrelloListDto> expectedList = Arrays.asList(trelloListDto);
        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(listToTest);
        //Then
        Assert.assertEquals(expectedList, mappedList);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard cardToTest = new TrelloCard("1", "something", "top", "22");

        TrelloCardDto expectedCard = new TrelloCardDto("1", "something", "top", "22");
        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(cardToTest);
        //Then
        Assert.assertEquals(expectedCard, mappedCard);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardToTest = new TrelloCardDto("1", "something", "top", "22");

        TrelloCard expectedCard = new TrelloCard("1", "something", "top", "22");
        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(cardToTest);
        //Then
        Assert.assertEquals(expectedCard, mappedCard);
    }
}