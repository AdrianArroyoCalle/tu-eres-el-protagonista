package com.divel.tu.eres.el.protagonista;



import java.util.Locale;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Inicio extends Activity {

	NotificationManager mgr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
        	setTheme(android.R.style.Theme_Holo);
            ActionBar actionbar=getActionBar();
            actionbar.setDisplayHomeAsUpEnabled(false);
            
        }else{
        	setTheme(android.R.style.Theme_Black);
        }
        setContentView(R.layout.activity_inicio);
        final Button iniciar = (Button) findViewById(R.id.button1);
        iniciar.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.iniciar) , Toast.LENGTH_SHORT).show();
				Dialog configurar=new Dialog(Inicio.this);
				configurar.setTitle(getResources().getString(R.string.jugadores));
				configurar.setContentView(R.layout.configurar);
				final SeekBar barra= (SeekBar) configurar.findViewById(R.id.seekBar1);
				final TextView players= (TextView) configurar.findViewById(R.id.players);
				final Button next=(Button) configurar.findViewById(R.id.next);
				barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
							boolean fromUser) {
						// TODO Auto-generated method stub
						/* THIS IS BAD
						 * String lng=Locale.getDefault().getLanguage();
						if(lng.startsWith("es"))
						{
							players.setText(progress+" jugadores seleccionados");
						}else{
							players.setText(progress+" players selected");
						}*/
						String playerschanged = getResources().getString(R.string.players_changed);

						players.setText(progress+" "+playerschanged);
						
					}
				});
				next.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View a) {
						int jugadores=barra.getProgress();
						//Pasar jugadores al Intent
						if(jugadores>0 && jugadores<11){
						Intent juego=new Intent(Inicio.this,Juego.class);
						juego.putExtra("JUGADORES",jugadores);
						startActivity(juego);
						}
						
					}
				});
				configurar.show();
			}
		});
         
        int icono = R.drawable.ic_launcher;


        String texto_notificacion = getResources().getString(R.string.notify_message);
        Notification n = new Notification(icono,texto_notificacion,System.currentTimeMillis());


        
        Context contextoAplicacion = getApplicationContext();
        Intent intentNotificacion = new Intent(this, Inicio.class);
        PendingIntent intentPendiente = PendingIntent.getActivity(this, 0,intentNotificacion, Intent.FLAG_ACTIVITY_NEW_TASK);

        String titulo =getResources().getString(R.string.notify_title);
        String mensaje =getResources().getString(R.string.notify_message);
        n.setLatestEventInfo(contextoAplicacion, titulo, mensaje, intentPendiente);
        n.flags |= Notification.FLAG_AUTO_CANCEL;

        //Ahora invocamos el servicio de la notificación.
        String servicioNotificacion = Context.NOTIFICATION_SERVICE;
        mgr = (NotificationManager) getSystemService(servicioNotificacion);
        mgr.notify(1,n);
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inicio, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {    
		// Handle item selection
		switch (item.getItemId()) {    
			case R.id.menu_settings:{        
				InfoAlerta();
				return true;}  
			case R.id.exit:
			{
				mgr.cancelAll();
				finish();
				
				return true;
			}
			default:
				return false;
		}
 
	}
    public void InfoAlerta()
    {
    	AlertDialog.Builder mensaje=new AlertDialog.Builder(this);
		mensaje.setTitle("Android: You are the protagonist");
		mensaje.setMessage("(C) Adrián Arroyo Calle 2012\nWebsite?");
		mensaje.setPositiveButton("Sí",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Uri web=Uri.parse("http://sites.google.com/site/adrianarroyocalle");
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, web);
        		startActivity(launchBrowser);
				dialog.cancel();
			}
		    });
		mensaje.setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				dialog.cancel();
				
			}
		});
		mensaje.show();
    }

    
}
