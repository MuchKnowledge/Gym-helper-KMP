package com.trulala.gymhelper.shared.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

/**
 * Корень навигации. Компонент ничего не знает про Compose — UI подписывается на [stack]
 * и рисует текущего ребёнка. Пока в стеке одна заглушка: реальные фичи приедут в Фазе 1.
 */
interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed interface Child {
        data object Placeholder : Child
    }
}
