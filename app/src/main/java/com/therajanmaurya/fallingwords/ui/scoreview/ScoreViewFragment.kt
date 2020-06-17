package com.therajanmaurya.fallingwords.ui.scoreview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.therajanmaurya.core.di.Injectable
import com.therajanmaurya.fallingwords.R
import kotlinx.android.synthetic.main.fragment_score_view.*
import javax.inject.Inject

class ScoreViewFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val params by navArgs<ScoreViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvPlayAgain.setOnClickListener {
            findNavController().navigate(ScoreViewFragmentDirections.showMain())
        }

        tvRight.text = params.rightAnswers
        tvWrong.text = params.wrongAnswers
        tvNoAnswer.text = params.unAnswered

        ScoreViewAdapter(activity!!).apply {
            rvAnswers.setHasFixedSize(true)
            rvAnswers.adapter = this
            this.submitList(params.words)
        }
    }
}
