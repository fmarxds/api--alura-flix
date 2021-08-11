package br.com.aluraflix.service

import br.com.aluraflix.dto.input.CategoriaInputDTO
import br.com.aluraflix.model.CategoriaModel
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

interface CategoriaService {

    fun listAll(pageable: Pageable): Page<CategoriaModel>

    fun listOne(id: Long): CategoriaModel

    fun createOne(categoriaInputDTO: CategoriaInputDTO): CategoriaModel

    fun updateOne(id: Long, categoriaInputDTO: CategoriaInputDTO): CategoriaModel

    fun deleteOne(id: Long)

}