package br.com.aluraflix.config.data

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("auth.credential")
interface BasicAuthProperty {

    val user: String
    val pass: String

}