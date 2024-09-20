package com.solodev.codingchallenge.data.manager

import android.content.Context
import com.solodev.codingchallenge.presentation.navgraph.Route
import com.solodev.codingchallenge.utils.SharedPreferenceDelegate
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RouteManager @Inject constructor(@ApplicationContext context: Context) {
    var lastRoute: String by SharedPreferenceDelegate(context, "last_route", Route.MoviesNavigation.route)
}