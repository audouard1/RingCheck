package iut.clermont.pm.ringcheck.ui.mainringchek

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import iut.clermont.pm.ringcheck.R

class MainRingChekFragment : Fragment() {

    companion object {
        fun newInstance() = MainRingChekFragment()
    }

    private lateinit var viewModel: MainRingChekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_ring_chek_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainRingChekViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
