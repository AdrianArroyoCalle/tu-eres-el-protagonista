package com.divel.tu.eres.el.protagonista;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")

public class Juego extends Activity {
	private Handler mHandler;
	private ProgressBar barra;
	private TextView pregunta;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private int jugadores;
	private int current_player;
	private int tiempo=0;
	private int num_pregunta;
	private boolean results_b1[]=new boolean[10];
	private boolean results_b2[]=new boolean[10];
	private boolean results_b3[]=new boolean[10];
	private boolean results_b4[]=new boolean[10];
	private int question=1;
	private boolean preguntas[]={false,false,false,false,false,false,false,false,false,false};
    

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
        	setTheme(android.R.style.Theme_Holo);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }else{
        	setTheme(android.R.style.Theme_Black);
        }
        setContentView(R.layout.juego);


        Bundle extra = this.getIntent().getExtras();
		jugadores = extra.getInt("JUGADORES");
		current_player=0; //DUENO ES 0, resto es un numero entero
		mHandler=new Handler();
		barra=(ProgressBar) findViewById(R.id.barra);
		pregunta=(TextView) findViewById(R.id.pregunta);
		b1=(Button) findViewById(R.id.button1);
		b2=(Button) findViewById(R.id.button2);
		b3=(Button) findViewById(R.id.button3);
		b4=(Button) findViewById(R.id.button4);
		pregunta.setText("Son "+jugadores+" jugadores");
		mHandler.removeCallbacks(Timer);
	    mHandler.postDelayed(Timer, 1000);
	    //Obtener preguntas aleatorias
	    do{
	    	num_pregunta=(int)Math.random()*9+0;
	    }while(preguntas[num_pregunta]!=false);
	    //Decir que ya esta hecha la pregunta
	    preguntas[num_pregunta]=true;
	    //Poner los textos de la pregunta
	    pregunta.setText((getResources().getStringArray(R.array.preguntas_array))[num_pregunta]);
	    
	    b1.setText((getResources().getStringArray(R.array.b1_array))[num_pregunta]);
	    b2.setText((getResources().getStringArray(R.array.b2_array))[num_pregunta]);
	    b3.setText((getResources().getStringArray(R.array.b3_array))[num_pregunta]);
	    b4.setText((getResources().getStringArray(R.array.b4_array))[num_pregunta]);
	    
	    b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				results_b1[current_player]=true;
		    	newplayer();
			}
		});
	    b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				results_b2[current_player]=true;
		    	newplayer();
			}
		});
	    b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				results_b3[current_player]=true;
		    	newplayer();
			}
		});
	    b4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				results_b4[current_player]=true;
		    	newplayer();
			}
		});
	    

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inicio, menu);
        //MenuItem item1= menu.add(0,0,0,"Get more apps");
        
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
				mHandler.removeCallbacks(Timer);
				finish();
			}
			case android.R.id.home:
			{
				finish();
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
    private Runnable Timer = new Runnable()
    {

		@Override
		public void run() {
            mHandler.removeCallbacks(Timer);
            if(tiempo<14)
            {
            	tiempo++;
            	barra.setProgress(tiempo);
            	mHandler.postDelayed(this, 1000);
            }else{
            	//Se terminó el tiempo
            	AlertDialog.Builder mensaje=new AlertDialog.Builder(Juego.this);
        		mensaje.setTitle("Arg!");
        		mensaje.setMessage(getResources().getString(R.string.sin_tiempo));
        		mensaje.setPositiveButton("OK",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) {
        				newplayer();
        			}
        		    });
        		mensaje.show();
            	
            }
            
			
		}
    	
    };
    private void newplayer(){
    	if(jugadores==current_player)
    	{

    			//Toast.makeText(getApplicationContext(), "Showing result for the question", Toast.LENGTH_SHORT).show();
    			//MOSTRAR RESULTADOS
    			String message=getResources().getString(R.string.question)+(getResources().getStringArray(R.array.preguntas_array))[num_pregunta];
    			int count;
    			for(count=1;count-1<jugadores;count++)
    			{
    				//Toast.makeText(getApplicationContext(), "PLAYERS: "+jugadores+"\nCOUNT: "+count, Toast.LENGTH_SHORT).show();	
    			 message+="\n"+getResources().getString(R.string.friend)+" "+count+" "+getResources().getString(R.string.says)+" ";
    			 if(results_b1[count])
    			 {
    				 message+=(getResources().getStringArray(R.array.b1_array))[num_pregunta];
    				 results_b1[count]=false;
    			 }else
    			 if(results_b2[count])
    			 {
    				 message+=(getResources().getStringArray(R.array.b2_array))[num_pregunta];
    				 results_b2[count]=false;
    			 }else
    			 if(results_b3[count])
    			 {
    				 message+=(getResources().getStringArray(R.array.b3_array))[num_pregunta];
    				 results_b3[count]=false;
    			 }else
    			 if(results_b4[count])
    			 {
    				 message+=(getResources().getStringArray(R.array.b4_array))[num_pregunta];
    				 results_b4[count]=false;
    			 }
    			 
    			 
    			}
    			//DUENO
    			message+="\n"+getResources().getString(R.string.correct);
    			if(results_b1[0])
   			 	{
   				 	message+=(getResources().getStringArray(R.array.b1_array))[num_pregunta];
   				 results_b1[count]=false;
   			 	}else
   			 if(results_b2[0])
   			 {
   				 message+=(getResources().getStringArray(R.array.b2_array))[num_pregunta];
   				results_b2[count]=false;
   			 }else
   			 if(results_b3[0])
   			 {
   				 message+=(getResources().getStringArray(R.array.b3_array))[num_pregunta];
   				results_b3[count]=false;
   			 }else
   			 if(results_b4[0])
   			 {
   				 message+=(getResources().getStringArray(R.array.b4_array))[num_pregunta];
   				results_b4[count]=false;
   			 }
    			
    			
    			
    			
    			
    			AlertDialog.Builder mensaje=new AlertDialog.Builder(Juego.this);
        		mensaje.setTitle(getResources().getString(R.string.results));
        		mensaje.setMessage(message);
        		mensaje.setPositiveButton("OK",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) {
            			//Toast.makeText(getApplicationContext(), "Next question. Remember, the protagonist first", Toast.LENGTH_LONG).show();
            			tiempo=0;
            			barra.setProgress(tiempo);
            			mHandler.postDelayed(Timer, 1000);
            			current_player=0;
            			if(question>8)
            			{
                			Intent theend=new Intent(Juego.this,TheEnd.class);
                			startActivity(theend);
                			finish();
            			}else{
            				do{
            			    	num_pregunta= (int) Math.floor(Math.random()*(0-9+1)+9);;
            			    }while(preguntas[num_pregunta]!=false);
            			    //Decir que ya esta hecha la pregunta
            			    preguntas[num_pregunta]=true;
            			    //Poner los textos de la pregunta
            			    pregunta.setText((getResources().getStringArray(R.array.preguntas_array))[num_pregunta]);
            			    
            			    b1.setText((getResources().getStringArray(R.array.b1_array))[num_pregunta]);
            			    b2.setText((getResources().getStringArray(R.array.b2_array))[num_pregunta]);
            			    b3.setText((getResources().getStringArray(R.array.b3_array))[num_pregunta]);
            			    b4.setText((getResources().getStringArray(R.array.b4_array))[num_pregunta]);
            				question++;
            			}
        			}
        		    });
        		mensaje.show();
	
    	}else{
        	current_player++;
        	tiempo=0;
        	barra.setProgress(tiempo);
        	mHandler.postDelayed(Timer, 1000);
        	
        	
        	
        	
    		
    	}

    }
    

    
}