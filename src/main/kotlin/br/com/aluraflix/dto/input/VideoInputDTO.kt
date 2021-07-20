package br.com.aluraflix.dto.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoInputDTO(

    @JsonProperty("titulo")
    @field:Size(min = 5, max = 150, message = "O campo 'titulo' deve ter entre 5 e 150 caracteres")
    @field:NotBlank(message = "O campo 'titulo' deve ser informado")
    val titulo: String,

    @JsonProperty("descricao")
    @field:Size(min = 5, max = 255, message = "O campo 'titulo' deve ter entre 5 e 255 caracteres")
    @field:NotBlank(message = "O campo 'descricao' deve ser informado")
    val descricao: String,

    @JsonProperty("url")
    @field:Size(min = 10, max = 255, message = "O campo 'titulo' deve ter entre 10 e 255 caracteres")
    @field:NotBlank(message = "O campo 'url' deve ser informado")
    val url: String,

)
