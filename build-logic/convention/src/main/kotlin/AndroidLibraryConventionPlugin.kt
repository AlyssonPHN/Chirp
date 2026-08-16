import com.android.build.api.dsl.LibraryExtension
import com.marshall.chirp.convention.configureKotlinAndroid
import com.marshall.chirp.convention.findVersionString
import com.marshall.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                val targetSdk = libs.findVersionString("projectTargetSdkVersion").toInt()
                lint.targetSdk = targetSdk
                testOptions.targetSdk = targetSdk
            }
        }
    }
}
