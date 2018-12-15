package com.example.student.minishop;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    int numberOfQuantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        CheckBox shippingCheckBox = (CheckBox) findViewById(R.id.shipping);
        boolean hasShipping = shippingCheckBox.isChecked();
        CheckBox warrantyCheckBox = (CheckBox) findViewById(R.id.warranty);
        boolean hasWarranty = warrantyCheckBox.isChecked();
        EditText nameEditText = (EditText) findViewById(R.id.name);

        displayQuantity(numberOfQuantity);
        int price = calculatePrice(hasShipping, hasWarranty);
        displayPrice(price);

        String orderMessage = "Name: " + nameEditText.getText().toString() + "\n" +
                "Add shipping? " + hasShipping + "\n" +
                "Add warranty? " + hasWarranty + "\n" +
                "Number of shirts: " + numberOfQuantity + "\n" +
                "Total: $" + price;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "T-shirt Order");
        intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("$" + number);
    }

    public void increase(View view) {
        numberOfQuantity = numberOfQuantity + 1;
        displayQuantity(numberOfQuantity);
    }

    public void decrease(View view) {
        numberOfQuantity = numberOfQuantity - 1;
        displayQuantity(numberOfQuantity);
    }

    private int calculatePrice(boolean addShipping, boolean addWarranty) {
        int price = numberOfQuantity * 25;

        if (addShipping == true) {
            price++;
        }

        if (addWarranty == true) {
            price++;
        }
        return price;
    }

}

