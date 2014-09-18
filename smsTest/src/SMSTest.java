import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowSmsManager;

import static org.junit.Assert.*;
import static org.robolectric.Robolectric.shadowOf_;
import android.app.AlertDialog;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.sms.*;

import com.example.sms.MainActivity;
@Config(manifest = "../sms/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class SMSTest {
	private MainActivity smsActivity;
	@Before 
	public void setUp() throws Exception {
		smsActivity = Robolectric.buildActivity(MainActivity.class).create().get();;
	}
	@Test
	public void itShowsDialogIfAFieldIsBlank() {
		EditText phone = (EditText) smsActivity.findViewById(R.id.editText1);
		phone.setText("");
		EditText content = (EditText) smsActivity.findViewById(R.id.editText2);
		content.setText("");
		Button btnSend = (Button)smsActivity.findViewById(R.id.button1);
		btnSend.performClick();
		AlertDialog alert =	ShadowAlertDialog.getLatestAlertDialog();
		ShadowAlertDialog sAlert = shadowOf_(alert);
		assertEquals(sAlert.getMessage().toString(),
				smsActivity.getString(R.string.sms_empty));
	}
	@Test
	public void shouldSendSMSToTheGivenPhoneNumber() {
		String message = "Android is cool";
		String phoneNumber = "123-123-1222";
		smsActivity.sendSMS(phoneNumber, message);
		ShadowSmsManager shadowSmsManager = shadowOf_(SmsManager.getDefault());
		ShadowSmsManager.TextSmsParams lastSentTextMessageParams = shadowSmsManager.getLastSentTextMessageParams();
		assertEquals(phoneNumber, lastSentTextMessageParams.getDestinationAddress());
		assertEquals(message, lastSentTextMessageParams.getText());
	}
}
