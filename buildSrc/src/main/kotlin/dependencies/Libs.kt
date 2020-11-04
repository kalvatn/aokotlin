package dependencies

object Libs {
  const val PROTOBUF_JAVA = "com.google.protobuf:protobuf-java:${Versions.PROTOBUF}"
  const val PROTOBUF_JAVA_UTIL = "com.google.protobuf:protobuf-java-util:${Versions.PROTOBUF}"
  const val PROTOBUF_PROTOC = "com.google.protobuf:protoc:${Versions.PROTOBUF}"

  const val GRPC_NETTY = "io.grpc:grpc-netty:${Versions.GRPC}"
  const val GRPC_PROTOBUF = "io.grpc:grpc-protobuf:${Versions.GRPC}"
  const val GRPC_STUB = "io.grpc:grpc-stub:${Versions.GRPC}"
  const val GRPC_KOTLIN_STUB = "io.grpc:grpc-kotlin-stub:${Versions.GRPC_KOTLIN}"

  const val PROTOC_GEN_GRPC_JAVA = "io.grpc:protoc-gen-grpc-java:${Versions.GRPC}"
  const val PROTOC_GEN_GRPC_KOTLIN = "io.grpc:protoc-gen-grpc-kotlin:${Versions.GRPC_KOTLIN}"

  const val JAVAX_ANNOTATION_API = "javax.annotation:javax.annotation-api:${Versions.JAVAX_ANNOTATION_API}"

  const val KOTLIN_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINES}"
  const val KOTLINX_SERIALIZATION_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION_JSON}"

  const val KTOR_SERVER_CORE = "io.ktor:ktor-server-core:${Versions.KTOR}"
  const val KTOR_AUTH = "io.ktor:ktor-auth:${Versions.KTOR}"
  const val KTOR_SERVER_NETTY = "io.ktor:ktor-server-netty:${Versions.KTOR}"
  const val KTOR_JACKSON = "io.ktor:ktor-jackson:${Versions.KTOR}"
  const val KTOR_LOCATIONS = "io.ktor:ktor-locations:${Versions.KTOR}"
  const val KTOR_HTML_BUILDER = "io.ktor:ktor-html-builder:${Versions.KTOR}"

  const val EXPOSED = "org.jetbrains.exposed:exposed:${Versions.EXPOSED}"
  const val HIKARICP = "com.zaxxer:HikariCP:${Versions.HIKARICP}"

  const val JACKSON_MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON}"
  const val JACKSON_DATATYPE_JSR310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.JACKSON}"

  const val NEWRELIC_AGENT_JAVA = "com.newrelic.agent.java:newrelic-java:${Versions.NEWRELIC}"
  const val NEWRELIC_API = "com.newrelic.agent.java:newrelic-api:${Versions.NEWRELIC}"

  const val LOGBACK_CLASSIC = "ch.qos.logback:logback-classic:${Versions.LOGBACK}"
  const val KOTLIN_LOGGING = "io.github.microutils:kotlin-logging:${Versions.KOTLIN_LOGGING}"


  const val SMAUG_CORE = "com.sportradar.smaug:smaug-core:${Versions.SMAUG}"
  const val SMAUG_EXPOSED = "com.sportradar.smaug:smaug-exposed:${Versions.SMAUG}"

  const val LIVEDATA_CORE = "com.sportradar.livedata:livedata-core:${Versions.LIVEDATA}"

  const val KAFKA_CLIENTS = "org.apache.kafka:kafka-clients:${Versions.KAFKA}"
  const val KAFKA_STREAMS = "org.apache.kafka:kafka-streams:${Versions.KAFKA}"
  const val KAFKA_PROTOBUF = "io.confluent:kafka-protobuf-serializer:${Versions.KAFKA_PROTOBUF}"

  const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
  const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

  object Versions {
    const val KOTLIN = BuildPlugins.Versions.KOTLIN
    const val KOTLIN_COROUTINES = "1.3.7"

    const val PROTOBUF = "3.12.2"
    const val GRPC = "1.30.0"
    const val GRPC_KOTLIN = "0.1.3"

    const val JAVAX_ANNOTATION_API = "1.2"

    const val PROMETHEUS = "0.8.0"
    const val KTOR = "1.3.2"
    const val EXPOSED = "0.17.7"
    const val HIKARICP = "3.4.1"
    const val NEO4J = "3.1.7"
    const val KLAXON = "5.0.12"
    const val OKHTTP = "4.0.0"
    const val JACKSON = "2.10.2"
    const val NEWRELIC = "5.9.0"

    const val LOGBACK = "1.2.3"
    const val KOTLIN_LOGGING = "1.7.9"


    const val KTLINT = "0.37.2"

    const val SMAUG = "0.0.1"
    const val LIVEDATA = "2020.3.8"

    const val KAFKA = "2.6.0"
    const val KAFKA_PROTOBUF = "5.5.0"
    const val KOTLINX_SERIALIZATION_JSON = "1.0.1"

  }
}

