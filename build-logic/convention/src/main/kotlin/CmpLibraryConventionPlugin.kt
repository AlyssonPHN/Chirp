import com.marshall.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.marshall.chirp.kmp.library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.getByName("commonMain").dependencies {
                    implementation(libs.findLibrary("jetbrains-compose-ui").get())
                    implementation(libs.findLibrary("jetbrains-compose-foundation").get())
                    implementation(libs.findLibrary("jetbrains-compose-material3-icons-core").get())
                    implementation(libs.findLibrary("jetbrains-compose-material3").get())
                }
            }
        }
    }
}