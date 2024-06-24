package com.sryang.library

import YoutubeBottomBar
import androidx.compose.runtime.Composable

object BottomBarsProvider

sealed class BottomAppBarTypes(val name: String, val bottomBar: @Composable (() -> Unit)?) {
    object None : BottomAppBarTypes("None", null)
    object Youtube : BottomAppBarTypes("Youtube", { BottomBarsProvider.YoutubeBottomBar() })
    object Owl : BottomAppBarTypes("Owl", { BottomBarsProvider.OwlBottomBar() })
    object Survey : BottomAppBarTypes("Survey", { BottomBarsProvider.SurveyBottomBar() })
    object JetSnack : BottomAppBarTypes("JetSnack", { BottomBarsProvider.JetsnackBottomBar() })

}

val bottomAppBarTypeList = listOf(
    BottomAppBarTypes.None,
    BottomAppBarTypes.Youtube,
    BottomAppBarTypes.Owl,
    BottomAppBarTypes.Survey,
    BottomAppBarTypes.JetSnack
)