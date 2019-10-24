package kz.simplecode.sampleandroidproject.ui.tabs

import kz.simplecode.sampleandroidproject.ui.SampleScreens
import ru.terrakok.cicerone.Screen
import simple.code.base.ui.BaseTabContainerFragment


class MoviesTabFragment : BaseTabContainerFragment() {
    override val mainScreen: Screen = SampleScreens.MoviesScreen()
}