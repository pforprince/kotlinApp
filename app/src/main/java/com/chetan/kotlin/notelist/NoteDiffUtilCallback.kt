package com.chetan.kotlin.notelist


import androidx.recyclerview.widget.DiffUtil
import com.chetan.domain.domainmodel.Note

class NoteDiffUtilCallback : DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.creationDate == newItem.creationDate
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.creationDate == newItem.creationDate
    }

}