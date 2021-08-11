package br.com.aluraflix.service.impl

import br.com.aluraflix.dto.input.VideoInputDTO
import br.com.aluraflix.exception.ItemNotFoundException
import br.com.aluraflix.mapper.toModel
import br.com.aluraflix.model.VideoModel
import br.com.aluraflix.repository.VideoRepository
import br.com.aluraflix.service.CategoriaService
import br.com.aluraflix.service.VideoService
import javax.inject.Singleton

@Singleton
class VideoServiceImpl(
    private val videoRepository: VideoRepository,
    private val categoriaService: CategoriaService,
) : VideoService {

    override fun listAll(busca: String): Collection<VideoModel> {
        return if (busca.isBlank()) videoRepository.findAll() else videoRepository.findAllByTituloIlike(busca)
    }

    override fun listOne(id: Long): VideoModel {
        return videoRepository.findById(id).orElseThrow { ItemNotFoundException("Video não encontrado!") }
    }

    override fun listAllByCategoriaId(id: Long): Collection<VideoModel> {
        val categoria = categoriaService.listOne(id)
        return videoRepository.findAllByCategoriaId(categoria.id!!)
    }

    override fun createOne(videoInputDTO: VideoInputDTO): VideoModel {
        val categoriaModel = categoriaService.listOne(videoInputDTO.categoriaId)
        return videoRepository.save(videoInputDTO.toModel(categoriaModel))
    }

    override fun updateOne(id: Long, videoInputDTO: VideoInputDTO): VideoModel {

        if (!videoRepository.existsById(id)) throw ItemNotFoundException()

        val videoModel = VideoModel(
            id = id,
            titulo = videoInputDTO.titulo,
            descricao = videoInputDTO.descricao,
            url = videoInputDTO.url,
            categoria = categoriaService.listOne(videoInputDTO.categoriaId),
        )

        return videoRepository.update(videoModel)

    }

    override fun deleteOne(id: Long) {
        if (!videoRepository.existsById(id)) throw ItemNotFoundException()
        videoRepository.deleteById(id)
    }

}