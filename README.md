 # **injecter** #
 [![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
 [![](https://jitpack.io/v/JackL124/injecter.svg)](https://jitpack.io/#JackL124/injecter)
 [![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2019%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=19)


## 基于反射+动态代理实现的注解框架 ###

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

Copyright (c) 2021 JackL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
