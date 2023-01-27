package com.example.quizle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizle.QuizViewModel
import com.example.quizle.databinding.FragmentQuizBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding

    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Todo Code

        //upDateUi()

        viewModel.score.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Live", Toast.LENGTH_SHORT)
                .show()
            binding.scoreText.text = it.toString()
        }
        viewModel.currentQuestion.observe(viewLifecycleOwner){
            binding.questionText.text = it.name
        }

        viewModel.gameEnd.observe(viewLifecycleOwner){
            if (it){
                findNavController()
                    .navigate(QuizFragmentDirections.actionQuizFragmentToResultFragment())
            }
        }

        binding.actorButton.setOnClickListener {
            viewModel.checkAnswer(true)
            //upDateUi()
        }
        binding.writerButton.setOnClickListener {
            viewModel.checkAnswer(false)
            //upDateUi()
        }
    }


}
