package com.project.recofashion.recofashion_app.controller.v1.api

import com.project.recofashion.recofashion_app.controller.v1.request.AddHistoryRequest
import com.project.recofashion.recofashion_app.entity.history.History
import com.project.recofashion.recofashion_app.service.HistoryService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/histories")
class HistoryController(
        private val historyService: HistoryService
) {
    @PostMapping("/")
    fun addHistory(
            @RequestBody @Valid addHistoryRequest: AddHistoryRequest,
            @AuthenticationPrincipal userDetails: UserDetails
    ): History {
        val history = History(null, userDetails.username, addHistoryRequest.topColor, addHistoryRequest.pantscolor, LocalDate.now())
        return historyService.addHistory(history)
    }
}