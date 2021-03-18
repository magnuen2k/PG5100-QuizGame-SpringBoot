package org.quizgame.controller;

import org.quizgame.entity.Category;
import org.quizgame.entity.Quiz;
import org.quizgame.service.CategoryService;
import org.quizgame.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class MatchController implements Serializable {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuizService quizService;

    private final int NUMBER_QUIZZES = 5;

    private boolean gameIsOn = false;
    private Long selectedCategoryId;
    private int counter;
    private List<Quiz> questions;

    public boolean isMatchOn() {
        return gameIsOn;
    }

    public String newMatch() {
        gameIsOn = true;

        selectedCategoryId = null;

        return "/ui/match.jsf?faces-redirect=true";
    }

    public List<Category> getCategories() {
        return categoryService.getAllCategories(false);
    }

    public boolean isCategorySelected() {
        return selectedCategoryId != null;
    }

    public void selectCategory(long id) {
        selectedCategoryId = id;
        counter = 0;
        questions = quizService.getRandomQuizzes(NUMBER_QUIZZES, selectedCategoryId);
    }

    public Quiz getCurrentQuiz() {
        return questions.get(counter);
    }

    public int getIncreasedCounter(){
        return counter + 1;
    }

    public int getNumberOfQuizzes(){
        return NUMBER_QUIZZES;
    }

    public String answerQuiz(int index){

        Quiz quiz = getCurrentQuiz();
        if(index == quiz.getCorrectAnswerIndex()){
            counter++;
            if(counter == NUMBER_QUIZZES){
                gameIsOn = false;
                return "result.jsf?victory=true&faces-redirect=true";
            }
        } else {
            gameIsOn = false;
            return "result.jsf?defeat=true&faces-redirect=true";
        }

        return null;
    }

}
