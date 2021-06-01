package com.rsschool.android2021;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements DataCommunicator {

    private int previousNumber = 0;
    private int y = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private Button generateButton = null;

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        // TODO: invoke function which apply changes of the transaction
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        // TODO: implement it
        y = 1;
        final Fragment firstFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment).commit();
    }

    @Override
    public void passToFirstFragment() {
        openFirstFragment(previousNumber);
    }

    @Override
    public void passData(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void passRandomNumber(int randomNumber) {
        this.previousNumber = randomNumber;
    }

    @Override
    public void onBackPressed() {
        if (y == 1) {
            openFirstFragment(this.previousNumber);
        } else {
            super.onBackPressed();
        }
    }
}
