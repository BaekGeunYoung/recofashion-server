package com.project.recofashion.recofashion_app.service

import com.project.recofashion.recofashion_app.entity.history.History

interface HistoryService {
    fun getMyHistories(username: String): List<History>

    fun addHistory(history: History): History
}