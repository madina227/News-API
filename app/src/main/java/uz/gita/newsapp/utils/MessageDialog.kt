package uz.gita.newsapp.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.gita.newsapp.databinding.DialogMessageBinding


class MessageDialog(ctx: Context, private val message: String) : Dialog(ctx) {

    private lateinit var binding: DialogMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogMessageBinding.inflate(layoutInflater)
        config(binding)
        binding.tvMessage.text = message

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}