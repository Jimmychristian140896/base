package com.android.baselibrary.extension

import java.security.MessageDigest

private enum class HashType {
    MD5, SHA1, SHA256
}

fun String.applyMD5(): String = this.hashWithAlgorithm(HashType.MD5)

fun String.applySHA1(): String = this.hashWithAlgorithm(HashType.SHA1)

fun String.applySHA256(): String = this.hashWithAlgorithm(HashType.SHA256)

private fun String.hashWithAlgorithm(type: HashType): String {
    return MessageDigest.getInstance(type.name)
        .digest(this.toByteArray(Charsets.UTF_8))
        .joinToString(separator = "") { String.format("%02x", it) }
}