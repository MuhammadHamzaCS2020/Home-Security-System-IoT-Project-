package com.example.smarthome.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionStatePagerAdapter extends FragmentStatePagerAdapter
{
    private static String TAG = "SectionStatePagerAdapter";
    private List<Fragment> mFragmentList = new ArrayList<>();
    private HashMap<Fragment,String> mFragment = new HashMap<>();
    private HashMap<String,Integer> mFragmentNumber = new HashMap<>();
    private HashMap<Integer,String> mFragmentName = new HashMap<>();

    public SectionStatePagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment,String FragmentName)
    {
        mFragmentList.add(fragment);
        mFragment.put(fragment,FragmentName);
        mFragmentNumber.put(FragmentName,mFragmentList.size()-1);
        mFragmentName.put(mFragmentList.size()-1,FragmentName);

    }

    public Integer getFragmentNumber(String fragmentName)
    {
      if(mFragmentNumber.containsKey(fragmentName))
      {
          return mFragmentNumber.get(fragmentName);
      }
      else
      {
          return null;
      }
    }

    public Integer getFragmentNumber(Fragment fragment)
    {
        if(mFragmentNumber.containsKey(fragment))
        {
            return mFragmentNumber.get(fragment);
        }
        else
        {
            return null;
        }
    }

    public String getFragmentName(Integer fragmentNumber)
    {
        if(mFragmentName.containsKey(fragmentNumber))
        {
            return mFragmentName.get(fragmentNumber);
        }
        else
        {
            return null;
        }
    }
}
