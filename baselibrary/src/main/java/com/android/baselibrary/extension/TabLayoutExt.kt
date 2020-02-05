package com.android.baselibrary.extension

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
<!-- A ViewPager2 element with a TabLayout -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical">

<com.google.android.material.tabs.TabLayout
android:id="@+id/tab_layout"
android:layout_width="match_parent"
android:layout_height="wrap_content" />

<androidx.viewpager2.widget.ViewPager2
android:id="@+id/pager"
android:layout_width="match_parent"
android:layout_height="0dp"
android:layout_weight="1" />

</LinearLayout>
 */

fun TabLayout.attachViewPager(viewPager: ViewPager2) {
    TabLayoutMediator(this, viewPager) { tab, position ->
        //tab.text = "OBJECT ${(position + 1)}"
    }.attach()
}