package com.epay.codingchallenge.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Created By Rafiqul Hasan
 */
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run {
        navigate(direction)
    }
}