package kz.simplecode.sampleandroidproject.ui.tabs

import kz.simplecode.sampleandroidproject.ui.SampleScreens
import ru.terrakok.cicerone.Screen
import simple.code.base.ui.BaseTabContainerFragment


class BooksTabFragment : BaseTabContainerFragment() {
    override val mainScreen: Screen = SampleScreens.MoviesScreen()
}