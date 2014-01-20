package jp.basic.sample;

import jp.basic.sample.R;
import jp.basicinc.gamefeat.android.sdk.controller.GameFeatAppController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sub1Activity extends Activity {

	GameFeatAppController gfAppController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub1);

		// GFコントローラ
		gfAppController = new GameFeatAppController();
		gfAppController.init(Sub1Activity.this);
		gfAppController.setPopupProbability(1);

		// GF 全画面起動
		((Button) findViewById(R.id.load_popup)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gfAppController.showPopupAdDialog(Sub1Activity.this);
			}
		});

		// 次のViewへ
		((Button) findViewById(R.id.btn_next)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Sub1Activity.this, Sub2Activity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		Log.d("GF", "requestCode = " + requestCode + " / resultCode = " + resultCode);
	}
}
