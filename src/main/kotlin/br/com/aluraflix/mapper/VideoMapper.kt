package br.com.aluraflix.mapper

import br.com.aluraflix.dto.input.VideoInputDTO
import br.com.aluraflix.dto.output.VideoOutputDTO
import br.com.aluraflix.model.VideoModel

fun VideoInputDTO.toModel(): VideoModel {
    return VideoModel(
        titulo = this.titulo,
        descricao = this.descricao,
        url = this.url,
    )
}

fun VideoModel.toOutputDTO(): VideoOutputDTO {
    return VideoOutputDTO(
        id = this.id,
        titulo = this.titulo,
        descricao = this.descricao,
        url = this.url
    )
}