# checkVueRouterNameRepeat
如果你会中文，请看这篇介绍：http://blog.csdn.net/zhangchao19890805/article/details/78944278

## scenario

I will use vue-router to manage router when I develop front-end project whit Vue.js . I like to navigate router whith router name, because changing path is convenient in this way. The router configuration is written in js file. If the scale of the project is big, those vue-router configurations are devided into a number of js files. 

It is acknowledged that the specification of vue-router says router name must be unique.

However, there is a problem: The repeat router names are likely to happen when a front-end project is developed by many people and its vue-router configurations are deveded into a lot of js files.

## 

