package jpms

import org.gradlex.javamodule.moduleinfo.ExtraJavaModuleInfoPluginExtension

fun ExtraJavaModuleInfoPluginExtension.defaultModule(
    identifier: String,
    moduleName: String
) {
    module(identifier, moduleName) {
        exportAllPackages()
        requireAllDefinedDependencies()
    }
}