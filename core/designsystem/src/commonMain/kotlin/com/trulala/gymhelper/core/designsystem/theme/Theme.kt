package com.trulala.gymhelper.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val GymHelperDarkColors = darkColorScheme(
    primary = Purple200,
    onPrimary = OnPurple,
    primaryContainer = PurpleContainer,
    onPrimaryContainer = Purple200,
    secondary = Purple400,
    tertiary = Purple600,
    background = Surface,
    onBackground = OnSurface,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceContainerHigh,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainer = SurfaceContainer,
    outline = Outline,
    error = ErrorRed,
)

/**
 * Приложение сознательно тёмное: светлой схемы нет и не планируется,
 * поэтому [darkColorScheme] отдаётся напрямую, без isSystemInDarkTheme.
 */
@Composable
fun GymHelperTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GymHelperDarkColors,
        typography = GymHelperTypography,
        content = content,
    )
}
