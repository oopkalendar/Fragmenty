package net.useobjects.fragmenty;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements ControlFragment.MainFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Aplikacia", "MainActivity.onCreate zaciatok ------------------------------------ " + savedInstanceState);
        super.onCreate(savedInstanceState);
        Log.d("Aplikacia", "MainActivity.onCreate pred volanim setContentView");
        setContentView(R.layout.activity_main);

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {

            Selection selection = ((ControlFragment) (getSupportFragmentManager().findFragmentById(R.id.fragment_control))).getSelection();
            DetailFragment detail = DetailFragment.newInstance(selection);

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_detail, detail);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        }

        Log.d("Aplikacia", "MainActivity.onCreate koniec <<<<<<<<<");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        DetailFragment detail = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail);
//        if( detail != null ) {
//            Log.d("Aplikacia", "MainActivity.onDestroy detail = " + Integer.toHexString(System.identityHashCode(detail)));
//            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.remove(detail);
//            //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChange(Selection selection) {
        showDetail(selection);
    }

    private void showDetail(Selection selection) {
        Log.d("Aplikacia", "MainActiviy.showDetail (info = " + selection.toInfoString() + ")");

        View  detailFragment = findViewById(R.id.fragment_detail);
        //DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail);

        boolean dualMode = detailFragment != null && detailFragment.getVisibility() == View.VISIBLE;
        Log.d("Aplikacia", "MainActiviy.showDetail dualMode = " + dualMode);

        if( dualMode ) {
            DetailFragment detail = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail);

            //if(detail == null) { //alebo existuje ale zobrazuje nieco ine
                detail = DetailFragment.newInstance(selection);

                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_detail, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            //}
        }
        else {
            Intent intent = new Intent();
            intent.setClass(this, DetailActivity.class);
            intent.putExtra("info", selection.toInfoString());
            startActivity(intent);
        }

    }


}
