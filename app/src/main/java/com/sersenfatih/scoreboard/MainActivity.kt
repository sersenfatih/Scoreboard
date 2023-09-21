package com.sersenfatih.scoreboard

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import com.sersenfatih.scoreboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addrow.setOnClickListener {
            binding.playernumber.isEnabled = false
            val numberOfColumns = binding.playernumber.text.toString().toInt()
            val tableRow = TableRow(this)
            tableRow.addView(TextView(this).apply {
                text = (binding.tableLayout.childCount + 1).toString()
            })
            for (i in 1..numberOfColumns) {
                tableRow.addView(EditText(this).apply {
                    hint = "Team $i"
                    setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED)
                })
            }
            binding.tableLayout.addView(tableRow)
        }

        binding.calculate.setOnClickListener {
            val numberOfRows = binding.tableLayout.childCount
            val numberOfColumns = (binding.tableLayout.getChildAt(0) as TableRow).childCount
            val tableRow = TableRow(this)
            for (j in 0 until numberOfColumns) {
                var total = 0
                for (i in 0 until numberOfRows) {
                    val row = binding.tableLayout.getChildAt(i) as TableRow
                    val view = row.getChildAt(j)
                    if (view is EditText) {
                        total += view.text.toString().toIntOrNull() ?: 0
                    }
                }
                tableRow.addView(TextView(this).apply {
                    text = total.toString()
                })
            }
            binding.tableLayout.addView(tableRow)
        }

        binding.reset.setOnClickListener {
            binding.tableLayout.removeAllViews()
            binding.playernumber.isEnabled = true
        }
    }

}
