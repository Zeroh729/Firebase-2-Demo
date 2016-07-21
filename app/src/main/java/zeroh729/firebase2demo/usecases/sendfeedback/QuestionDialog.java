package zeroh729.firebase2demo.usecases.sendfeedback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.models.User;

public class QuestionDialog extends Dialog {
    private SendFeedbackPresenter presenter;
    private EditText et_question;
    private CheckBox cb_private;
    private EditText et_contactInfo;
    private Button btn_send;
    private User user;

    public QuestionDialog(Context context, SendFeedbackPresenter presenter) {
        super(context);
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sendquestion);

        et_question = (EditText)findViewById(R.id.et_question);
        cb_private = (CheckBox)findViewById(R.id.cb_private);
        et_contactInfo = (EditText)findViewById(R.id.et_contactInfo);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSendQuestion(et_question.getText().toString(),
                                                et_contactInfo.getText().toString(),
                                                cb_private.isChecked());
            }
        });
        if(user != null){
            et_contactInfo.setText(user.getContactInfo());
        }
    }

    public void setUser(User user){
        this.user = user;
    }
}
