package com.mycabbages.teamavatar.ido;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Date;

public class NewItemActivity extends AppCompatActivity
        implements DatePickerFragment.DatePickerFragmentListener,
        TimePickerFragment.TimePickerFragmentListener {

    private final String TAG = "NewItemActivity";
    private ConstraintLayout mConstraintLayout; // ConstraintLayout containing delete button
    private Switch mSwitch;
    private EditText datePickerEditText;
    private Button goalPickerButton;
    private Button deleteButton;
    private ConstraintLayout notifConstraintLayout;
    private EditText descriptionEditText;
    private EditText timePickerEditText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // get references to everything we need in the UI.
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mSwitch = (Switch) findViewById(R.id.notificationSettingsSwitch);
        datePickerEditText = (EditText) findViewById(R.id.datePickerEditText);
        goalPickerButton = (Button) findViewById(R.id.goalPickerButton);
        notifConstraintLayout = (ConstraintLayout) findViewById(R.id.notifSettingsConstraintLayout);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        descriptionEditText = (EditText) findViewById(R.id.description);
        timePickerEditText = (EditText) findViewById(R.id.timePickerEditText);

        // Get reference to ConstraintLayout containing delete button
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.bottomConstraintLayout);

        // make the notifConstraintLayout unclickable from the beginning.
        notifConstraintLayout.setVisibility(notifConstraintLayout.GONE);

        // set the toolbar as the action bar for the activity.
        setSupportActionBar(myToolbar);

        // get a reference to the action bar
        ActionBar actionBar = getSupportActionBar();

        // set the action bar title
        actionBar.setTitle("New Item Activity");

        // Display back arrow (up button) in top left and enable it.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // set a listener for the switch for when it is checked
        // hid
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "switch is on");
                    // do something because the switch is on...
                    mConstraintLayout.animate().translationY(mConstraintLayout.getHeight() +
                            deleteButton.getHeight());
                    notifConstraintLayout.setVisibility(notifConstraintLayout.VISIBLE);
                } else {
                    Log.d(TAG, "switch is off");

                    // do something because the switch is off..
                    mConstraintLayout.animate().translationY(0);
                    notifConstraintLayout.setVisibility(notifConstraintLayout.GONE);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_item_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.done) {
            Log.d(TAG, "Done button tapped");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * SHOW DATE PICKER DIALOG
     * */
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(this);
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    /**
     * SHOW TIME PICKER DIALOG
     * */
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = TimePickerFragment.newInstance(this);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * ON DATE SET
     * */
    @Override
    public void onDateSet(Date date) {
        Log.d(TAG, date.toString());
        datePickerEditText.setText(date.toString());
    }

    /**
     * ON TIME SET
     * */
    @Override
    public void onTimeSet(int hourOfDay, int minute) {
        timePickerEditText.setText(hourOfDay + ":" + minute);
    }

    /**
     * TO ADD ITEM ACTIVITY
     * Called from UI
     * */
    public void toAddItemActivity(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
}
