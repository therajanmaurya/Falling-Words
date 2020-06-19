package com.therajanmaurya.fallingwords.ui.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.therajanmaurya.core.di.Injectable
import com.therajanmaurya.core.models.Words
import com.therajanmaurya.fallingwords.R

import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }
    private var screenHeight = 0f
    private var valueAnimator: ValueAnimator? = null

    companion object {
        const val DEFAULT_ANIMATION_DURATION = 8000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DisplayMetrics().apply {
            activity!!.windowManager.defaultDisplay.getMetrics(this)
            screenHeight = this.heightPixels.toFloat()
        }

        lifecycleScope.launch { mainViewModel.fetchWords() }

        pbWord.visibility = View.VISIBLE

        setScore()

        mainViewModel.wordApiResult.observe(activity!!, Observer {
            if (it != null && it.isNotEmpty()) {
                pbWord.visibility = View.GONE
                btnRight.visibility = View.VISIBLE
                tvFallingWord.visibility = View.VISIBLE
                mainViewModel.buildSuggestionList()
                tbTitle.text = mainViewModel.currentQuestion.textEng
                tvFallingWord.text = mainViewModel.currentSuggestion
                valueAnimator?.removeAllUpdateListeners()
                onStartAnimation()
            }
        })

        btnRight.setOnClickListener {
            if (mainViewModel.currentQuestion.textSpaL == tvFallingWord.text.toString()) {
                ++mainViewModel.rightAnswerCount
                mainViewModel.addAttemptedQuestion(1)
            } else {
                ++mainViewModel.wrongAnswerCount
                mainViewModel.addAttemptedQuestion(-1, tvFallingWord.text.toString())
            }
            setScore()
            moveToNextQuestion()
        }

        tvEnd.setOnClickListener {
            valueAnimator?.removeAllUpdateListeners()
            findNavController().navigate(
                MainFragmentDirections.showScore(
                    mainViewModel.rightAnswerCount.toString(),
                    mainViewModel.wrongAnswerCount.toString(),
                    mainViewModel.unAnsweredCount.toString(),
                    Words().apply { addAll(mainViewModel.attemptedQuestions) }
                )
            )
        }
    }

    private fun onStartAnimation() {
        ValueAnimator.ofFloat(-screenHeight, 0f).apply {
            valueAnimator = this
            this.addUpdateListener {
                val value = it.animatedValue as Float
                tvFallingWord?.translationY = value
                if (value == 0f) {
                    if (mainViewModel.suggestionCount < 4) {
                        tvFallingWord.text = mainViewModel.nextSuggestion()
                        this.removeAllUpdateListeners()
                        onStartAnimation()
                    } else {
                        ++mainViewModel.unAnsweredCount
                        mainViewModel.addAttemptedQuestion(0)
                        setScore()
                        moveToNextQuestion()
                    }
                }
            }
            this.interpolator = LinearInterpolator()
            this.duration =
                DEFAULT_ANIMATION_DURATION
            this.start()
        }
    }

    private fun moveToNextQuestion() {
        mainViewModel.nextQuestion().apply {
            tbTitle.text = this.textEng
            tvFallingWord.text = mainViewModel.currentSuggestion
            valueAnimator?.removeAllUpdateListeners()
            onStartAnimation()
        }
    }

    private fun setScore() {
        val score = "R ${mainViewModel.rightAnswerCount}" +
                " | W ${mainViewModel.wrongAnswerCount} | U ${mainViewModel.unAnsweredCount}"
        tvPlayAgain.text = score
    }
}
