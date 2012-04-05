package com.calebsantangelo.nerdery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainPage extends Activity {
    /** Called when the activity is first created. */
	
	int quattro = 4;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        
        TomatoQuery load = new TomatoQuery();
        load.execute(this);
        
        
    }
}