package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
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
public class TrelloValidatorTestSuite {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(new TrelloList("1", "testedList", true));
        List<TrelloBoard> trelloBoardToTest = Arrays.asList(
                new TrelloBoard("1.1", "test", trelloLists),
                new TrelloBoard("1.1", "test_2", trelloLists),
                new TrelloBoard("1.1", "toTest", trelloLists)
        );
        //When
        List<TrelloBoard> filteredList = trelloValidator.validateTrelloBoards(trelloBoardToTest);
        //Then
        Assert.assertEquals(1,filteredList.size());
    }
}