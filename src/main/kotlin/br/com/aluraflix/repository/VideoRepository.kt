package br.com.aluraflix.repository

import br.com.aluraflix.model.VideoModel
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.CrudRepository

@Repository
interface VideoRepository: CrudRepository<VideoModel, Long> {

    fun findAll(pageable: Pageable): Page<VideoModel>

    @Query(
        value = "select v from VideoModel v where lower(v.titulo) like concat('%', lower(:busca), '%')",
        countQuery = "select count(v) from VideoModel v where lower(v.titulo) like concat('%', lower(:busca), '%')",
    )
    fun findAllByTituloIlike(busca: String, pageable: Pageable): Page<VideoModel>

    fun findAllByCategoriaId(categoriaId: Long): List<VideoModel>

}