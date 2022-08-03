package com.example.lightshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"maxsmart1996@gmail.com"};
    String subject = "Order from LightShop";
    String emailText;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
//Назначаю текст имени в этот лэйаут
        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);

        emailText = "Customer name: " + userName + "\n" +
                "Goods name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Order Price: " + orderPrice + "\n";

        TextView orderTextView1 = findViewById(R.id.orderTextViewName);
        orderTextView1.setText(userName);

        TextView orderTextView2 = findViewById(R.id.orderTextViewGoodsName);
        orderTextView2.setText(goodsName);

        TextView orderTextView3 = findViewById(R.id.orderTextViewQuantity);
        orderTextView3.setText("" + quantity);

        TextView orderTextView4 = findViewById(R.id.orderTextViewOrderPrice);
        orderTextView4.setText("" + orderPrice + " $");
    }

        public void submitOrder (View view){
//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, emailText);
            try {
                startActivity(Intent.createChooser(intent, "Send mail"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }

}
