rootProject.name = "RecipeBook"

include("web")
include("common")
//include 'desktop'
//include 'mobile'

include("DataManager")
project(":DataManager").projectDir = File(settingsDir, "lib/DataManager")
//
//include ':utilities'
//project(":utilities").projectDir = new File(settingsDir, "lib/utilities")