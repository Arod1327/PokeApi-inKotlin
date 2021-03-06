package com.example.mykotlinpokedex.Interfaces

import android.view.View

interface IItemClickListener {
    fun onClick(view: View, position: Int)
}