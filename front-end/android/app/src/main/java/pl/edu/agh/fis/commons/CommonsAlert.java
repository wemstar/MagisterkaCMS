package pl.edu.agh.fis.commons;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by wemstar on 2016-05-29.
 */
public class CommonsAlert {


    public static void showDialogBox(Context context,DialogInterface.OnClickListener yesAction) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context);
        builder.setTitle("Dialog");
        builder.setMessage("Lorem ipsum dolor ....");
        builder.setPositiveButton("OK", yesAction);
        builder.setNegativeButton("Cancel", null);
        builder.show();;
    }
}
