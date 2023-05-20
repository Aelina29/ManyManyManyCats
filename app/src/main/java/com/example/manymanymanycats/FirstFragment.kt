package com.example.manymanymanycats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.manymanymanycats.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val viewModel:CatsViewModel by viewModels()
    private var _binding: FragmentFirstBinding? = null

    private lateinit var adapter : CatsAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.catsMLD.observe(viewLifecycleOwner,{
            adapter.list=viewModel.list
            adapter.notifyDataSetChanged()
        })
        adapter = CatsAdapter({
            Log.i("aelinka", it.toString())
            viewModel.getCats(it, requireContext())
        },{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundleOf(image_key to it.url))
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val image_key  = "image_key"
    }
}