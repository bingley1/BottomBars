package com.sryang.library

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@Composable
fun BottomBarsProvider.OwlBottomBar(navController: NavController, tabs: Array<CourseTabs>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: CourseTabs.FEATURED.route

    val routes = remember { CourseTabs.values().map { it.route } }
    if (currentRoute in routes) {
        NavigationBar(
            Modifier.windowInsetsBottomHeight(
                WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
            )
        ) {
            tabs.forEach { tab ->
                NavigationBarItem(
                    icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                    label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
) {
    MY_COURSES(R.string.my_courses, R.drawable.ic_grain, CoursesDestinations.MY_COURSES_ROUTE),
    FEATURED(R.string.featured, R.drawable.ic_featured, CoursesDestinations.FEATURED_ROUTE),
    SEARCH(R.string.search, R.drawable.ic_search, CoursesDestinations.SEARCH_COURSES_ROUTE)
}


/**
 * Destinations used in the ([OwlApp]).
 */
private object CoursesDestinations {
    const val FEATURED_ROUTE = "courses/featured"
    const val MY_COURSES_ROUTE = "courses/my"
    const val SEARCH_COURSES_ROUTE = "courses/search"
}

@Preview
@Composable
fun PreviewOwnBottomBar() {
    BottomBarsProvider.OwlBottomBar(
        navController = rememberNavController(), tabs =
        arrayOf(CourseTabs.MY_COURSES, CourseTabs.FEATURED, CourseTabs.SEARCH)
    )
}
