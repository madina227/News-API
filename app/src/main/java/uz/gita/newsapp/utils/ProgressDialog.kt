package uz.gita.newsapp.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.gita.newsapp.databinding.DialogProgressBinding


class ProgressDialog(ctx: Context) : Dialog(ctx) {

    private lateinit var binging: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binging = DialogProgressBinding.inflate(layoutInflater)
        config(binging)
        setCancelable(false)
    }

}