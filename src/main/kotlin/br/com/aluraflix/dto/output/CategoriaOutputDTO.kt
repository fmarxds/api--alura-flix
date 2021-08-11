package br.com.aluraflix.dto.output

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoriaOutputDTO(

    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("titulo")
    val titulo: String,

    @JsonProperty("cor")
    val cor: String,

)
