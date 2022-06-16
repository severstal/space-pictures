package com.example.space.service

import com.example.space.BuildConfig
import com.example.space.domain.SpacePictureDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

interface PictureService {
    suspend fun getSpacePictures(startDate: LocalDate,
                         endDate: LocalDate): List<SpacePictureDto>
}

class PictureServiceImpl @Inject constructor(): PictureService {

    private val baseUrl = BuildConfig.BASE_URL

    override suspend fun getSpacePictures(startDate: LocalDate,
                                 endDate: LocalDate): List<SpacePictureDto> {
        return client.get(baseUrl) { // todo check response status/code
            parameter("api_key", BuildConfig.API_KEY)
            parameter("start_date", startDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
            parameter("end_date", endDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
        }
    }

    private val kLogger = object : Logger {
        override fun log(message: String) {
            if (message.contains("api_key=")) {
                val messageWithoutApiKey = message.replace(BuildConfig.API_KEY, "xxx")
                println(messageWithoutApiKey)
            } else {
                println(message)
            }
        }
    }

    private val client = HttpClient() {

        install(Logging) {
            logger = kLogger
            level = LogLevel.BODY
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class PictureServiceModule {

    @Binds
    @Singleton
    abstract fun bindPictureService(
        pictureServiceImpl: PictureServiceImpl
    ): PictureService
}