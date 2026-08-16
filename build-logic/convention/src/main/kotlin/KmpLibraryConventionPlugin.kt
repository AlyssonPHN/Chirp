import com.android.build.api.dsl.LibraryExtension
import com.marshall.chirp.convention.configureKotlinAndroid
import com.marshall.chirp.convention.configureKotlinMultiplatform
import com.marshall.chirp.convention.libs
import com.marshall.chirp.convention.pathToResourcePrefix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KmpLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.marshall.chirp.android.library")
            }

            configureKotlinMultiplatform()

            extensions.configure<LibraryExtension>() {
                configureKotlinAndroid(this)
                resourcePrefix = this@with.pathToResourcePrefix()
                // Required to make debug build of app run in IOS simulator
                experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
            }

            dependencies {
                "commonMainImplementation"  (libs.findLibrary("kotlinx-serialization-json").get())
                "commonMainImplementation"  (libs.findLibrary("kotlin-test").get())
            }
        }
    }

}