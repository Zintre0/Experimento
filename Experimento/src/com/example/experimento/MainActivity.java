package com.example.experimento;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

	Animation animacion,animacion_btn;
    TextView texto,cont;
	Button btn1;
	SensorManager sensorManager;
    Sensor proxi;
	private int var,cont1=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		animacion = AnimationUtils.loadAnimation(this,R.anim.animacion);
		animacion_btn = AnimationUtils.loadAnimation(this,R.anim.amin_type);
	    texto = (TextView) findViewById(R.id.textView);
	    cont = (TextView) findViewById(R.id.cont);
	    btn1 = (Button) findViewById(R.id.btn_1);
	    
	    //btn1.setOnClickListener(this);
	    cont.setText(Integer.toString(cont1));
	    
	    sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	    proxi = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	    sensorManager.registerListener(this, proxi,SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void cero(View view){
		this.var=0;
		cont1=0;
		cont.setText(Integer.toString(cont1));
	}

	/*@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getId() == R.id.btn_1){
			texto.startAnimation(animacion);
		    btn1.startAnimation(animacion_btn);
		}
		
	}*/

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}
	
	

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		synchronized (this) {
			switch(event.sensor.getType()){
				case Sensor.TYPE_PROXIMITY:
					this.var+=1;
					this.cont1+=1;
					if (this.var==3) {
						texto.setText("Estas cerca del telefono!!");
						btn1.startAnimation(animacion_btn);
						break;
					}
					cont.setText(Integer.toString(cont1));
			}
		}
		
	}

}
