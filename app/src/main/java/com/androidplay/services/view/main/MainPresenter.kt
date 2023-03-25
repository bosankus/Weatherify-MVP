package com.androidplay.services.view.main

import com.androidplay.services.BaseContract
import com.androidplay.services.model.model.Weather

/**
 * Author: Ankush Bose
 * Company: Androidplay.in
 * Created on: 12,March,2022
 */
class MainPresenter(private val interactor: BaseContract.Interactor) : BaseContract.Presenter,
    BaseContract.Interactor.OnFinishedListener {

    private var view: BaseContract.View? = null

    override fun attach(view: BaseContract.View) {
        this.view = view
    }

    override fun getData(areaName: String) {
        view?.apply {
            hideKeyboard()
            showProgress()
        }
        interactor.requestData(areaName, this)
    }

    override fun onSuccess(weather: Weather) {
        view?.apply {
            setSuccessData(weather)
            saveDataInDataStore(weather)
            hideProgress()
        }
    }

    override fun onFailed(error: String) {
        view?.apply {
            setFailureData(error)
            hideProgress()
        }
    }

    override fun cleanUp() {
        this.view = null
        interactor.cleanUp()
    }
}