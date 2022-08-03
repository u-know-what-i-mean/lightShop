package com.example.lightshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0 ;

    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        userNameEditText = findViewById(R.id.editTextTextPersonName);

        createSpinner();
        createMap();



    }

    void createSpinner() {
        //Добавление спиннера
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Beosound 1");
        spinnerArrayList.add("Beolit 17");
        spinnerArrayList.add("Beoplay H9");
        spinnerArrayList.add("Beoplay A9");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        //Хэш мэп в спиннере для подвязки с ценой
        goodsMap = new HashMap();
        goodsMap.put("Beosound 1", 1600.0);
        goodsMap.put("Beolit 17", 550.0);
        goodsMap.put("Beoplay H9", 250.0);
        goodsMap.put("Beoplay A9", 1550.0);

    }


    //Кнопка увеличения количества товара
    public void increaseQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantity = quantity + 1;
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("" + quantity * price);

    }

    //Кнопка уменьшения количества товара
    public void decreaseQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        if (quantity > 0)
        quantity = quantity - 1;
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("" + quantity * price);

    }

//Привязка к цене по значению спиннера

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName) {
            case "Beosound 1" :
                goodsImageView.setImageResource(R.drawable.speaker2);
                break;
            case "Beolit 17" :
                goodsImageView.setImageResource(R.drawable.speaker3);
                break;
            case "Beoplay H9" :
                goodsImageView.setImageResource(R.drawable.speaker4);
                break;
            case "Beoplay A9" :
                goodsImageView.setImageResource(R.drawable.speaker5);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.speaker2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
//Кнопка добавить в корзину отрабатывает запись данных
    public void addToCart(View view) {
        Order order = new Order();
//поле "введите имя" присваивает значение
        order.userName = userNameEditText.getText().toString();
// выводим имя в лог кат в дебаг
//        Log.d("printUserName", order.userName);
//присваиваем остальные значения по заказу в класс Заказ
        order.goodsName = goodsName;
//        Log.d("goodsName", order.goodsName);
        order.quantity = quantity;
//        Log.d("quantity", "" + order.quantity);
        order.orderPrice = price * quantity;
//        Log.d("orderPrice", "" + order.orderPrice);
//передаю имя заказчика в новый лэйаут
        Intent orderIntent = new Intent (MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("userNameForIntent", order.userName);




//врубаю новое окно
        startActivity(orderIntent);


    }


}