package com.example.quizle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizle.data.QuizRepository
import com.example.quizle.data.model.Question


class QuizViewModel: ViewModel() {

    private val repository = QuizRepository()

    private val questions = repository.loadQuestions()

  /*  private var _score = 0
    val score: Int
        get() = _score*/


    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
    get() = _score

    /*private var _currentQuestion = questions.random()
val currentQuestion: Question
    get() = _currentQuestion*/


    private val _currentQuestion = MutableLiveData<Question>(questions.random())
    val currentQuestion: LiveData<Question>
    get () = _currentQuestion


    private val _gameEnd = MutableLiveData<Boolean>(false)
    val gameEnd : LiveData<Boolean>
    get() = _gameEnd



    fun checkAnswer(isActor : Boolean){
        if (isActor == currentQuestion.value?.isActor){

            //_score ++

            _score.value = _score.value?.plus(1)
            getNextQuestion()
        }else {
          _gameEnd.value = true
        }


    }


    private fun getNextQuestion() {
        val nextQuestion = questions.random()

        if (nextQuestion == currentQuestion.value){
            getNextQuestion()
        }else {
            _currentQuestion.value = nextQuestion
        }



    }

    fun restartGame(){
       // _score = 0
        _score.value = 0
        getNextQuestion()
        _gameEnd.value =false

    }
}