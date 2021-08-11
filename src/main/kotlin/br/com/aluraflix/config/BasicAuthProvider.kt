package br.com.aluraflix.config

import br.com.aluraflix.config.data.BasicAuthProperty
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton

@Singleton
class BasicAuthProvider(
    private val basicAuthProperty: BasicAuthProperty,
) : AuthenticationProvider {

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {

        val user = authenticationRequest?.identity as String
        val pass = authenticationRequest.secret as String

        return if (basicAuthProperty.user == user && basicAuthProperty.pass == pass) {
            Flowable.just(UserDetails(user, listOf()))
        } else {
            Flowable.just(AuthenticationFailed())
        }

    }

}