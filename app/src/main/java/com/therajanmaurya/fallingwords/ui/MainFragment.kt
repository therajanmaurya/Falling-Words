package com.therajanmaurya.fallingwords.ui

import android.animation.ValueAnimator
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.therajanmaurya.core.di.Injectable
import com.therajanmaurya.fallingwords.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }
    private var screenHeight = 0f
    private var valueAnimator = ValueAnimator.ofFloat(-screenHeight, 0f)

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

        if (!mainViewModel.isFetchedFromNetwork) {
            lifecycleScope.launch { mainViewModel.fetchWords() }
        }

        pbWord.visibility = View.VISIBLE

        mainViewModel.wordApiResult.observe(activity!!, Observer {
            if (it != null && it.isNotEmpty()) {
                pbWord.visibility = View.GONE
                mainViewModel.buildSuggestionList()
                tbTitle.text = mainViewModel.currentQuestion.textEng
                tvFallingWord.text = mainViewModel.currentSuggestion
                onStartAnimation()
            }
        })

        btnRight.setOnClickListener {
            if (mainViewModel.currentQuestion.textSpaL == tvFallingWord.text.toString()) {
                tvScore.text = (++mainViewModel.score).toString()
            }
            moveToNextQuestion()

        }
    }

    private fun onStartAnimation() {
        ValueAnimator.ofFloat(-screenHeight, 0f).apply {
            valueAnimator = this
            this.addUpdateListener {
                val value = it.animatedValue as Float
                tvFallingWord.translationY = value
                if (value == 0f) {
                    if (mainViewModel.suggestionCount < 4) {
                        tvFallingWord.text = mainViewModel.nextSuggestion()
                        Timber.d("Current Suggestion: " + tvFallingWord.text.toString())
                        Timber.d("Suggestions: " + TextUtils.join(", ", mainViewModel.suggestionList) + "\n")
                        this.removeAllUpdateListeners()
                        onStartAnimation()
                    } else {
                        tvFallingWord.visibility = View.GONE
                        this.removeAllUpdateListeners()
                        moveToNextQuestion()
                        onStartAnimation()
                        tvFallingWord.visibility = View.VISIBLE
                    }
                }
                // Timber.d("Screen height: " + screenHeight.toString())
                // Timber.d("Value height: " + value.toString())
            }
            this.interpolator = LinearInterpolator()
            this.duration = DEFAULT_ANIMATION_DURATION
            this.start()
        }
    }

    private fun moveToNextQuestion() {
        mainViewModel.nextQuestion().apply {
            tbTitle.text = this.textEng
            tvFallingWord.text = mainViewModel.currentSuggestion
        }
    }
}
