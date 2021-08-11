package br.com.aluraflix.dto.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
@JsonIgnoreProperties(ignoreUnknown = true)
data class CategoriaInputDTO(

    @JsonProperty("titulo")
    @field:Size(min = 5, max = 100, message = "O campo 'titulo' deve ter entre 5 e 100 caracteres")
    @field:NotBlank(message = "O campo 'titulo' deve ser informado")
    val titulo: String,

    @JsonProperty("cor")
    @field:Size(min = 7, max = 7, message = "O campo 'cor' deve ter 7 caracteres")
    @field:NotBlank(message = "O campo 'cor' deve ser informado")
    val cor: String,

)
