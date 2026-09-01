plugins {
    id("gymhelper.kmp.library")
}

kotlin {
    // jvm() нужен, потому что от core:common зависят Room-модули,
    // а они собираются под JVM ради DAO-тестов (см. gymhelper.kmp.room).
    jvm()
}
