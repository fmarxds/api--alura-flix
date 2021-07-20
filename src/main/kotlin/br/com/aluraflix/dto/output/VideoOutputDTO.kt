package br.com.aluraflix.dto.output

import com.fasterxml.jackson.annotation.JsonProperty

data class VideoOutputDTO(

    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("titulo")
    val titulo: String,

    @JsonProperty("descricao")
    val descricao: String,

    @JsonProperty("url")
    val url: String,

)
