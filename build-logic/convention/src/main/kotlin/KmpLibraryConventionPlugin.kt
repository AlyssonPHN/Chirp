import com.android.build.api.dsl.LibraryExtension
import com.marshall.chirp.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure

class KmpLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.kotlin.multiplatform.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.marshall.chirp.android.library")
                apply("org.jetbrains.compose")
            }

            extensions.configure<LibraryExtension>() {
                configureKotlinAndroid(this)
            }
        }
    }

}