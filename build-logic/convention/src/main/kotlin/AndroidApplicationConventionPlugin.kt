import com.android.build.api.dsl.ApplicationExtension
import com.marshall.chirp.convention.configureKotlinAndroid
import com.marshall.chirp.convention.findVersionString
import com.marshall.chirp.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                namespace = "com.marshall.chirp"

                defaultConfig {
                    applicationId = libs.findVersionString("projectApplicationId")
                    targetSdk = libs.findVersionString("projectTargetSdkVersion").toInt()
                    versionCode = libs.findVersionString("projectVersionCode").toInt()
                    versionName = libs.findVersionString("projectVersionName")
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }

                configureKotlinAndroid(this)
            }
        }
    }
}