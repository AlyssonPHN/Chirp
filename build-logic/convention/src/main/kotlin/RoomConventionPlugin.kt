import androidx.room.gradle.RoomExtension
import com.marshall.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("androidx.room")
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                "commonMainApi"(libs.findLibrary("androidx-room-runtime").get())
                "commonMainApi"(libs.findLibrary("sqlite-bundled").get())
            }

            val roomCompiler = libs.findLibrary("androidx-room-compiler").get()
            listOf(
                "kspCommonMain",
                "kspAndroidMain",
                "kspIosSimulatorArm64",
                "kspIosX64",
                "kspIosArm64"
            ).forEach { configName ->
                configurations.matching { it.name == configName }.all {
                    target.dependencies.add(configName, roomCompiler)
                }
            }
        }
    }
}