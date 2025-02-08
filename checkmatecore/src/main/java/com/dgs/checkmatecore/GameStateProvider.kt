package com.dgs.checkmatecore

import checkmate.model.GameState
import checkmate.model.GameStatus
import checkmate.model.Move
import checkmate.model.Position
import kotlinx.coroutines.flow.Flow

interface GameStateProvider {
    val gameFlow: Flow<GameState>
    val canUndoMoveFlow: Flow<Boolean>
    val canRedoMoveFlow: Flow<Boolean>
    val gameStatusFlow: Flow<GameStatus>
    fun getMoves(pos: Position): List<Move>
    fun executeMove(move: Move)
}