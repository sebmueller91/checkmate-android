package com.dgs.checkmatecore

import checkmate.CheckmateCore
import checkmate.model.Game
import checkmate.model.GameState
import checkmate.model.GameStatus
import checkmate.model.Move
import checkmate.model.Position
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update

internal class GameStateProviderImpl @Inject constructor(
    private val checkmateCore: CheckmateCore
) : GameStateProvider {
    private var game: Game = checkmateCore.getInitialGame()
    private var curGameStateIndex = 0

    private val _gameFlow = MutableStateFlow(game.gameStates.last())
    override val gameFlow: Flow<GameState>
        get() = _gameFlow.asStateFlow().filterNotNull()

    override val canUndoMoveFlow: Flow<Boolean> = game.gameStates.

    override val canRedoMoveFlow: Flow<Boolean>
        get() = TODO("Not yet implemented")
    override val gameStatusFlow: Flow<GameStatus>
        get() = TODO("Not yet implemented")

    init {
        _gameFlow.update { game.gameStates.last() }
    }

    override fun getMoves(pos: Position): List<Move> =
        checkmateCore.getValidMoves(game.gameStates.get(curGameStateIndex), pos)

    override fun executeMove(move: Move) {
        TODO("Not yet implemented")
    }

}