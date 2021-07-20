package br.com.aluraflix.dto.output

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ErrorOutputDTO(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val timeStamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String? = "Desculpe, ocorreu um erro.",
    val path: String,
)
