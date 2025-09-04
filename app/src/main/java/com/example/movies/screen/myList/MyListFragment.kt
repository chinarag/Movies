package com.example.movies.screen.myList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movies.R
import com.example.movies.adapter.MyListAdapter
import com.example.movies.databinding.FragmentMyListBinding
import com.example.movies.util.gone
import com.example.movies.util.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyListFragment : Fragment() {
   lateinit var binding: FragmentMyListBinding
   private val viewModel by viewModels<MyListViewModel>()
   private val adapter = MyListAdapter()

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = FragmentMyListBinding.inflate(layoutInflater)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.rvMyList.adapter = adapter
      adapter.onItemClickListener = {movie ->
         viewModel.deleteMovie(movie.id)
         removed(movie.id)

      }
      observeData()
      viewModel.getAllMovies()
   }

   private fun observeData(){
      viewModel.movies.observe(viewLifecycleOwner){
         adapter.updateList(it)

         if(it.isNullOrEmpty()){
            binding.imageViewEmpty.visible()
            binding.rvMyList.gone()
         }else{
            binding.imageViewEmpty.gone()
            binding.rvMyList.visible()
         }
      }
   }

   private fun removed(id: Int){
      val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
      sp.edit().putBoolean("added_${id}", false).apply()

   }

}