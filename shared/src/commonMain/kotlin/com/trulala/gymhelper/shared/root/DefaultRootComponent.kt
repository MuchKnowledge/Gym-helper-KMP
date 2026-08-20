package com.trulala.gymhelper.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Placeholder,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    override fun onBackClicked() {
        navigation.pop()
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child = when (config) {
        Config.Placeholder -> RootComponent.Child.Placeholder
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Placeholder : Config
    }
}
