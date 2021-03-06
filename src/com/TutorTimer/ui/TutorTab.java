package com.TutorTimer.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import com.TutorTimer.students.StudentManager;

import java.util.concurrent.ThreadPoolExecutor;

abstract class TutorTab implements ActionBar.TabListener
{
    final Activity           m_activity;
    final ThreadPoolExecutor m_threadPool;
    final StudentManager     m_studentManager;

    TutorTab(Activity activity, ThreadPoolExecutor threadPool)
    {
        m_activity = activity;
        m_threadPool = threadPool;
        m_studentManager = StudentManager.getInstance(activity);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
    {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
    {
    }
}
