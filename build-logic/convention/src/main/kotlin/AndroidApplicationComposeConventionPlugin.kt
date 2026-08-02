import com.android.build.api.dsl.ApplicationExtension
import com.marshall.chirp.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.marshall.chirp.android.application")
                if (!hasPlugin("org.jetbrains.kotlin.multiplatform")) {
                    apply("org.jetbrains.kotlin.android")
                }
            }

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}