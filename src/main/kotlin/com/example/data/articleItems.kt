package com.example.data

import com.example.entity.ArticleCategory
import com.example.entity.Author

data class ArticleRequest(
    val title: String,
    val category: ArticleCategory,
    val author: Author
)
data class SearchRequest(
    val title: String
)