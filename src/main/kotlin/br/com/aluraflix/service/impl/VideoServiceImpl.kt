package br.com.aluraflix.service.impl

import br.com.aluraflix.dto.input.VideoInputDTO
import br.com.aluraflix.exception.ItemNotFoundException
import br.com.aluraflix.mapper.toModel
import br.com.aluraflix.model.VideoModel
import br.com.aluraflix.repository.VideoRepository
import br.com.aluraflix.service.VideoService
import javax.inject.Singleton

@Singleton
class VideoServiceImpl(
    private val videoRepository: VideoRepository,
) : VideoService {

    override fun listAll(): Collection<VideoModel> {
        return videoRepository.findAll()
    }

    override fun listOne(id: Long): VideoModel {
        return videoRepository.findById(id).orElseThrow { ItemNotFoundException() }
    }

    override fun createOne(videoInputDTO: VideoInputDTO): VideoModel {
        return videoRepository.save(videoInputDTO.toModel())
    }

    override fun updateOne(id: Long, videoInputDTO: VideoInputDTO): VideoModel {
        if (!videoRepository.existsById(id)) throw ItemNotFoundException()
        return videoRepository.update(VideoModel(id, videoInputDTO.titulo, videoInputDTO.descricao, videoInputDTO.url))
    }

    override fun deleteOne(id: Long) {
        if (!videoRepository.existsById(id)) throw ItemNotFoundException()
        videoRepository.deleteById(id)
    }

}