Alchemist Plugin for IntelliJ
=============================

You can use this plugin to work on [Alchemist](https://alchemistsimulator.github.io) in IntelliJ.

Currently, it is possible to work with two templates: [Protelis](https://protelis.github.io/) and Sapere.

How to improve it
-----------------

### Template

If you want to add a template to the plugin, you just follow the instructions below:

1. Create the template root folder in _src/main/resources/it/unibo/alchemist/intellij/templates/_.  
It makes sense call it with the template name.

2. Create a _name.txt_ file in the template root folder.  
Write in it the template name which are going to be shown in IntelliJ.

3. Optionally, create a _icon.svg_ file in the template root folder. Don't set _width_ and _height_ tag.  
This is the template icon which are going to be shown in IntelliJ.  
If you skip this point, Alchemist icon are going to be use.

4. Optionally, create a _description.html_ file in the template root folder.  
This is the template description which are going to be shown in IntelliJ.  
If you skip this point, no description are going to be shown.

5. Create the _contents_ folder in the template root folder. Add in it the template project structure.  
If you want add an empty folder, do it but create a _dummy_ file in it.  
When you add the template project structure, remember that the plugin create a Gradle project.

How to use it
-------------

### Install the plugin

When the plugin is released, it will be possible to download it from the marketplace. Other instructions will be added in that case.

### Create a project

If you want to create a project with the plugin, you just follow the instructions below:

1. Open IntelliJ.  
Be sure to be on the welcome screen of IntelliJ. If you are not on it, you probably are on a project window, so select _File_ and then _Close project_.    
You must be here: ![IntelliJ Welcome Screen](https://www.jetbrains.com/help/img/idea/2018.2/ideaWelcomeScreen.png)

2. Select _Create New Project_.  
It will open a new window.

3. In the new window, select _Alchemist_ on the left and then the template you want to use on the right. Then click _Next_.  
It will open a new window.

4. In the new window, set the _Project name_ and the _Project location_. Then select an appropriate _Project JDK_. Click _Finish_.

### Run a project

If you want to run a project created with the plugin, you just follow the instructions below:

1. Look at the top-right on the project window.
 
2. If it is not already selected, select _Run Alchemist Simulator with Gradle for ..._ in the _Edit Run/Debug Configurations_ dialog.

3. Click the _Run_ button or use the _Maiusc_ + _F10_ keys to run the project.