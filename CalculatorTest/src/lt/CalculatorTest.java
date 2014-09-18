package lt;
import static org.junit.Assert.*;
import static org.robolectric.Robolectric.shadowOf_;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowTextView;
import com.calculator.CalculatorActivity;
import com.calculator.R;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@Implements(TextView.class)
class CustomShadowTextView extends ShadowTextView {
  @RealObject private TextView tv;
}

@Config(manifest = "../Calculator/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class CalculatorTest {
  private CalculatorActivity calculator;
  private EditText secondNumBut, firstNumBut;
  private TextView total;
  private Button addBut;

  
  @Before 
  public void setUp() throws Exception {
      calculator  = Robolectric.buildActivity(CalculatorActivity.class).create().get();;
      firstNumBut = (EditText) calculator.findViewById(R.id.firstNumber);
      secondNumBut= (EditText) calculator.findViewById(R.id.secondNumber);
      addBut = (Button) calculator.findViewById(R.id.addButton);
      total = (TextView) calculator.findViewById(R.id.total);
      
  }

  
 @Test
 public void testClickAddButton_withPositiveValues() {
	firstNumBut.setText("12");
    secondNumBut.setText("42");
    addBut.performClick();
    String s = total.getText().toString();
    assertEquals("54", s);
  }
  
 @Test
 @Config(shadows={CustomShadowTextView.class})
 public void testClick_TalktoShadow() {
	 CustomShadowTextView tb = shadowOf_(total);
	  
	  String startedIntent = tb.innerText();
	  assertEquals(total.getText().toString(), startedIntent);		
	  }
  
}

