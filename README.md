# checkVueRouterNameRepeat
如果你会中文，请看这篇介绍：http://blog.csdn.net/zhangchao19890805/article/details/78944278

## scenario

I will use vue-router to manage router when I develop front-end project whit Vue.js . I like to navigate router whith router name, because changing path is convenient in this way. The router configuration is written in js file. If the scale of the project is big, those vue-router configurations are devided into a number of js files. 

It is acknowledged that the specification of vue-router says router name must be unique.

However, there is a problem: The repeat router names are likely to happen when a front-end project is developed by many people and its vue-router configurations are deveded into a lot of js files.

## How to use it ?

Find config.xml, the content of config.xml may look like this:

```
<?xml version="1.0" encoding="UTF-8"?>
<folders>
    <folder>D:\your\folder_a</folder>
    <folder>D:\your\folder_b</folder>
</folders>
```

The content of folder tag is the path of folder where router file is. You can specify multiple folder paths. After save your changes, enter checkVueRouterNameRepeat tool directory in CMD. Execute commands:

```
java -jar checkVueRouterNameRepeat-0.0.1-SNAPSHOT.jar
```

If there is not any repeat router name, you can read tips as follow:

```
路由名称没有重复。
```

If there is some repeat router name, you can read tips as follow:

```
D:\sdf\config.xml
重复的路由名称是：  guideBookTeacher
D:\workspaceSet\vsc\front\src\routes\b\teacher.js
```

Attention: The vue-router syntax support of this tool is limited. The users should read the introduction of vue-router first. The code about vue-router should be written as follow:

```
{
  path: 'test/:testId',
  name: 'test',
  // Some code...
}
```
Please make sure that the `name` property is on a separate line.

This tool has a limited support of commonts like `/* ... */`.





对 /* ... */ 注释支持有限。请确保 /*在一行的行首，*/ 在一行的行尾。写成类似下面这种：

{





