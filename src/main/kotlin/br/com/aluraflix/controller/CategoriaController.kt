package br.com.aluraflix.controller

import br.com.aluraflix.dto.input.CategoriaInputDTO
import br.com.aluraflix.dto.output.CategoriaOutputDTO
import br.com.aluraflix.dto.output.ErrorOutputDTO
import br.com.aluraflix.dto.output.VideoOutputDTO
import br.com.aluraflix.exception.CategoriaEmUsoException
import br.com.aluraflix.mapper.toOutputDTO
import br.com.aluraflix.service.CategoriaService
import br.com.aluraflix.service.VideoService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(
    value = "/categorias",
    produces = [MediaType.APPLICATION_JSON],
    consumes = [MediaType.APPLICATION_JSON],
)
class CategoriaController(
    private val categoriaService: CategoriaService,
    private val videoService: VideoService,
) {

    @Get
    fun listAll(
        pageable: Pageable,
    ): Page<CategoriaOutputDTO> {
        return categoriaService.listAll(pageable).map { it.toOutputDTO() }
    }

    @Get("/{id}")
    fun listOne(@PathVariable id: Long): HttpResponse<CategoriaOutputDTO> {
        return HttpResponse.ok(categoriaService.listOne(id).toOutputDTO())
    }

    @Get("/{id}/videos")
    fun listVideosByOne(@PathVariable id: Long): HttpResponse<List<VideoOutputDTO>> {
        return HttpResponse.ok(videoService.listAllByCategoriaId(id).map { it.toOutputDTO() } )
    }

    @Post
    fun createOne(@Body @Valid categoriaInputDTO: CategoriaInputDTO): HttpResponse<CategoriaOutputDTO> {
        val categoriaModel = categoriaService.createOne(categoriaInputDTO)
        val resourceCreatedURI = UriBuilder.of("/videos/${categoriaModel.id}").build()
        return HttpResponse.created(categoriaModel.toOutputDTO(), resourceCreatedURI)
    }

    @Put("/{id}")
    fun updateOne(
        @PathVariable id: Long,
        @Body @Valid categoriaInputDTO: CategoriaInputDTO,
    ): HttpResponse<CategoriaOutputDTO> {
        val categoriaModel = categoriaService.updateOne(id, categoriaInputDTO)
        return HttpResponse.ok(categoriaModel.toOutputDTO())
    }

    @Delete("/{id}")
    fun deleteOne(@PathVariable id: Long): HttpResponse<Unit> {
        categoriaService.deleteOne(id)
        return HttpResponse.noContent()
    }

    @Error(exception = CategoriaEmUsoException::class, global = false)
    fun handleCategoriaEmUsoException(
        categoriaEmUsoException: CategoriaEmUsoException,
        httpRequest: HttpRequest<*>,
    ): HttpResponse<ErrorOutputDTO> {

        val errorOutputDTO = ErrorOutputDTO(
            status = HttpStatus.BAD_REQUEST.code,
            error = HttpStatus.BAD_REQUEST.reason,
            message = categoriaEmUsoException.message,
            path = httpRequest.path
        )

        return HttpResponse.badRequest(errorOutputDTO)

    }

}