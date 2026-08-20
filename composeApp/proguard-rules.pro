# Room/Decompose/Koin рефлексию не используют, но kotlinx.serialization требует
# сохранить сгенерированные сериализаторы конфигураций Decompose.
-keepclassmembers class ** {
    *** Companion;
}
-keepclasseswithmembers class ** {
    kotlinx.serialization.KSerializer serializer(...);
}
