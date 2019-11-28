package com.project.recofashion.recofashion_app.service.impl

import com.project.recofashion.recofashion_app.entity.history.History
import com.project.recofashion.recofashion_app.repository.ColorRepository
import com.project.recofashion.recofashion_app.repository.HistoryRepository
import com.project.recofashion.recofashion_app.service.HistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HistoryServiceImpl(
        @Autowired
        private val historyRepository: HistoryRepository,
        @Autowired
        private val colorRepository: ColorRepository
): HistoryService {
    override fun getMyHistories(username: String): List<History> {
        val histories = historyRepository.findByUsername(username)

        histories?: throw Exception()

        return histories
    }

    override fun addHistory(history: History): History {
        if(!colorRepository.existsById(history.topColor)) colorRepository.save(history.topColor)
        if(!colorRepository.existsById(history.pantsColor)) colorRepository.save(history.pantsColor)

        return historyRepository.save(history)
    }
}