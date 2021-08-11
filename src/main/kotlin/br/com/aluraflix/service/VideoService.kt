package br.com.aluraflix.service

import br.com.aluraflix.dto.input.VideoInputDTO
import br.com.aluraflix.model.VideoModel
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

interface VideoService {

    fun listAll(busca: String, pageable: Pageable): Page<VideoModel>

    fun listOne(id: Long): VideoModel

    fun listAllByCategoriaId(id: Long): Collection<VideoModel>

    fun createOne(videoInputDTO: VideoInputDTO): VideoModel

    fun updateOne(id: Long, videoInputDTO: VideoInputDTO): VideoModel

    fun deleteOne(id: Long)

}