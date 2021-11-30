plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
}

kotlinDslPluginOptions {
  this.jvmTarget.set(JavaVersion.VERSION_11.toString())
}

