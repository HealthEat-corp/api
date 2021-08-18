package com.healtheat.api

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class RestApiBaseFormat (
    var data: Any? = null,
    var message: String? = null
)
