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

The fifth fragment, Fragment5 has a RecyclerView with custom Progressbar. The Progressbar has a vertical threshold bar and a text below it. The text will be positioned either on the left side or the right side of the threshold bar, based if either the thresold is before the middle of the progressbar or after it.

The project is made according to the MVC design pattern. 

Demo:

![alternate text](https://github.com/OctavianIonel/AndroidDesignSupportLibraryWithCards2015/blob/master/cards_animation.gif)

In order to test this project, all you have to do is to clone it.

Enjoy!

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
