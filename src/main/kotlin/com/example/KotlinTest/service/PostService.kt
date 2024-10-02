package com.example.KotlinTest.service

import com.example.KotlinTest.entity.PostEntity
import com.example.KotlinTest.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(private val repository: PostRepository) {

    fun getAllPosts() : List<PostEntity> = repository.findAll()
    fun createPost(name: String, age: Int) : PostEntity{
        val post = PostEntity(name = name, age = age)
        return repository.save(post)
    }

    fun getPostById(id:Long) : PostEntity? {
        return repository.findByIdOrNull(id)
    }

    fun updatePost(id: Long, name: String?, age: Int?): PostEntity? {
        val exist = repository.findByIdOrNull(id) ?: return null // ID가 없으면 null 반환

        val updatedName = name ?: exist.name
        val updatedAge = age ?: exist.age

        val updatedPost = exist.copy(name = updatedName, age = updatedAge)

        return try {
            repository.save(updatedPost)
        } catch (e: Exception) {
            // 예외 발생 시 null 또는 다른 값을 반환하거나 로깅 처리
            null
        }
    }

    fun deletePost(id: Long) = if (repository.findByIdOrNull(id) != null){
        repository.deleteById(id)
        true
    }else {
        false
    }
}