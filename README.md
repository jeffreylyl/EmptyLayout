# EmptyLayout

### To get a Git project into your build:

####Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
###
allprojects {<br/>
		      repositories {<br/>
			              ...<br/>
			              maven { url 'https://jitpack.io' }<br/>
		      }<br/>
	}<br/>
  
  
####Step 2. Add the dependency

###
	dependencies {
	        compile 'com.github.jeffreylyl:EmptyLayout:0.1'
	}