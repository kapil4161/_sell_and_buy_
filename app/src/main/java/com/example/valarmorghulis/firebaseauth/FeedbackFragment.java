package com.example.valarmorghulis.firebaseauth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FeedbackFragment extends Fragment implements View.OnClickListener {

    private EditText editTextMessage;
    private Button buttonSend;
    private String Name;
    private String Email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        editTextMessage = (EditText) v.findViewById(R.id.editTextMessage);
        buttonSend = (Button) v.findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(this);
        Name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        Email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        return v;
    }

    private void sendEmail() {
        String email = "juggler.buy.and.sell@gmail.com";
        String subject = "[Feedback] " + Name;
        String message = editTextMessage.getText().toString().trim() + "\n\nsent by " + Email;
        SendMail sm = new SendMail(getActivity(), email, subject, message);
		//sendEmail(to, replyTo, subject, body)
		//Sends an email message. This method allows a user to easily specify a Reply-To address for the sent message
		//that can differ from the sender.
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        if(editTextMessage.getText().toString().length() < 1){
            editTextMessage.setError("Message can't be empty.");
            editTextMessage.requestFocus();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		/*Android AlertDialog can be used to display the dialog message with OK and Cancel buttons. 
		It can be used to interrupt and ask the user about his/her choice to continue or discontinue.
		Android AlertDialog is composed of three regions: title, content area and action buttons.
		Android AlertDialog is the subclass of Dialog class.*/
        builder.setTitle("Feedback");
        builder.setMessage("Your feedback is very valuable to us.");

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendEmail();
                editTextMessage.setText("");
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editTextMessage.setText("");
                Toast.makeText(getActivity(),"Message Discarded", Toast.LENGTH_SHORT).show();
            }
        });
		//Creating dialog box  
        AlertDialog ad = builder.create();
        ad.show();

    }

}
