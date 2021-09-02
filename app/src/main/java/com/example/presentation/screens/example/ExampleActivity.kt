package com.example.presentation.screens.example

import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.R
import com.example.domain.common.Failure
import com.example.domain.entities.ExampleUserEntity
import com.example.domain.entities.MetaDataParam
import com.example.domain.usecase.interactor.onboarding.ExampleInteractor
import com.example.domain.usecase.interactor.onboarding.InitialDataInteractor
import com.example.presentation.common.ViewMvcFactory
import com.example.presentation.screens.common.controllers.BaseActivity
import javax.inject.Inject

class ExampleActivity : BaseActivity(), ExampleViewMvc.Listener {

    companion object {
        const val DUMMY_USER_ID = "123456"
    }

    private lateinit var mViewMvc: ExampleViewMvc

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mExampleInteractor: ExampleInteractor

    @Inject
    lateinit var mInitialInteractor: InitialDataInteractor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        mViewMvc = mViewMvcFactory.getExampleViewMvc(null)
        mExampleInteractor.storeDummyUser(ExampleUserEntity(DUMMY_USER_ID, "Raj"))
        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)

    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unRegisterListeners(this)
    }

    override fun getUserClicked() {
        /*val entity = mExampleInteractor.getDummyUserID()
        mInitialInteractor.getMetaData(MetaDataParam(emptyList()))
        Toast.makeText(this, "User ID: ${entity.mUserID} UserName: ${entity.mUserName}", Toast.LENGTH_LONG).show()*/
        launch {
            val response = mInitialInteractor.getMetaData(MetaDataParam(emptyList()))
            withContext(Dispatchers.Main) {
                // Handle response and pass to Views.
                response.either({
                    when (it) {
                        is Failure.NetworkConnection -> Log.e("TAG","Error ->  ${getString(R.string.failure_network_connection)}")
                        is Failure.ServerError -> Log.e("TAG", "Error ->  ${getString(R.string.failure_server_error)}")
                        else -> Log.e("TAG", "Error ->  Something Went Wrong")
                    }
                }, {
                    Log.d("TAG", "Success Data ${it.mPrivacyURL}")
                })
            }
        }
    }
}
