package com.freeworldone.honorpay.ui.base.extensions

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T = ViewModelProviders.of(this).get(T::class.java)