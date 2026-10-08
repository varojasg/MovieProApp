package pe.edu.upc.movieproapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val LightColorScheme = lightColorScheme(
    primary = Wine,
    onPrimary = Cream,
    primaryContainer = WarmTaupe,
    onPrimaryContainer = NearBlack,
    secondary = NearBlack,
    onSecondary = Cream,
    secondaryContainer = Cream,
    onSecondaryContainer = NearBlack,
    tertiary = RedWine,
    onTertiary = Cream,
    background = Cream,
    onBackground = NearBlack,
    surface = Cream,
    onSurface = NearBlack,
    surfaceVariant = WarmTaupe,
    onSurfaceVariant = NearBlack,
    surfaceContainer = Cream,
    surfaceContainerLow = Cream,
    surfaceContainerHigh = WarmTaupe,
    outline = Wine,
    error = Wine,
    onError = Cream
)

private val DarkColorScheme = darkColorScheme(
    primary = Cream,
    onPrimary = Wine,
    primaryContainer = Wine,
    onPrimaryContainer = Cream,
    secondary = WarmTaupe,
    onSecondary = NearBlack,
    secondaryContainer = Wine,
    onSecondaryContainer = Cream,
    tertiary = WarmTaupe,
    onTertiary = NearBlack,
    background = NearBlack,
    onBackground = Cream,
    surface = NearBlack,
    onSurface = Cream,
    surfaceVariant = Wine,
    onSurfaceVariant = Cream,
    surfaceContainer = NearBlack,
    surfaceContainerLow = NearBlack,
    surfaceContainerHigh = Wine,
    outline = WarmTaupe,
    error = WarmTaupe,
    onError = NearBlack
)

@Composable
fun MovieProAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
