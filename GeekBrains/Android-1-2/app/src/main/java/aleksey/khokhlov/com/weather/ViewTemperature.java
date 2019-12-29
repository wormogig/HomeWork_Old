package aleksey.khokhlov.com.weather;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewTemperature extends RelativeLayout {
    private TextView viewDate;
    private TextView viewText;
    private TextView viewTemperature;

    public ViewTemperature(Context context) {
        super(context);
        initView(context);
    }

    public ViewTemperature(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ViewTemperature(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_card_temperature, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        testView();
    }

    private void initView() {
        viewDate = this.findViewById(R.id.view_date);
        viewText = this.findViewById(R.id.view_text);
        viewTemperature = this.findViewById(R.id.view_temperature);
    }

    private void testView() {
        viewDate.setText("09.03");
        viewText.setText("Пасмурно");
        viewTemperature.setText("7 / -2");
    }
}
