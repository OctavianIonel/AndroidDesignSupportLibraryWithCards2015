# README #

This README would normally document whatever steps are necessary to get your application up and running.


* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### Details of this project ###

* Summary of set up
The project includes: CoordinatorLayout, AppBarLayout (Toolbar & TabLayout), NavigationView, FloatingActionButton, Snackbar. 
Inside the first fragment Fragment1 (NavigationView > Cards, toolbar, tablayout) it loads overlaps the main toolbar, in order to be able to hide the top part of the toolbar, while you are scrolling. 

Fragment1 contains inside 2 other fragments, and you can switch between them by using ViewPager (swiping to the right or left) or you can click on the name of the tabs. 

The second fragment, Fragment2 (Cards, swipe and delete cards) there are CardViews which you can delete by pressing the delete icon or by swiping to the left or right (based on Roman Nurik's example).  

In the third fragment, Fragment3 (NavigationView > Generic fragment), there is a generic fragment with no content.

The fourth fragment, Fragment4 (NavigationView > Expandable cards), there is an example of cards which expand by clicking on the respective arrow.

The project is made according to the MVC design pattern. 

Demo:

![alternate text](https://github.com/OctavianIonel/AndroidDesignSupportLibraryWithCards2015/blob/master/cards_animation.gif)


Dependencies to your gradle file in Android Studio:
    
    compile 'com.android.support:appcompat-v7:23.1.1'
    
    compile 'com.android.support:design:23.1.1'
    
    compile 'com.android.support:recyclerview-v7:23.1.1'
    
    compile 'com.android.support:cardview-v7:23.1.1'

The project is provided under Apache license. Enjoy!
