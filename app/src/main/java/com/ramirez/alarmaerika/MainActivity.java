package com.ramirez.alarmaerika;


import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //variables
    private Spinner hrs;
    private Spinner mnts;
    private EditText mensaje;
    private Button boton;
    private int Phora;
    private int Pmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner de las horas
        hrs = findViewById(R.id.SP_hours);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.horas,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hrs.setAdapter(adapter);
        hrs.setOnItemSelectedListener(this);

        //spinner de los minutos
        mnts = findViewById(R.id.SP_minutes);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.minutos,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mnts.setAdapter(adapter2);
        mnts.setOnItemSelectedListener(this);

        //instanciando
        mensaje = findViewById(R.id.ET_mensaje);
        boton = findViewById(R.id.acept);

        //listener boton
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implementacion del metodo
                alarm(mensaje.getText().toString(),Phora,Pmin);
            }
        });
    }

    private void alarm(String message, int hour,int minutes) {

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);//instanciando intent
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, message);//poniento mensaje
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);//poniendo hora
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);//poninedo minutos

        //validacion
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = hrs.getSelectedItem().toString();// obteniendo elemento seleccionado del spinner de horas
        //Toast.makeText(horitas.getContext(),text,Toast.LENGTH_SHORT).show();//mostrar mensaje
        String text2 = mnts.getSelectedItem().toString();// obteniendo el elemento selecciondo del spinner de minutos
        //Toast.makeText((minutitos.getContext(),text2,Toast.LENGTH_SHORT).show();//mostrar mensaje

        Phora = Integer.parseInt(text);//transformando a int
        Pmin = Integer.parseInt(text2);// transformando a int
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}