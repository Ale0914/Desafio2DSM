package com.example.farmacia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

data class Producto(val nombre: String, val precio: Double)

class HomeActivity : AppCompatActivity() { // Cambié el nombre de la clase a HomeActivity
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private lateinit var totalTextView: TextView
    private lateinit var pagarButton: Button

    private val productosSeleccionados = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        totalTextView = findViewById(R.id.textView7)
        pagarButton = findViewById(R.id.button5)

        adapter = ProductoAdapter(productosSeleccionados)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val checkBoxMedicamento1: CheckBox = findViewById(R.id.checkBox1)
        val checkBoxMedicamento2: CheckBox = findViewById(R.id.checkBox2)
        val checkBoxMedicamento3: CheckBox = findViewById(R.id.checkBox3)
        val checkBoxMedicamento4: CheckBox = findViewById(R.id.checkBox4)
        val checkBoxMedicamento5: CheckBox = findViewById(R.id.checkBox5)
        val checkBoxMedicamento6: CheckBox = findViewById(R.id.checkBox6)
        val checkBoxMedicamento7: CheckBox = findViewById(R.id.checkBox7)
        val checkBoxMedicamento8: CheckBox = findViewById(R.id.checkBox8)
        val checkBoxMedicamento9: CheckBox = findViewById(R.id.checkBox9)
        val checkBoxMedicamento10: CheckBox = findViewById(R.id.checkBox10)

        val checkboxes = listOf(
            Pair(checkBoxMedicamento1, Producto("Paracetamol", 5.00)),
            Pair(checkBoxMedicamento2, Producto("Ibuprofeno", 4.50)),
            Pair(checkBoxMedicamento3, Producto("Amoxicilina", 6.00)),
            Pair(checkBoxMedicamento4, Producto("Cetirizina", 3.75)),
            Pair(checkBoxMedicamento5, Producto("Metformina", 7.25)),
            Pair(checkBoxMedicamento6, Producto("Aspirina", 2.50)),
            Pair(checkBoxMedicamento7, Producto("Loratadina", 3.00)),
            Pair(checkBoxMedicamento8, Producto("Omeprazol", 5.50)),
            Pair(checkBoxMedicamento9, Producto("Simvastatina", 8.00)),
            Pair(checkBoxMedicamento10, Producto("Losartán", 4.00))
        )

        for (pair in checkboxes) {
            pair.first.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    productosSeleccionados.add(pair.second)
                } else {
                    productosSeleccionados.remove(pair.second)
                }
                actualizarTotal()
                adapter.notifyDataSetChanged()
            }
        }

        pagarButton.setOnClickListener {
            productosSeleccionados.clear()
            for (pair in checkboxes) {
                pair.first.isChecked = false
            }
            adapter.notifyDataSetChanged()
            totalTextView.text = "Total a pagar: $0.00"
        }
    }

    private fun actualizarTotal() {
        val total = productosSeleccionados.sumOf { it.precio }
        totalTextView.text = "Total a pagar: $$total"
    }
}
