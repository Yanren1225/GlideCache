# GlideCache[![](https://jitpack.io/v/EndureBlaze/GlideCache.svg)](https://jitpack.io/#EndureBlaze/GlideCache)
一个可以方便快捷操作 Glide 本地缓存的开源库
## 性能
方便调用，稳定，经过时间考验
## 配置要求
GlideCache 最低支持到Android [Ice Cream Sandwich](https://developer.android.com/about/versions/android-4.0-highlights.html) (API 14)

可能需要配置 Java1.8(未测试)
Java 1.8 配置方法
在 build.gradle 下添加
```Java
android {
    ...
    }
    buildTypes {
      ...
    }
    compileOptions {
        //以下两行
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```
## 开始使用
### Gradle
1.在项目根目录的 build.gradle 添加如下代码
```Java
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```
2.在需要使用模块(一般为app)的 build.gradle 中添加依赖
```Java
dependencies {
	        implementation 'com.github.EndureBlaze:GlideCache:1.0'
	}
```
### Maven
1.首先需要添加 JitPack 仓库
```HTML
    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
2.然后添加依赖
```HTML
	<dependency>
	    <groupId>com.github.EndureBlaze</groupId>
	    <artifactId>GlideCache</artifactId>
	    <version>1.0</version>
	</dependency>
```
## 功能说明
### 设置 Glide 本地缓存图片到对应 ImageView
```Java
GlideCache.setNormalImageViaGlideCache(activity,imageview,image_url);
```
#### 参数说明
activity：上下文，传入对应的 Activity即可
imageview：你想把图片设置到的 ImageViwe
image_url：在 Glide 添加图片时使用的 URL (一般为网络地址)

### 设置 Glide 本地缓存图片并且高斯模糊到对应 ImageView
```Java
GlideCache.setNormalImageViaGlideCache(activity,imageview,image_url,pattern);
```
#### 参数说明
activity：上下文，传入对应的 Activity即可
imageview：你想把图片设置到的 ImageViwe
image_url：在 Glide 添加图片时使用的 URL (一般为网络地址)
pattern：模糊程度 int 类型，数值从 0-10，数值越大越模糊，一般设置为 8 就好

## 更多使用
### 如果我只想模糊化图片(来源不是 Glide 缓存，比如是网络或者本地)呢？
当然，这里也提供了对应的 api 以供调用
#### 从网络图片进行模糊化
```Java
FatBlur.GetUrlBitmap(image_url, blurRadius)
```
#### 参数说明
image_url：网络图片的地址
blurRadius：模糊程度，用法和 GlideCache 的 pattern 一致，设置为 8 就足够使用
#### 返回说明
使用后返回 bitmap 请注意接收

#### 从本地图片进行模糊化
```Java
FatBlur.toBlur(bitmap, blurRadius)
```
#### 参数说明
bitmap：本地图片转化的 bitmap
blurRadius：模糊程度，用法和 GlideCache 的 pattern 一致，设置为 8 就足够使用
#### 返回说明
使用后返回 bitmap 请注意接收

## 演示
暂无，等待后续补充

## 关于依赖库
本项目依赖 [Glide](https://github.com/bumptech/glide) 对于 Glide 有什么使用问题[请参阅](https://muyangmin.github.io/glide-docs-cn/)

## 反馈
在使用中如果有麻烦，或者出现bug请及时提交 issues

## 混淆(Proguard)
如果你有使用 Proguard 请添加如下代码
```Java
-keep public class cn.endureblaze.glidecache.*
```
以及 Glide 本身的混淆代码
```Java
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
```
如果你的 target API 低于 Android API 27，请添加：
```Java
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
```
## 其他作品
[Kirby Assistant](https://github.com/EndureBlaze/Kirby-Assistant)

License
-------
MIT License

Copyright (c) 2019 Haocun Ni

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