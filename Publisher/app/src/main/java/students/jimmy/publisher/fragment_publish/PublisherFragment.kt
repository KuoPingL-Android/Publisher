package students.jimmy.publisher.fragment_publish

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import students.jimmy.publisher.R
import students.jimmy.publisher.adapters.bind
import students.jimmy.publisher.databinding.FragmentPublishBinding
import students.jimmy.publisher.databinding.ItemTagBinding
import students.jimmy.publisher.factory.GeneralViewModelFactory

class PublisherFragment : AppCompatDialogFragment() {
    private lateinit var binding: FragmentPublishBinding
    private val viewModel by lazy {
        GeneralViewModelFactory().create(PublisherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.PublishDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPublishBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        viewModel.articleSent.observe(this, Observer {
            it?.let {
                viewModel.resetArticleSent()
                findNavController().navigateUp()
            }
        })

        viewModel.discardArticle.observe(this, Observer {
            it?.let {
                viewModel.resetDiscardArticle()

                // Display dialog
                context?.let {context ->
                    val alertBuilder = AlertDialog.Builder(context)
                    alertBuilder.setMessage(context.getString(R.string.warning_discarddrafe))
                    alertBuilder.setPositiveButton(context.getString(R.string.button_discard)){dialog, button ->
                        findNavController().navigateUp()
                        Log.i("DIALOG POS", "$dialog")
                        Log.i("BUTTON POS", "$button")
                    }
                    alertBuilder.setNegativeButton(context.getString(R.string.button_cancel)) {dialog, button ->
                        Log.i("DIALOG NEG", "$dialog")
                        Log.i("BUTTON NEG", "$button")
                    }
                    val alert = alertBuilder.create()
                    alert.show()
                }

            }
        })

        binding.viewModel = viewModel

        return binding.root
    }

    private fun setupBinding() {
        binding.editPublishTag.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s?.trim()?.isEmpty() == true) {
                    
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s == " ") {

                }
            }

            fun generateATagLabel(withText: String) {
                context?.let {context ->
                    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val tagBinding = ItemTagBinding.inflate(inflater)
                    tagBinding.textTagTag.text = withText

                }
            }

        })
    }
}