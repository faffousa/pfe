package com.vermeg.app.auth.controller;

import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.entity.TextSimilarityRequest;
import com.vermeg.app.auth.entity.similarityResult;
import com.vermeg.app.auth.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/similarity")
@CrossOrigin("*")
public class TextSimilarityController {
    @Autowired
    QuestionService questionservice;
    private List<similarityResult> similarityResults = new ArrayList<>();

    @PostMapping("/text-similarity")
    public ResponseEntity<?> calculateTextSimilarity(@RequestBody TextSimilarityRequest request) {
        List<Question> questions = questionservice.getQuestions();

        if (questions.size() == 0) {
            System.out.println("No Questions");
        }

        similarityResults.clear(); // Nettoyez la liste à chaque appel

        for (Question question : questions) {
            String text2 = question.getContent();
            String text1 = request.getText1();

            // Prepare the Python command to execute
            List<String> command = Arrays.asList("python", "C:/Users/pc/Desktop/fares/courzelobackend-main/target/classes/python/text_similarity.py", text1, text2);

            try {
                // Execute the Python script using ProcessBuilder
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                Process process = processBuilder.start();

                // Read the output from the Python script
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = bufferedReader.readLine();
                bufferedReader.close();

                // Check if the line is empty or null
                if (line != null && !line.isEmpty()) {
                    // Trim and parse the similarity score
                    double similarityScore = Double.parseDouble(line.trim()) * 100;
                    if (similarityScore >= 80) {
                        similarityResults.add(new similarityResult(question.getContent(), similarityScore));
                    }
                } else {
                    System.out.println("Error: Empty or null output from Python script.");
                }

                // Wait for the process to complete and get the exit value
                int exitCode = process.waitFor();
                System.out.println("Python script executed. Exit code: " + exitCode);
            } catch (IOException | InterruptedException | NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok(similarityResults);
    }
}

