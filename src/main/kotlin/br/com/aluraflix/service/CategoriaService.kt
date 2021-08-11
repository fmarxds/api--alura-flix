package br.com.aluraflix.service

import br.com.aluraflix.dto.input.CategoriaInputDTO
import br.com.aluraflix.model.CategoriaModel

interface CategoriaService {

    fun listAll(): Collection<CategoriaModel>

    fun listOne(id: Long): CategoriaModel

    fun createOne(categoriaInputDTO: CategoriaInputDTO): CategoriaModel

    fun updateOne(id: Long, categoriaInputDTO: CategoriaInputDTO): CategoriaModel

    fun deleteOne(id: Long)

}