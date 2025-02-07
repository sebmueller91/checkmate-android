package com.dgs.checkmatecore

import checkmate.model.Game
import kotlinx.coroutines.flow.Flow

interface CheckmateCoreProvider {
    val gameFlow: Flow<Game>
}