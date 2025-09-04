package com.example.movies.screen.detail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movies.R
import com.example.movies.adapter.CastAdapter
import com.example.movies.adapter.CommentAdapter
import com.example.movies.adapter.DiscoverMoviesAdapter
import com.example.movies.adapter.TrailerAdapter
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.model.MyListModel
import com.example.movies.model.movies.Result
import com.example.movies.screen.splash.SplashFragmentDirections
import com.example.movies.util.gone
import com.example.movies.util.visible
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
   lateinit var binding: FragmentDetailBinding
   private val castAdapter = CastAdapter()
   private val trailerAdapter = TrailerAdapter()
   private val similarAdapter = DiscoverMoviesAdapter()
   private val commentAdapter = CommentAdapter()
   private val viewModelDetail by viewModels<DetailViewModel>()
   private val args: DetailFragmentArgs by navArgs()
   private var isSelected = false



   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = FragmentDetailBinding.inflate(layoutInflater)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      checkSelected()
      val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)

      binding.imageView11.setOnClickListener {
         findNavController().popBackStack()
      }
      binding.rvCasts.adapter = castAdapter
      binding.rvTrailer.adapter = trailerAdapter
      binding.rvSimilar.adapter = similarAdapter
      binding.rvComment.adapter = commentAdapter
      trailerAdapter.onItemClick = { video ->
         openYoutube(requireContext(), video.key.toString())
      }

      observeData()
      viewModelDetail.getMovieDetail(args.id)
      viewModelDetail.getCast(args.id)
      viewModelDetail.getVideos(args.id)
      viewModelDetail.getSimilar(args.id)
      viewModelDetail.getComments(args.id)
      binding.rvTrailer.visibility = View.VISIBLE
      binding.rvSimilar.visibility = View.GONE
      binding.rvComment.visibility = View.GONE

      binding.imageViewAdd.setOnClickListener {

         isSelected = !isSelected

         val image = if (isSelected) R.drawable.selected else R.drawable.unselected
         binding.imageViewAdd.setImageResource(image)


            viewModelDetail.movie.value?.let { movie ->
               val movie = MyListModel(
                  title = movie.title ?: "No Title",
                  image = movie.posterPath ?: "",
                  selected = isSelected,
                  id = movie.id ?: 0

               )

               if (isSelected) {
                  viewModelDetail.addMovie(movie)
                  Toast.makeText(requireContext(), "Movie added to your list", Toast.LENGTH_SHORT)
                     .show()
                  added()
               } else {
                  viewModelDetail.deleteMovie(movie.id)
                  Toast.makeText(requireContext(), "Movie removed from your list", Toast.LENGTH_SHORT)
                     .show()
                  removed()
               }


            }
         }


      binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
         override fun onTabSelected(tab: TabLayout.Tab) {
            if (tab.position == 0) {
               binding.rvTrailer.visible()
               binding.rvSimilar.gone()
               binding.rvComment.gone()
               trailerAdapter.onItemClick = { video ->
                  openYoutube(requireContext(), video.key.toString())
               }

            }

            if (tab.position == 1) {
               binding.rvTrailer.gone()
               binding.rvSimilar.visible()
               binding.rvComment.gone()




            }

            if (tab.position == 2) {
               binding.rvTrailer.gone()
               binding.rvSimilar.gone()
               binding.rvComment.visible()
            }



         }

         override fun onTabUnselected(tab: TabLayout.Tab?) {}
         override fun onTabReselected(tab: TabLayout.Tab?) {}
      })
   }




   private fun observeData(){
      viewModelDetail.movie.observe(viewLifecycleOwner){
         binding.movie = it

      }

      viewModelDetail.cast.observe(viewLifecycleOwner){
         castAdapter.updateList(it)
      }


      viewModelDetail.videos.observe(viewLifecycleOwner){
         trailerAdapter.updateList(it)
      }


      viewModelDetail.similar.observe(viewLifecycleOwner){
         similarAdapter.updateList(it)
      }

      viewModelDetail.comments.observe(viewLifecycleOwner){
         commentAdapter.updateList(it)
      }

      viewModelDetail.error.observe(viewLifecycleOwner){
         Toast.makeText(context, it, Toast.LENGTH_LONG).show()
      }

   }

   private fun openYoutube(context: Context, youtubeKey: String) {
      val youtubeAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$youtubeKey"))
      val youtubeWebIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$youtubeKey"))

      try {
         context.startActivity(youtubeAppIntent)
      } catch (e: ActivityNotFoundException) {
         context.startActivity(youtubeWebIntent)
      }
   }



   private fun added(){
      val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
      sp.edit().putBoolean("added_${args.id}", true).apply()

   }

   private fun removed(){
      val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
      sp.edit().putBoolean("added_${args.id}", false).apply()

   }


   private fun checkSelected(){

      val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
      val isAdded = sp.getBoolean("added_${args.id}", false)


      if(isAdded){
         isSelected = true


      } else {
         isSelected = false
      }

      val image = if (isSelected) R.drawable.selected else R.drawable.unselected
      binding.imageViewAdd.setImageResource(image)



   }


}