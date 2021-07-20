package br.com.aluraflix.repository

import br.com.aluraflix.model.VideoModel
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface VideoRepository: CrudRepository<VideoModel, Long> {

    override fun findAll(): List<VideoModel>

}