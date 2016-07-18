package zeroh729.firebase2demo.usecases.sendfeedback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import zeroh729.firebase2demo.R;

public class ShoutoutDialog extends Dialog{
    private SendFeedbackPresenter presenter;
    private EditText et_shoutout;
    private Button btn_send;

    public ShoutoutDialog(Context context, SendFeedbackPresenter presenter) {
        super(context);
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sendshoutout);

        et_shoutout = (EditText) findViewById(R.id.et_shoutout);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSendShoutout(et_shoutout.getText().toString());
            }
        });
    }
}
