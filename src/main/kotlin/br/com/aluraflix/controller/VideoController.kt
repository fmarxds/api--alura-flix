package br.com.aluraflix.controller

import br.com.aluraflix.dto.input.VideoInputDTO
import br.com.aluraflix.dto.output.VideoOutputDTO
import br.com.aluraflix.mapper.toOutputDTO
import br.com.aluraflix.service.VideoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller(
    value = "/videos",
    produces = [MediaType.APPLICATION_JSON],
    consumes = [MediaType.APPLICATION_JSON],
)
class VideoController(
    private val videoService: VideoService,
) {

    @Get
    fun listAll(
        @QueryValue("search") busca: String = "",
    ): HttpResponse<List<VideoOutputDTO>> {
        return HttpResponse.ok(videoService.listAll(busca).map { it.toOutputDTO() })
    }

    @Get("/{id}")
    fun listOne(@PathVariable id: Long): HttpResponse<VideoOutputDTO> {
        return HttpResponse.ok(videoService.listOne(id).toOutputDTO())
    }

    @Post
    fun createOne(@Body @Valid videoInputDTO: VideoInputDTO): HttpResponse<VideoOutputDTO> {
        val videoModel = videoService.createOne(videoInputDTO)
        val resourceCreatedURI = UriBuilder.of("/videos/${videoModel.id}").build()
        return HttpResponse.created(videoModel.toOutputDTO(), resourceCreatedURI)
    }

    @Put("/{id}")
    fun updateOne(
        @PathVariable id: Long,
        @Body @Valid videoInputDTO: VideoInputDTO,
    ): HttpResponse<VideoOutputDTO> {
        val videoModel = videoService.updateOne(id, videoInputDTO)
        return HttpResponse.ok(videoModel.toOutputDTO())
    }

    @Delete("/{id}")
    fun deleteOne(@PathVariable id: Long): HttpResponse<Unit> {
        videoService.deleteOne(id)
        return HttpResponse.noContent()
    }

}