package com.dgs.checkmatecore

import checkmate.model.GameState
import checkmate.model.GameStatus
import checkmate.model.Move
import checkmate.model.Position
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface GameStateProvider {
    val gameStateFlow: StateFlow<GameState>
    val canUndoMoveFlow: StateFlow<Boolean>
    val canRedoMoveFlow: StateFlow<Boolean>
    val gameStatusFlow: StateFlow<GameStatus>
    fun getMoves(pos: Position): List<Move>
    fun executeMove(move: Move)
}