package com.david.spanisleague.ui

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.david.spanisleague.R
import kotlinx.android.synthetic.main.road_references_dialog.dialogFragmentTitle

class RoadReferencesDialogFragment(private val application: Application) : DialogFragment() {


    private lateinit var dialogFragmentTrigger: String
    //private lateinit var adapter: ArrayAdapter<RoadReference>



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.road_references_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialogFragmentTrigger = arguments?.getString("trigger", "").toString()
        //roadReferenceSearchInput.hint = application.applicationContext.getString(R.string.search)
        //roadReferenceSearchInput.isFocused
        //dialogFragmentTitle.hint = application.applicationContext.getString(R.string.road_reference_title)
        /*
        adapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_list_item_1, RoadReferenceRepository(application)
                .getFilteredRoadReferenceList(""))
         */
        //roadReferenceListView.adapter = adapter
        //selectRoadReference(adapter)

        /*
        roadReferenceSearchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                if (charSequence.isNotEmpty())
                    adapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_list_item_1,
                            RoadReferenceRepository(application).getFilteredRoadReferenceList(charSequence.toString()))
                    roadReferenceListView.adapter = adapter
                    adapter.notifyDataSetChanged()
            }
            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })
        */

    }

    override fun onResume() {
        super.onResume()
    }
}
