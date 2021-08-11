package br.com.aluraflix.service

import br.com.aluraflix.dto.input.VideoInputDTO
import br.com.aluraflix.model.VideoModel

interface VideoService {

    fun listAll(busca: String): Collection<VideoModel>

    fun listOne(id: Long): VideoModel

    fun listAllByCategoriaId(id: Long): Collection<VideoModel>

    fun createOne(videoInputDTO: VideoInputDTO): VideoModel

    fun updateOne(id: Long, videoInputDTO: VideoInputDTO): VideoModel

    fun deleteOne(id: Long)

}