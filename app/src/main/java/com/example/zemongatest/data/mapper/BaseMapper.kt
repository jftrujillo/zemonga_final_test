package com.example.zemongatest.data.mapper

interface BaseMapper<S,E,P> {
    fun transformResponseToEntity(service: S): E
    fun transformEntityToPresentation(entity: E): P
}