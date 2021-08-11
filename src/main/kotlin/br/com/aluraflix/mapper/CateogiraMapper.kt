package br.com.aluraflix.mapper

import br.com.aluraflix.dto.input.CategoriaInputDTO
import br.com.aluraflix.dto.output.CategoriaOutputDTO
import br.com.aluraflix.model.CategoriaModel

fun CategoriaInputDTO.toModel(): CategoriaModel {
    return CategoriaModel(
        titulo = this.titulo,
        cor = this.cor,
    )
}

fun CategoriaModel.toOutputDTO(): CategoriaOutputDTO {
    return CategoriaOutputDTO(
        id = this.id,
        titulo = this.titulo,
        cor = this.cor,
    )
}