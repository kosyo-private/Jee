package cm11.SensorTest;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;

public class SensorTestActivity extends Activity implements SensorEventListener{
	/** Called when the activity is first created. */
	EditText etAccelerometer;
	EditText etOrientation;
	EditText etMagnetic;
	EditText etTemperature;
	EditText etPressure;
	EditText etLight;
	EditText etDisplay;
	DisplayMetrics dm;
	
	SensorManager sensorManager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		etAccelerometer = (EditText)findViewById(R.id.etAccelerometer);
		etOrientation = (EditText)findViewById(R.id.etOrientation);
		etMagnetic = (EditText)findViewById(R.id.etMagnetic);
		etTemperature = (EditText)findViewById(R.id.etTemperature);
		etPressure = (EditText)findViewById(R.id.etPressure);
		etLight = (EditText)findViewById(R.id.etLight);
		etDisplay = (EditText)findViewById(R.id.etDisplay);

		dm = getResources().getDisplayMetrics();
		int displayHeightPixels = dm.heightPixels;
		int displayWidthPixels = dm.widthPixels;
		int displayDpi = dm.densityDpi;
		
		etDisplay.setText("��Pix = " + displayWidthPixels + "   " + "�cPix = " + displayHeightPixels + "   " + "DisplayDpi = " + displayDpi);
		
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onResume(){
		super.onResume();
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE), SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_GAME);

	}

	@Override
	protected void onStop(){
		super.onStop();
		sensorManager.unregisterListener(this);
	}

	@Override
	protected void onPause(){
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		StringBuilder sb;
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			sb = new StringBuilder();
			sb.append("�����x�Z���T�[\n");
			sb.append("X���̉����x:");
			sb.append(values[0]);
			sb.append("\n");
			sb.append("Y���̉����x:");
			sb.append(values[1]);
			sb.append("\n");
			sb.append("Z���̉����x:");
			sb.append(values[2]);
			etAccelerometer.setText(sb.toString());
			etAccelerometer.setTextSize(14);
			break;

		case Sensor.TYPE_ORIENTATION:
			sb = new StringBuilder();
			sb.append("�����Z���T�[\n");
			sb.append("Z���𒆐S�ɉ�]�p�x:");
			sb.append(values[0]);
			sb.append("\n");
			sb.append("X���𒆐S�ɉ�]�p�x:");
			sb.append(values[1]);
			sb.append("\n");
			sb.append("Y���𒆐S�ɉ�]�p�x:");
			sb.append(values[2]);
			etOrientation.setText(sb.toString()); 
			etOrientation.setTextSize(14);
			break;

		case Sensor.TYPE_TEMPERATURE:
			sb = new StringBuilder();
			sb.append("���x�Z���T�[\n");
			sb.append("���݂̉��x:");
			sb.append(values[0]);
			etTemperature.setText(sb.toString());
			etTemperature.setTextSize(14);
			break;

		case Sensor.TYPE_MAGNETIC_FIELD:
			sb = new StringBuilder();
			sb.append("�}�O�l�b�g�Z���T�[\n");
			sb.append("X�����̊p�x:");
			sb.append(values[0]);
			sb.append("\n");
			sb.append("Y�����̊p�x:");
			sb.append(values[1]);
			sb.append("\n");
			sb.append("Z�����̊p�x:");
			sb.append(values[2]);
			etMagnetic.setText(sb.toString());
			etMagnetic.setTextSize(14);
			break;
 
		case Sensor.TYPE_PRESSURE:
			sb = new StringBuilder();
			sb.append("�C���Z���T�[\n");
			sb.append("���݂̋C��:");
			sb.append(values[0]);
			etPressure.setText(sb.toString());
			etPressure.setTextSize(14);
			break;

		case Sensor.TYPE_LIGHT:
			sb = new StringBuilder();
			sb.append("���Z���T�[\n");
			sb.append("���݂̌��̋��x:");
			sb.append(values[0]);
			etLight.setText(sb.toString());
			etLight.setTextSize(14);
			break;

		default:
			break;
		}
	}
}