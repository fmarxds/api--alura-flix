package br.com.aluraflix.model

import javax.persistence.*

@Entity
@Table(name = "categoria")
class CategoriaModel (

    @field:Id
    @field:Column(name = "id")
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:Column(name = "titulo", length = 100, nullable = false)
    val titulo: String,

    @field:Column(name = "cor", length = 7, nullable = false)
    val cor: String,

) {

    override fun toString(): String {
        return "CategoriaModel(id=$id, titulo='$titulo', cor='$cor')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CategoriaModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}