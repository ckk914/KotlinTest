package com.example.KotlinTest.repository

import com.example.KotlinTest.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long>
