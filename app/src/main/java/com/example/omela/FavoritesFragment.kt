package com.example.omela

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.omela.adapters.FlowersAdapter
import com.example.omela.databinding.FragmentFavoritesBinding
import com.example.omela.login.NeedToAuthorizeFragment
import com.example.omela.model.FlowersItem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FavoritesFragment : Fragment(), Delegates.FlowerClicked {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var flowersAdapter: FlowersAdapter

    private val flowersList by lazy {
        mutableListOf(
            FlowersItem(
                "НЕВЕСТЕ ДОРОГУ",
                R.drawable.flower_1,
                4900,
                false,
            ),
            FlowersItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                5000,
                false,
            ))
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        flowersAdapter = FlowersAdapter(requireContext(), this)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Firebase.auth.currentUser
        if (user == null) {
            // User is not signed in
            parentFragmentManager.commit {
                replace<NeedToAuthorizeFragment>(R.id.nav_fragment)
                setReorderingAllowed(true)
                addToBackStack("name") // name can be null
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            topsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            topsRecyclerView.adapter = flowersAdapter
        }
        flowersAdapter.setList(flowersList)
    }

    override fun onItemClick(flower: FlowersItem) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToFlowerDetailsFragment2(flower)
        findNavController().navigate(action)
    }
}