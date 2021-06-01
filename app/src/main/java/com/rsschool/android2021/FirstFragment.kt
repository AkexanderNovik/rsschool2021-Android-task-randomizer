package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        val minId = view.findViewById<EditText>(R.id.min_value)
        // TODO: val max = ...
        val maxId = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            val minInputField = minId.text.toString()
            val maxInputField = maxId.text.toString()
            var min: Int? = null
            var max: Int? = null
            if (minInputField.isNotEmpty() && maxInputField.isNotEmpty()) {
                min = minInputField.toIntOrNull()
                max = maxInputField.toIntOrNull()
            }
            // TODO: send min and max to the SecondFragment
            if (min != null && max != null) {
                when {
                    min < 0 || max < 1 || min >= max -> {
                        closeKeyboard()
                        Toast.makeText(
                            context,
                            "Please, change Min and Max value",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    else -> (activity as MainActivity).passData(min, max)
                }
            } else {
                closeKeyboard()
                Toast.makeText(
                    context,
                    "Please, change input values. Min and Max should be less than 2,147,483,647",
                    Toast.LENGTH_LONG
                )
                    .show()
                minId.text.clear()
                maxId.text.clear()
            }
        }
    }

    private fun closeKeyboard() {
        activity?.currentFocus?.let { view ->
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}
