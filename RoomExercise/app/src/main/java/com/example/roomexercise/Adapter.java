package com.example.roomexercise;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<EmployeeEntity> employeeEntities;


    Adapter(Context context, List<EmployeeEntity> employeeEntities) {
        this.context = context;
        this.employeeEntities = employeeEntities;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //employeeEntity = employeeEntities.get(position);
        holder.nameTextView.setText(employeeEntities.get(position).getName());
        holder.addressTextView.setText(employeeEntities.get(position).getAddress());
        holder.mobileTextView.setText(employeeEntities.get(position).getMobile());
        holder.openPoPMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.openPoPMenu);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.update:
                                Intent intent = new Intent(context, AddActivity.class);
                                intent.putExtra("record", employeeEntities.get(position));
                                context.startActivity(intent);
                                break;


                            case R.id.delete:
                                deleteRecord(employeeEntities.get(position), position);
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void deleteRecord(final EmployeeEntity employeeEntity, final int position) {
        class DeleteRecordTask extends AsyncTask<Void, Void, View> {
            @Override
            protected View doInBackground(Void... voids) {
                EmployeeDatabase.getInstance(context).employeeDao().delete(employeeEntity);
                return null;
            }

            @Override
            protected void onPostExecute(View view) {
                try {
                    employeeEntities.remove(position);
                    notifyItemChanged(position);
                    Toast.makeText(context, "Record Deleted..", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d("Exception", e.toString());
                }
            }
        }

        DeleteRecordTask deleteRecordTask = new DeleteRecordTask();
        deleteRecordTask.execute();
    }

    @Override
    public int getItemCount() {
        return employeeEntities.size();
    }
}
