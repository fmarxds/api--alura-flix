package br.com.aluraflix.controller

import br.com.aluraflix.dto.output.ErrorOutputDTO
import br.com.aluraflix.exception.ItemNotFoundException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.security.authentication.AuthorizationException
import javax.validation.ConstraintViolationException

@Controller
class ErrorController {

    @Error(exception = ItemNotFoundException::class, global = true)
    fun handleItemNotFoundException(
        itemNotFoundException: ItemNotFoundException,
        httpRequest: HttpRequest<*>,
    ): HttpResponse<ErrorOutputDTO> {

        val errorOutputDTO = ErrorOutputDTO(
            status = HttpStatus.NOT_FOUND.code,
            error = HttpStatus.NOT_FOUND.reason,
            message = itemNotFoundException.message,
            path = httpRequest.path
        )

        return HttpResponse.notFound(errorOutputDTO)

    }

    @Error(exception = ConstraintViolationException::class, global = true)
    fun handleItemNotFoundException(
        constraintViolationException: ConstraintViolationException,
        httpRequest: HttpRequest<*>,
    ): HttpResponse<ErrorOutputDTO> {

        val errorOutputDTO = ErrorOutputDTO(
            status = HttpStatus.NOT_FOUND.code,
            error = HttpStatus.NOT_FOUND.reason,
            message = constraintViolationException.constraintViolations.joinToString { it.message },
            path = httpRequest.path
        )

        return HttpResponse.badRequest(errorOutputDTO)

    }

    @Error(exception = AuthorizationException::class, global = true)
    fun handleAuthorizationException(
        authorizationException: AuthorizationException,
        httpRequest: HttpRequest<*>,
    ): HttpResponse<ErrorOutputDTO> {

        val httpStatus = if (authorizationException.isForbidden) HttpStatus.FORBIDDEN else HttpStatus.UNAUTHORIZED

        val errorOutputDTO = ErrorOutputDTO(
            status = httpStatus.code,
            error = httpStatus.reason,
            message = "Não autorizado!",
            path = httpRequest.path,
        )

        return HttpResponse.status<ErrorOutputDTO?>(httpStatus).body(errorOutputDTO)

    }

    @Error(exception = Exception::class, global = true)
    fun handleException(
        exception: Exception,
        httpRequest: HttpRequest<*>,
    ): HttpResponse<ErrorOutputDTO> {

        exception.printStackTrace()

        val errorOutputDTO = ErrorOutputDTO(
            status = HttpStatus.INTERNAL_SERVER_ERROR.code,
            error = HttpStatus.INTERNAL_SERVER_ERROR.reason,
            message = exception.message,
            path = httpRequest.path
        )

        return HttpResponse.serverError(errorOutputDTO)

    }

}