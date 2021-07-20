package br.com.aluraflix.model

import javax.persistence.*

@Entity
@Table(name = "video")
class VideoModel(

    @field:Id
    @field:Column(name = "id")
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:Column(name = "titulo", length = 100, nullable = false)
    val titulo: String,

    @field:Column(name = "descricao", length = 255, nullable = false)
    val descricao: String,

    @field:Column(name = "url", length = 255, nullable = false)
    val url: String,

) {

    override fun toString(): String {
        return "VideoModel(id=$id, titulo='$titulo', descricao='$descricao', url='$url')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VideoModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}