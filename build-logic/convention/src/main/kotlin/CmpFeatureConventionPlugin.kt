import com.marshall.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.marshall.chirp.cmp.library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    getByName("commonMain").dependencies {
                        implementation(project(":core:presentation"))
                        implementation(project(":core:designsystem"))

                        implementation(libs.findLibrary("koin-compose").get())
                        implementation(libs.findLibrary("koin-compose-viewmodel").get())
                        implementation(libs.findLibrary("koin-core-viewmodel").get())

                        implementation(libs.findLibrary("jetbrains-compose-runtime").get())
                        implementation(libs.findLibrary("androidx-lifecycle-viewmodelCompose").get())
                        implementation(libs.findLibrary("jetbrains-lifecycle-viewmodel").get())
                        implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())

                        implementation(libs.findLibrary("jetbrains-lifecycle-viewmodel-savedstate").get())
                        implementation(libs.findLibrary("jetbrains-savedstate").get())
                        implementation(libs.findLibrary("jetbrains-bundle").get())
                        implementation(libs.findLibrary("jetbrains-compose-navigation").get())
                    }
                    getByName("androidMain").dependencies {
                        implementation(libs.findLibrary("koin-android").get())
                        implementation(libs.findLibrary("koin-androidx-compose").get())
                        implementation(libs.findLibrary("koin-androidx-navigation").get())
                    }
                }
            }

            dependencies {
                "androidMainImplementation"(platform(libs.findLibrary("koin-bom").get()))
            }
        }
    }
}