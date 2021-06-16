package com.tejeet.beets.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.tejeet.beets.R
import com.tejeet.beets.databinding.FragmentProfileBinding
import com.tejeet.beets.ui.main.viewmodel.MainViewModel
import com.tejeet.beets.utils.Constants.showStatusAndNavBar
import com.tejeet.beets.utils.ViewUtils.changeStatusBarColor
import com.tejeet.beets.utils.ViewUtils.showStatusBar
import timber.log.Timber

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val mainViewModel: MainViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        showStatusAndNavBar(requireActivity())

        binding.viewpager.apply {
//            adapter = MyFragmentStateAdapter()
            registerOnPageChangeCallback(onPageChangedCallback)
        }

        val tabConfigurationStrategy =
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                Timber.d("tab is $tab and tab position is $position")
                tab.text = getString(
                    when (position) {
                        0 -> R.string.my_videos
                        1 -> R.string.my_private_videos
                        2 -> R.string.my_liked_videos
                        else -> throw IndexOutOfBoundsException("position was $position")
                    }
                )
            }
//        TabLayoutMediator(binding.profileTabLayout, binding.viewpager, tabConfigurationStrategy)
//            .attach()

        return binding.root
    }

    private val onPageChangedCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Timber.d("Callback position is $position")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        changeStatusBarColor(requireActivity(),R.color.colorBlack)
    }

//    class MyFragmentStateAdapter(
//        private val profileWithAccountFragment: ProfileWithAccountFragment
//    ) : FragmentStateAdapter(profileWithAccountFragment) {
//
//        /*
//        If the profile Uid represents the current user, show him everything
//        If the profile user wants to show their liked videos, show the current user the first two: public and liked videos
//        Otherwise, show only the public videos
//         */
//        override fun getItemCount(): Int = when {
//            profileWithAccountFragment.args.uid == Firebase.auth.uid -> 3
//            profileWithAccountFragment.viewModel.profileUser.value?.showLikedVideos == true -> 2
//            else -> 1
//        }
//
//        override fun createFragment(position: Int): Fragment {
//            val uid = profileWithAccountFragment.args.uid
//            return when (position) {
//                0 -> ProfileVideoTab.getInstance(uid, VideoType.PUBLIC)
//                1 -> ProfileVideoTab.getInstance(uid, VideoType.LIKED)
//                2 -> ProfileVideoTab.getInstance(uid, VideoType.PRIVATE)
//                else -> throw ArrayIndexOutOfBoundsException(position)
//            }
//        }
//    }

}