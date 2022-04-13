package com.example.omela.view.basket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Dao
import com.example.omela.view.Delegates
import com.example.omela.R
import com.example.omela.application.MyApplication
import com.example.omela.data.model.BasketItem
import com.example.omela.view.adapters.BasketAdapter
import com.example.omela.databinding.FragmentBasketBinding
import com.example.omela.data.model.BouquetItem
import com.example.omela.viewmodel.CategoriesViewModel
import com.example.omela.viewmodel.DatabaseViewModel
import com.example.omela.viewmodel.OneBouquetViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class BasketFragment : Fragment(), Delegates.BasketClicked {
    private var _binding: FragmentBasketBinding? = null
    private val binding
        get() = _binding!!

    private val basketAdapter = BasketAdapter(this)
    private val oneBouquetViewModel by viewModel<OneBouquetViewModel>()
    private val databaseViewModel by viewModel<DatabaseViewModel>()
    val bouquetList: MutableList<BasketItem> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderButton.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToOrderFragment()
            findNavController().navigate(action)
        }
        binding.toCurrentOrderButton.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToStatusFragment()
            findNavController().navigate(action)
        }
        binding.clearButton.setOnClickListener {
            alertDialog()
        }

        databaseViewModel.productList.observe(viewLifecycleOwner) { it ->
            Log.i("list", "1st $it")
            if (it.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "No items", Toast.LENGTH_SHORT).show()
                Log.i("list", "No items")
            }
            for (e in it) {
                bouquetList.add(e)
                Log.i("list", "2nd ${bouquetList.size}")
            }
            basketAdapter.setList(bouquetList)
        }
        Log.i("list", "6th ${bouquetList.size}")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            recyclerView.adapter = basketAdapter
        }
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        builder.setTitle("вы действительно хотите очистить корзину?")
        builder.setPositiveButton("да") { _, _ ->
            Toast.makeText(requireContext(), "корзина очищена", Toast.LENGTH_SHORT).show()
//            parentFragmentManager.commit {
//                replace<Fav>(R.id.nav_fragment)
//                setReorderingAllowed(true)
//                addToBackStack("name") // name can be null
//            }
        }
        builder.setNegativeButton("нет") { _, _ ->
            Toast.makeText(requireContext(), "действие отменено", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    private fun alertDialogDelete() {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        builder.setTitle("вы действительно удалить этот букет?")
        builder.setPositiveButton("да") { _, _ ->
            Toast.makeText(requireContext(), "букет удален", Toast.LENGTH_SHORT).show()
//            parentFragmentManager.commit {
//                replace<Fav>(R.id.nav_fragment)
//                setReorderingAllowed(true)
//                addToBackStack("name") // name can be null
//            }
        }
        builder.setNegativeButton("нет") { _, _ ->
            Toast.makeText(requireContext(), "действие отменено", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    override fun onItemClick(basketItem: BasketItem) {
        alertDialogDelete()
    }
}