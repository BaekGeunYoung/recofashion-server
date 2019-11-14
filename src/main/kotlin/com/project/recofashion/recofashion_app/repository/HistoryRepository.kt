package com.project.recofashion.recofashion_app.repository

import com.project.recofashion.recofashion_app.entity.history.History
import org.springframework.data.jpa.repository.JpaRepository

interface HistoryRepository : JpaRepository<History, Long>