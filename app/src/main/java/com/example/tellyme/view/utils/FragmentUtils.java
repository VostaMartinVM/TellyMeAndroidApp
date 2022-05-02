package com.example.tellyme.view.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {

    public static void changeFragment(Fragment fragment, int id , String tag ,FragmentManager fragmentManager)
    {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();

    }

    public static void changeFragmentWithAnimation(Fragment fragment, int id , String tag ,FragmentManager fragmentManager, int enter, int exit, int popEnter,
                                                   int popExit)
    {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
    }

    public  static void hideAndShowFragment(Fragment fragmentToHide, Fragment fragmentToShow, FragmentManager fragmentManager)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragmentToHide);
        fragmentTransaction.show(fragmentToShow);
        fragmentTransaction.commit();
    }

}
