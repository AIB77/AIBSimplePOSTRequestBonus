package com.example.aibsimplepostrequestbonus

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var EDT :EditText
    lateinit var BTN :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EDT=findViewById(R.id.edt)
        BTN=findViewById(R.id.btn)


        BTN.setOnClickListener {

            var f = MyDataItem(EDT.text.toString())

            addSingleuser(f, onResult = {

                EDT.setText("")

                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }


    }

    private fun addSingleuser(f: MyDataItem, onResult: () -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)



        if (apiInterface != null) {
            apiInterface.addUser(f).enqueue(object : Callback<MyDataItem> {
                override fun onResponse(
                    call: Call<MyDataItem>,
                    response: Response<MyDataItem>
                ) {

                    onResult()

                }

                override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();


                }
            })
        }

    }
}