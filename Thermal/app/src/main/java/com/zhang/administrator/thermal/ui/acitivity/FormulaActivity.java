package com.zhang.administrator.thermal.ui.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.MainActivity;

import io.github.kexanie.library.MathView;

/**
 * Created by Administrator on 2016/5/3.
 */
public class FormulaActivity extends Activity {
    private TextView tv_formula;
    private ImageView mFormulaBack;

    private MathView mFormulaTwo,mFormulaThree;
    String tex = "This come from string. You can insert inline formula:" +
            " \\(ax^2 + bx + c = 0\\) " +
            "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";
    String mathml =
            "<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" mathcolor=\"black\">\n" +
                    "  <mrow>\n" +
                    "    <mi>f</mi>\n" +
                    "    <mrow>\n" +
                    "      <mo>(</mo>\n" +
                    "      <mi>a</mi>\n" +
                    "      <mo>)</mo>\n" +
                    "    </mrow>\n" +
                    "  </mrow>\n" +
                    "  <mo>=</mo>\n" +
                    "  <mrow>\n" +
                    "    <mfrac>\n" +
                    "      <mn>1</mn>\n" +
                    "      <mrow>\n" +
                    "        <mn>2</mn>\n" +
                    "        <mi>&#x3C0;</mi>\n" +
                    "        <mi>i</mi>\n" +
                    "      </mrow>\n" +
                    "    </mfrac>\n" +
                    "    <msub>\n" +
                    "      <mo>&#x222E;</mo>\n" +
                    "      <mrow>\n" +
                    "        <mi>&#x3B3;</mi>\n" +
                    "      </mrow>\n" +
                    "    </msub>\n" +
                    "    <mfrac>\n" +
                    "      <mrow>\n" +
                    "        <mi>f</mi>\n" +
                    "        <mo>(</mo>\n" +
                    "        <mi>z</mi>\n" +
                    "        <mo>)</mo>\n" +
                    "      </mrow>\n" +
                    "      <mrow>\n" +
                    "        <mi>z</mi>\n" +
                    "        <mo>&#x2212;</mo>\n" +
                    "        <mi>a</mi>\n" +
                    "      </mrow>\n" +
                    "    </mfrac>\n" +
                    "    <mi>d</mi>\n" +
                    "    <mi>z</mi>\n" +
                    "  </mrow>\n" +
                    "</math>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_formula);

        //tv_formula= (TextView) findViewById(R.id.tv_formula);
       // showFormula();
        mFormulaBack= (ImageView) findViewById(R.id.Formula_back);
        mFormulaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormulaActivity.this, MainActivity.class));
            }
        });
    }

//    private void showFormula() {
//        InputStream inputStream = getResources().openRawResource(R.raw.formula);
//        String string = TxtReader.getString(inputStream);
//        tv_formula.setText(string);
//    }

    @Override
    protected void onResume() {
        super.onResume();

        mFormulaTwo = (MathView) findViewById(R.id.formula_two);
        mFormulaThree = (MathView) findViewById(R.id.formula_three);

        mFormulaTwo.setText(tex);
        mFormulaThree.setText(mathml);
    }



}
