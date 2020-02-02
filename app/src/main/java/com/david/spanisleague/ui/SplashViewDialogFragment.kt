package com.david.spanisleague.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.david.spanisleague.R
import com.david.spanisleague.utils.SPLASH_TIME_OUT
import kotlinx.android.synthetic.main.road_references_dialog.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * SplashViewDialogFragment
 *
 * Just a splash view that presents the app,
 * it delay less than 2 seconds, an then desappear
 *
 * @author david.mazo
 */
class SplashViewDialogFragment(private val application: Application) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.road_references_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dialogFragmentTrigger = arguments?.getInt("trigger", 0)!!
        textViewAppName.text = application.getString(dialogFragmentTrigger).toUpperCase()
        val soccerLeagueDrawableResource = arguments?.getInt("image", 0)!!
        imageViewSoccerLeague.setImageDrawable(application.getDrawable(soccerLeagueDrawableResource))
    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(SPLASH_TIME_OUT.toLong())
            dismiss()
        }
    }
}
