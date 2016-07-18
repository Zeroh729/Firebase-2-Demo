package zeroh729.firebase2demo.usecases.sendfeedback;

import android.app.Dialog;
import android.content.Context;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import zeroh729.firebase2demo.R;

public class RatingDialog extends Dialog {
    private SendFeedbackPresenter presenter;
    private RatingBar et_rating;
    private Button btn_send;

    public RatingDialog(Context context, SendFeedbackPresenter presenter) {
        super(context);
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sendrating);

        et_rating = (RatingBar) findViewById(R.id.et_rating);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSendSlideRating(et_rating.getRating());
            }
        });
    }
}
