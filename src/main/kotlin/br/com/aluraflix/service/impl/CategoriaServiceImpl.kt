package br.com.aluraflix.service.impl

import br.com.aluraflix.dto.input.CategoriaInputDTO
import br.com.aluraflix.exception.CategoriaEmUsoException
import br.com.aluraflix.exception.ItemNotFoundException
import br.com.aluraflix.mapper.toModel
import br.com.aluraflix.model.CategoriaModel
import br.com.aluraflix.repository.CategoriaRepository
import br.com.aluraflix.service.CategoriaService
import javax.inject.Singleton
import javax.persistence.PersistenceException
import javax.validation.ConstraintViolationException

@Singleton
class CategoriaServiceImpl(
    private val categoriaRepository: CategoriaRepository,
) : CategoriaService {

    override fun listAll(): Collection<CategoriaModel> {
        return categoriaRepository.findAll()
    }

    override fun listOne(id: Long): CategoriaModel {
        return categoriaRepository.findById(id).orElseThrow { ItemNotFoundException("Categoria não encontrada!") }
    }

    override fun createOne(categoriaInputDTO: CategoriaInputDTO): CategoriaModel {
        return categoriaRepository.save(categoriaInputDTO.toModel())
    }

    override fun updateOne(id: Long, categoriaInputDTO: CategoriaInputDTO): CategoriaModel {
        if (!categoriaRepository.existsById(id)) throw ItemNotFoundException()
        return categoriaRepository.update(CategoriaModel(id, categoriaInputDTO.titulo, categoriaInputDTO.cor))
    }

    override fun deleteOne(id: Long) {

        if (!categoriaRepository.existsById(id)) throw ItemNotFoundException()

        try {
            categoriaRepository.deleteById(id)
        } catch (ex: PersistenceException) {
            throw CategoriaEmUsoException("Não é possível excluir a categoria enquanto ela estiver em uso")
        }

    }

}