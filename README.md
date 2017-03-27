## Description
ProgressView like the one in Weibo

## Screenshots

<img src="screenshots/demo.png" width=270 height=486/>

## Download

Step 1. Add the JitPack repository to your your root build.gradle

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add WeiboProgressView to your dependencies

```groovy
dependencies {
    compile 'com.github.zhuhean:WeiboProgressView:1.0'
}
```

## Usage

### Step 1.Add WeiboProgressView to your layout file

```xml
    <com.zhuhean.weiboprogressview.WeiboProgressView xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/progress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        app:wpv_color="#FFF"
        app:wpv_padding="4dp"
        app:wpv_ring_width="2dp"
        />
```

Attribute explanation:
wpv_color: the main color of WeiboProgressView
wpv_padding: the width of the gap between the ring and the progress oval
wpv_ring_width: the width of the outer ring

### Step 2.Using WeiboProgressView in your Java class

```java
WeiboProgressView weiboProgressView = (WeiboProgressView) findViewById(R.id.progress);
weiboProgressView.setProgress(progress); //progress is a number in [0,100];
```