package com.dgs.checkmatecore

import checkmate.CheckmateCore
import checkmate.model.Game
import checkmate.model.GameState
import checkmate.model.GameStatus
import checkmate.model.Move
import checkmate.model.Position
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class GameStateProviderImpl @Inject constructor(
    private val checkmateCore: CheckmateCore,
    private val scope: CoroutineScope
) : GameStateProvider {
    private var _gameFlow = MutableStateFlow(checkmateCore.getInitialGame())
    private var _curGameStateIndexFlow = MutableStateFlow(0)

    override val gameStateFlow: StateFlow<GameState> =
        combine(_gameFlow, _curGameStateIndexFlow) { game, index ->
            game.gameStates[index]
        }.stateIn(scope, SharingStarted.Eagerly, _gameFlow.value.gameStates[0])

    override val canUndoMoveFlow: StateFlow<Boolean> =
        _curGameStateIndexFlow.map { it > 0 }.stateIn(scope, SharingStarted.Eagerly, false)

    override val canRedoMoveFlow: StateFlow<Boolean> =
        combine(_gameFlow, _curGameStateIndexFlow) { game, index ->
            index in 0..<game.gameStates.size - 1
        }.stateIn(scope, SharingStarted.Eagerly, true)

    override val gameStatusFlow: StateFlow<GameStatus> =
        combine(_gameFlow, _curGameStateIndexFlow) { game, index ->
            game.gameStates[index].gameStatus
        }.stateIn(scope, SharingStarted.Eagerly, _gameFlow.value.gameStates[0].gameStatus)

    override fun getMoves(pos: Position): List<Move> =
        checkmateCore.getValidMoves(
            _gameFlow.value.gameStates.get(_curGameStateIndexFlow.value),
            pos
        )

    override fun executeMove(move: Move) {
        checkmateCore.executeMove(move, _gameFlow.value, _curGameStateIndexFlow.value)
    }
}