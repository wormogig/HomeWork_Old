package aleksey.khokhlov.com.weather;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

public class WeatherService extends IntentService {
    int messageId = 0;
    private SensorManager sensorManager;
    private Sensor sensorTemperature;
    private boolean running = true;
    private float currentTemperature, previousTemperature;

    public WeatherService() {
        super("Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        initSensor();
        while (running){
            if (currentTemperature != previousTemperature) {
                makeNote(String.valueOf(currentTemperature));
                previousTemperature = currentTemperature;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        makeNote("Destroy");
    }

    private void makeNote (String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Текущая температура")
                .setContentText(message);
        Intent resultIntent = new Intent(this, WeatherService.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId, builder.build());
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        try {
            initSensorTemperature(sensorManager);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void initSensorTemperature (SensorManager sensorManager) {
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.registerListener(listenerTemperature, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
    }

    SensorEventListener listenerTemperature = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            changeTemperature(event.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void changeTemperature (float temperature) {
        currentTemperature = temperature;
    }

}
