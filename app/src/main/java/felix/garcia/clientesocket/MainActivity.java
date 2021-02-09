package felix.garcia.clientesocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String mensajecompleto = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button miboton = findViewById(R.id.idBotonEnviar);
        miboton.setOnClickListener(this);
        EditText mensaje =findViewById(R.id.idMensaje);
        mensaje.setText(getIP());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.idBotonEnviar:
                EditText mensaje =findViewById(R.id.idMensaje);
                String mensajeasumar = mensaje.getText().toString();
                mensajecompleto+= mensajeasumar+"\n";
                Log.e("mensaje", mensajecompleto);

                TextView mensajeMostrar = findViewById(R.id.idtodos);
                mensajeMostrar.setText(mensajecompleto);
                mensaje.setText("");
                break;
        }
    }

    public static String getIP(){
        List<InetAddress> addrs;
        String address = "";
        try{
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces){
                addrs = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addrs){
                    if(!addr.isLoopbackAddress() && addr instanceof Inet4Address){
                        address = addr.getHostAddress().toUpperCase(new Locale("es", "MX"));
                    }
                }
            }
        }catch (Exception e){
            Log.w("Error", "Ex getting IP value " + e.getMessage());
        }
        return address;
    }
}