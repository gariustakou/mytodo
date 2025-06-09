package org.garius.mytodo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform