package br.com.aluraflix.repository

import br.com.aluraflix.model.VideoModel
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface VideoRepository: CrudRepository<VideoModel, Long> {

    override fun findAll(): List<VideoModel>

    @Query("select v from VideoModel v where lower(v.titulo) like concat('%', lower(:busca), '%')")
    fun findAllByTituloIlike(busca: String): List<VideoModel>

    fun findAllByCategoriaId(categoriaId: Long): List<VideoModel>

}