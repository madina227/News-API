package uz.gita.newsapp.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.gita.newsapp.databinding.DialogErrorBinding
import uz.gita.newsapp.utils.config

class ErrorDialog(ctx: Context, private val message: String) : Dialog(ctx) {

    private lateinit var binding: DialogErrorBinding

    private var cancelListener: (() -> Unit)? = null

    fun setCancelListener(block: () -> Unit) {
        cancelListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogErrorBinding.inflate(layoutInflater)
        config(binding)
        binding.tvErrorMessage.text = message
        binding.btnCancel.setOnClickListener {
            cancelListener?.invoke()
            dismiss()
        }
    }

}