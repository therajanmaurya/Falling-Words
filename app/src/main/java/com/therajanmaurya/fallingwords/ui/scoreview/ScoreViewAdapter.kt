package com.therajanmaurya.fallingwords.ui.scoreview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.therajanmaurya.core.models.Word
import com.therajanmaurya.fallingwords.R
import kotlinx.android.synthetic.main.item_word_score.view.*

class ScoreViewAdapter(private val context: Context) :
    ListAdapter<Word, ScoreViewAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.textEng == newItem.textEng
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_word_score, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = getItem(position)
        holder.tvWordEng.text = word.textEng
        holder.tvWordSpa.text = word.textSpaL
        holder.tvAnswer.text = word.answer

        when (word.score) {
            0 -> holder.tvAnswer.setTextColor(ContextCompat.getColor(context, R.color.gray))
            1 -> holder.tvAnswer.setTextColor(ContextCompat.getColor(context, R.color.green))
            -1 -> holder.tvAnswer.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWordEng = itemView.tvWordEng!!
        val tvWordSpa = itemView.tvWordSpa!!
        val tvAnswer = itemView.tvAnswer!!
    }
}
