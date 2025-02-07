package com.dgs.checkmatecore

import checkmate.CheckmateCore
import checkmate.model.Game
import checkmate.model.Move
import checkmate.model.Position
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

internal class CheckmateCoreProviderImpl @Inject constructor(
    private val checkmateCore: CheckmateCore
): CheckmateCoreProvider {

    private val _gameFlow = MutableStateFlow(checkmateCore.getInitialGame())
    override val gameFlow: Flow<Game>
        get() = _gameFlow.asStateFlow().filterNotNull()

    fun getMoves(pos: Position): List<Move> = checkmateCore.getValidMoves(_gameFlow.value.gameStates.last(), pos)
}