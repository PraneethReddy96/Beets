package com.tejeet.beets.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tejeet.beets.R
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.beets.data.modelDTO.StoriesData
import com.tejeet.beets.databinding.FragmentProfileBinding
import com.tejeet.beets.model.ResultData
import com.tejeet.beets.ui.activities.main.viewmodel.MainViewModel
import com.tejeet.beets.ui.fragments.profile.adapter.MyVideosAdapter
import com.tejeet.beets.ui.fragments.profile.viewmodel.ProfileViewModel
import com.tejeet.beets.utils.Constants.showStatusAndNavBar
import com.tejeet.beets.utils.ResUtils
import com.tejeet.beets.utils.ViewUtils
import com.tejeet.beets.utils.ViewUtils.changeStatusBarColor
import com.tejeet.beets.utils.ViewUtils.showStatusBar
import com.tejeet.beets.utils.loadGlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class ProfileFragment : Fragment(),MyVideosClickListener {

    private val TAG = "tag"
    lateinit var myVideosAdapter: MyVideosAdapter
    val dataList: MutableList<StoriesData> = mutableListOf()

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code:Int=123
    var mAuth= FirebaseAuth.getInstance()


    private var _binding: FragmentProfileBinding? = null
    private val mainViewModel: MainViewModel by viewModels()
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.let { AppPreferences.init(it.baseContext) }
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setRecyclerView()

        showStatusAndNavBar(requireActivity())


        return binding.root
    }

    private fun setRecyclerView() {
        myVideosAdapter = MyVideosAdapter(dataList,this)
        binding.myVideosRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        binding.myVideosRecyclerView.adapter = myVideosAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (AppPreferences.isLoggedIn.equals("YES")){

            mainViewModel.getDataList().observe(viewLifecycleOwner, Observer {value->
                when(value) {
                    is ResultData.Loading -> {
                        binding.lottieLoaderAnimation.visibility = View.VISIBLE
                        binding.lottieNoInternetConnection.visibility = View.GONE

                    }
                    is ResultData.Success -> {

                        if (!value.data.isNullOrEmpty()) {
                            val dataList = value.data
                            myVideosAdapter.setData(dataList)
                            binding.lottieLoaderAnimation.visibility = View.GONE
                            binding.lottieNoInternetConnection.visibility = View.GONE
                        }
                    }
                    is ResultData.Exception ->{
                        binding.lottieLoaderAnimation.visibility = View.GONE
                        binding.lottieNoInternetConnection.visibility = View.GONE
                        ResUtils.showSnackBar(requireView(), value.nothing.toString())
                        ViewUtils.changeStatusBarColor(requireActivity(), R.color.colorBlack)
                    }
                }
            })
        }

        mAuth = FirebaseAuth.getInstance();

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = context?.let { it1 -> GoogleSignIn.getClient(it1,gso) }!!
        mAuth = FirebaseAuth.getInstance()

        Log.d(TAG, "Loggin status is ${AppPreferences.isLoggedIn}")

        if (AppPreferences.isLoggedIn.equals("YES")) {
            binding.layoutLoggedIn.visibility = View.VISIBLE
            binding.btnSignIn.visibility = View.GONE

            binding.userTag.text = "@${AppPreferences.userName.toString()}"

        } else {
            binding.layoutLoggedIn.visibility = View.GONE
            binding.btnSignIn.visibility = View.VISIBLE
        }

        binding.btnSignIn.setOnClickListener {

            Log.d(TAG, "Login with Google Clicked")
            signInGoogle()

        }

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        Log.d(TAG, "User Name is ${currentUser?.displayName}")
        Log.d(TAG, "Profile URL : ${currentUser?.photoUrl}")
        Log.d(TAG, "Mobile is : ${currentUser?.phoneNumber}")
        Timber.d(" Inn On Start Method ")

        loadGlideImage(binding.userPhoto, currentUser?.photoUrl.toString())

        CoroutineScope(Dispatchers.Main).launch {
            val res = viewModel.updateFirebaseToken(AppPreferences.userEmail.toString(), AppPreferences.userFirebaseToken.toString())
            Log.d(TAG, "Response is ${res?.message}")
        }


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private  fun signInGoogle(){

        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,Req_Code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==Req_Code){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)

                Log.d(TAG, "Hi ${account.displayName} Welcome to Beets")
                Toast.makeText(context, "Hi ${account.displayName} Welcome to Beets", Toast.LENGTH_SHORT).show()

                binding.layoutLoggedIn.visibility = View.VISIBLE
                binding.btnSignIn.visibility = View.GONE


                Log.d(TAG, "User Details Are : ${account.displayName}")
                Log.d(TAG, "Profile URL : ${account.photoUrl}")
                Log.d(TAG, "User Details Are : ${account.email}")

                loadGlideImage(binding.userPhoto, account?.photoUrl.toString())

                AppPreferences.setUser(account.displayName.toString(),account.givenName.toString(), account.email.toString(), account.photoUrl.toString())
                AppPreferences.isLoggedIn = "YES"

                CoroutineScope(Dispatchers.IO).launch {
                    var response = viewModel.signupUserAccount(AppPreferences.userDisplayName.toString(), AppPreferences.userName.toString(),AppPreferences.userEmail.toString(), AppPreferences.userProfile.toString(), AppPreferences.userFirebaseToken.toString())
                    Log.d(TAG, "Response is ${response?.message}")
                    AppPreferences.userID = response?.userID?.toInt()
                }

                CoroutineScope(Dispatchers.IO).launch {
                    val res = viewModel.updateFirebaseToken(AppPreferences.userEmail.toString(), AppPreferences.userFirebaseToken.toString())
                    Log.d(TAG, "Response is ${res?.message}")
                }

            }
        } catch (e: ApiException){
            Log.d(TAG, "handleResult: ${e.toString()}")

        }
    }
    // UpdateUI() function - this is where we specify what UI updation are needed after google signin has taken place.
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Task Successful")

            }
        }
    }

    override fun onResume() {
        super.onResume()
        showStatusBar(requireActivity())
        changeStatusBarColor(requireActivity(),R.color.colorBlack)
    }

    override fun onItemClicked(storiesData: StoriesData) {
        findNavController().navigate(
            ProfileFragmentDirections.actionNavigationMeToStoryViewFragment(storiesData)
        )
    }


}