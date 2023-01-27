package com.example.quizle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizle.QuizViewModel
import com.example.quizle.databinding.FragmentResultBinding

class ResultFragment: Fragment() {

    private lateinit var binding:FragmentResultBinding
    private val viewModel : QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Todo Code
        viewModel.score.observe(viewLifecycleOwner){

            binding.resultScoreText.text = "Hervorragend gemacht! \n Du hast $it Punkte :D "
        }

        binding.resultReviewButton.setOnClickListener {
            viewModel.restartGame()
            findNavController().navigateUp()
        }

    }

}