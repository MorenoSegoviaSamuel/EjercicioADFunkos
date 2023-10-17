plugins {
    id("java")
}

group = "org.ficheros"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("junit:junit:4.13.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}


tasks.test {
    useJUnitPlatform()
}