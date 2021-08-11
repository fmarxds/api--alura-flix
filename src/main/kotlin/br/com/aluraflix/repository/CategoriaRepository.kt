package br.com.aluraflix.repository

import br.com.aluraflix.model.CategoriaModel
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.CrudRepository

@Repository
interface CategoriaRepository : CrudRepository<CategoriaModel, Long> {

    fun findAll(pageable: Pageable): Page<CategoriaModel>

}