import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TimetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        populateTimetable();
    }

    private void populateTimetable() {
        GridLayout gridLayout = findViewById(R.id.timetable_grid);
        String[] days = {"Time/Day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] times = {"8-11", "11-1", "1-4", "4-8"};

        // Adding day headers
        for (int i = 0; i < days.length; i++) {
            TextView dayTextView = new TextView(this);
            dayTextView.setText(days[i]);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(0);
            params.columnSpec = GridLayout.spec(i, 1);
            params.setMargins(10, 10, 10, 10);
            dayTextView.setLayoutParams(params);
            gridLayout.addView(dayTextView);
        }

        // Adding times and editable cells
        for (int row = 1; row <= times.length; row++) {
            for (int col = 0; col < days.length; col++) {
                TextView cellTextView = new TextView(this);
                if (col == 0) {
                    cellTextView.setText(times[row - 1]);
                } else {
                    cellTextView.setText("Edit");
                    cellTextView.setOnClickListener(view -> showEditDialog(row, col));
                }
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(row);
                params.columnSpec = GridLayout.spec(col, 1);
                params.setMargins(10, 10, 10, 10);
                cellTextView.setLayoutParams(params);
                gridLayout.addView(cellTextView);
            }
        }
    }

    private void showEditDialog(int row, int col) {
        final EditText edittext = new EditText(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Edit Timetable Entry");
        alert.setTitle("Enter details");

        alert.setView(edittext);

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Here you can handle the save action
                String editTextValue = edittext.getText().toString();
                // Save the editTextValue somewhere
                // You might want to update the UI to reflect the new changes
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();
    }
}
