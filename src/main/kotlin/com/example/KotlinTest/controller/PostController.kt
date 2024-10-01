package com.example.KotlinTest.controller

import com.example.KotlinTest.dto.PostRequestDto
import com.example.KotlinTest.dto.UpdatePostRequestDto
import com.example.KotlinTest.entity.PostEntity
import com.example.KotlinTest.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController {

    @Autowired
    private lateinit var postService: PostService

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<PostEntity>> {
        val posts = postService.getAllPosts()
        return ResponseEntity(posts, HttpStatus.OK)
    }

    @PostMapping
    fun createPost(@RequestBody postRequest: PostRequestDto): ResponseEntity<PostEntity> {
        val createdPost = postService.createPost(postRequest.name, postRequest.age)
        return ResponseEntity(createdPost, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<PostEntity?> {
        val post = postService.getPostById(id)
        return if (post != null) {
            ResponseEntity(post, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updatePost(
        @PathVariable id: Long,
        @RequestBody request: UpdatePostRequestDto // JSON 요청 본문 매핑
    ): ResponseEntity<PostEntity?> {
        val updatedPost = postService.updatePost(id, request.name, request.age)
        return if (updatedPost != null) {
            ResponseEntity(updatedPost, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Unit> {
        val isDeleted = postService.deletePost(id)
        return if (isDeleted) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}