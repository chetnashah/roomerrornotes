package com.example.samplememorynotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.core.data.Note
import com.example.samplememorynotes.R
import com.example.samplememorynotes.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*


/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {


    private lateinit var viewModel: NoteViewModel
    private var currNote = Note("", "", 0L, 0L)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    private fun observeViewModel() {
        viewModel.saved.observe(this, Observer {
            if(it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkButton.setOnClickListener {
            if(titleView.text.toString() != "" || contentView.text.toString() != "") {
                val time = System.currentTimeMillis()
                currNote.title = titleView.text.toString()
                currNote.content = contentView.text.toString()

                currNote.updateTime = time
                if(currNote.id == 0L) {// id will be 0 if new note
                    currNote.creationTime = time
                }
                viewModel.saveNote(currNote)
            }
            findNavController().popBackStack()
        }

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        observeViewModel()
    }
}