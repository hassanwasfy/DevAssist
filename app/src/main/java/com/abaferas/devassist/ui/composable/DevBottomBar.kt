package com.abaferas.devassist.ui.composable

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.navigation.LocalNavController
import com.abaferas.devassist.ui.navigation.NavigationDestination
import com.abaferas.devassist.ui.screen.ai.navigateToAiChat
import com.abaferas.devassist.ui.screen.books.navigateToBook
import com.abaferas.devassist.ui.screen.home.navigateToHome
import com.abaferas.devassist.ui.screen.profile.navigateToProfile
import com.abaferas.devassist.ui.screen.settings.navigateToSettings
import com.abaferas.devassist.ui.theme.Tajawal
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_backgroundColor
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_dividerColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_primaryColor


@Composable
fun DevBottomBar() {
    val controller = LocalNavController.current
    val currentScreen = currentRoute(navController = controller)
    if (currentScreen != NavigationDestination.ScreenSplash.route &&
        currentScreen != NavigationDestination.ScreenLogin.route &&
        currentScreen != NavigationDestination.ScreenSignUp.route
    ) {
        BottomAppBar(
            containerColor = color_primaryColor,
            tonalElevation = 12.dp
        ) {
            AddDevBottomItem(
                icon = Icons.Outlined.AccountCircle, label = R.string.bottom_Profile,
                isCurrent = currentScreen == NavigationDestination.ScreenProfile.route
            ) {
                if (currentScreen != NavigationDestination.ScreenProfile.route) {
                    controller.navigateToProfile()
                }
            }
            AddDevBottomItem(
                icon = Icons.Outlined.Chat, label = R.string.bottom_Ai,
                isCurrent = currentScreen == NavigationDestination.ScreenAiChat.route
            ) {
                if (currentScreen != NavigationDestination.ScreenAiChat.route) {
                    controller.navigateToAiChat()
                }
            }
            AddDevBottomItem(
                icon = Icons.Outlined.Home, label = R.string.bottom_home,
                isCurrent = currentScreen == NavigationDestination.ScreenHome.route
            ) {
                if (currentScreen != NavigationDestination.ScreenHome.route) {
                    controller.navigateToHome()
                }
            }
            AddDevBottomItem(
                icon = Icons.Outlined.MenuBook, label = R.string.bottom_Books,
                isCurrent = currentScreen == NavigationDestination.ScreenBook.route
            ) {
                if (currentScreen != NavigationDestination.ScreenBook.route) {
                    controller.navigateToBook()
                }
            }
            AddDevBottomItem(
                icon = Icons.Outlined.Settings, label = R.string.bottom_Settings,
                isCurrent = currentScreen == NavigationDestination.ScreenSettings.route
            ) {
                if (currentScreen != NavigationDestination.ScreenSettings.route) {
                    controller.navigateToSettings()
                }
            }
        }
    }
}

@Composable
fun RowScope.AddDevBottomItem(
    icon: ImageVector,
    @StringRes label: Int,
    isCurrent: Boolean,
    onClick: () -> Unit
) {
    val currentColor by animateColorAsState(
        targetValue =
        if (isCurrent)
            color_darkPrimaryColor
        else
            color_backgroundColor,
        label = "",
        animationSpec = tween(
            durationMillis = 300
        )
    )

    NavigationBarItem(
        selected = isCurrent, alwaysShowLabel = true,
        enabled = true,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = currentColor
            )
        },
        label = {
            DevLabel(
                modifier = Modifier,
                text = stringResource(id = label),
                fontSize = 14,
                fontFamily = Tajawal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = currentColor
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = color_darkPrimaryColor,
            unselectedIconColor = color_lightPrimaryColor,
            selectedTextColor = color_darkPrimaryColor,
            unselectedTextColor = color_lightPrimaryColor,
            indicatorColor = color_primaryColor,
        )
    )

}

@Composable
fun currentRoute(navController: NavHostController): String? =
    navController.currentBackStackEntryAsState().value?.destination?.route