package com.vermeg.app.auth.repo;

import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, Long> {
    void deleteQuestionByIdQuestion(long idQuestion);
    Question findQuestionByIdQuestion(long idQuestion);
    List<Question> findQuestionsByUser(User user);

    List<Question> findByCategory(String category);
    @Aggregation(pipeline = {
            "{$group: { _id: '$category', count: { $sum: 1 } } }"
    })
    List<?> countQuestionsByCategory();

    @Query(value = "{}, { 'category' : 1 }")
    List<String> findDistinctCategories();








}
