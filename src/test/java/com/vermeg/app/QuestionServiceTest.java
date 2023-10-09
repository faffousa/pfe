package com.vermeg.app;

import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.repo.QuestionRepository;
import com.vermeg.app.auth.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.mockito.Mockito.when;
import org.junit.Assert;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        // Configurez le comportement simulé du restTemplateBuilder ici
        questionService.setRestTemplateBuilder(new RestTemplateBuilder());
    }

    @Test
    public void testAddQuestion() {
        // Créez un exemple de question
        Question question = new Question();
        question.setTitle("Test Title");
        question.setContent("Test Content");

        // Configurez le comportement simulé du repository
        when(questionRepository.save(question)).thenReturn(question);

        // Appelez la méthode de service à tester
        Question savedQuestion = questionService.addQuestion(question, 1L);

        // Vérifiez que la question a bien été sauvegardée
        Assert.assertEquals("Test Title", savedQuestion.getTitle());
        Assert.assertEquals("Test Content", savedQuestion.getContent());

        // Vérifiez que le repository a été appelé
        org.mockito.Mockito.verify(questionRepository, org.mockito.Mockito.times(1)).save(question);
    }
}
