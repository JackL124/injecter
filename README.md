 # **injecter** #
 [![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
 [![](https://jitpack.io/v/JackL124/injecter.svg)](https://jitpack.io/#JackL124/injecter)
 [![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2019%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=19)


## 基于注解+反射+动态代理实现的注解框架 ###

## Demo
[Download APK-Demo](apk/app-debug.apk)

## 集成

#### 1.添加 Gradle 依赖
在项目根目录的build.gradle 中添加
```
repositories {
       maven { url 'https://jitpack.io' }
   }
```
 在app build.gradle 中添加依赖
```
 dependencies {
 	   implementation 'com.github.JackL124:injecter:Tag'
   }
```
#### 2.使用

* ##### 实例化view：
```
 @BindView(id = R.id.id1)
 val view1: TextView?=null
```

* ##### 处理点击事件:
```
 @BindClick(ids = [R.id.id1])
 fun onClick(view: View){

 }
```

* ##### inflate布局:
```
 @BindInflate(id = R.layout.activity_main)
 class MainActivity : AppCompatActivity() {
 }
```

##### 如果觉得的还不错.....欢迎大家Star 👍


## License

    Copyright 2021 jackl

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

