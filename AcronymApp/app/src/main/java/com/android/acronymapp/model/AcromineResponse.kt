package com.android.acronymapp.model

import java.io.Serializable

data class AcromineResponse(
    val sf: String? = null,
    val lfs: MutableList<LongForm>? = null
) : Serializable

data class LongForm(
    val freq: Int? = null,
    val lf: String? = null,
    val vars: MutableList<Variation>? = null,
    val since: Int? = null
) : Serializable

data class Variation(
    val freq: Int? = null,
    val lf: String? = null,
    val since: Int? = null
) : Serializable