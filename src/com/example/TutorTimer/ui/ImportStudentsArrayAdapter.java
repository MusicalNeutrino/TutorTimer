package com.example.TutorTimer.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.TutorTimer.Logger.Logger;
import com.example.TutorTimer.R;
import com.example.TutorTimer.students.Student;
import com.example.TutorTimer.students.StudentManager;

import java.util.List;

public class ImportStudentsArrayAdapter extends ArrayAdapter<Student>
{
    private final StudentManager m_studentManager;

    public ImportStudentsArrayAdapter(Context context, int resource, List<Student> objects)
    {
        super(context, resource, objects);
        m_studentManager = StudentManager.getInstance(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView;
        if (convertView == null)
        {
            rowView = inflater.inflate(R.layout.import_student_entry, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.studentName = (TextView) rowView.findViewById(R.id.import_student_name);
            viewHolder.addToCurrentStudentsButton = (Button) rowView.findViewById(R.id.import_student_button);
            viewHolder.deleteStudentButton = (Button) rowView.findViewById(R.id.delete_student_button);

            viewHolder.addToCurrentStudentsButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Student student = (Student) v.getTag();
                    Logger.log(this, "Importing student %s", student);
                    m_studentManager.addToCurrentStudents(student);
                }
            });

            viewHolder.deleteStudentButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Student student = (Student) v.getTag();
                    Logger.log(this, "Deleting student %s", student);
                    m_studentManager.deleteStudent(student.getId());
                }
            });

            rowView.setTag(viewHolder);
        }
        else
        {
            rowView = convertView;
        }

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();

        Student student = getItem(position);
        viewHolder.student = student;

        TextView textView = viewHolder.studentName;
        textView.setText(student.toString());

        // add the student to the button's tag since the info will be needed in the onClick()
        viewHolder.addToCurrentStudentsButton.setTag(student);
        viewHolder.deleteStudentButton.setTag(student);

        return rowView;
    }

    private static final class ViewHolder
    {
        Student  student;
        TextView studentName;
        Button   addToCurrentStudentsButton;
        Button   deleteStudentButton;
    }
}
