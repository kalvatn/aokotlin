package repository

import environment.Property
import java.net.URI

const val BASE_URL = "https://archiva.sportradar.ag/repository"

enum class ArchivaRepository(
  val url: URI,
  val propertyUsername: Property,
  val propertyPassword: Property
) : PublishTarget {
  BETRADAR_JAVA(
    url = URI("$BASE_URL/betradar-java/"),
    propertyUsername = Property.ARCHIVA_USERNAME,
    propertyPassword = Property.ARCHIVA_PASSWORD
  )
  ;
}
