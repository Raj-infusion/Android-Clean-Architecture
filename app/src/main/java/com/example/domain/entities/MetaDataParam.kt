package com.example.domain.entities

data class MetaDataParam(val abTestData: List<TestData>)

data class TestData(
    val name: String,
    val options: List<String>,
    val selected: String
)
