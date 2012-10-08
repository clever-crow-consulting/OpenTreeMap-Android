package org.azavea.otm.ui;

import org.azavea.otm.App;
import org.azavea.otm.FieldGroup;
import org.azavea.otm.R;
import org.azavea.otm.data.Plot;
import org.azavea.otm.data.Tree;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TreeInfoDisplay extends TreeDisplay{
	
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.plot_view_activity);
    	
        LinearLayout fieldList = (LinearLayout)findViewById(R.id.field_list);
        LayoutInflater layout = ((Activity)this).getLayoutInflater();

        
		Log.d(App.LOG_TAG, "Setting header values");
		setHeaderValues(plot);
		showPositionOnMap();
		for (FieldGroup group : App.getFieldManager().getFieldGroups()) {
			fieldList.addView(group.renderForDisplay(layout, plot));
		}
    }
    
   
	private void setHeaderValues(Plot plot) {
		try {
			setText(R.id.address, plot.getAddress());
			
			Tree tree = plot.getTree();
			if (tree != null) {
				setText(R.id.species, tree.getSpeciesName());
			}
			
			setText(R.id.updated_on, "Last updated on " + plot.getLastUpdated());
			setText(R.id.updated_by, "By " + plot.getLastUpdatedBy());
		} catch (JSONException e) {
			Toast.makeText(this, "Could access plot information for display", 
					Toast.LENGTH_SHORT).show();
			Log.e(App.LOG_TAG, "Failed to create tree view", e);
		}
		
	}
	
	private void setText(int resourceId, String text) {
		// Only set the text if it exists, letting the layout define default text
		if (text != null &&  !"".equals(text)) {
			((TextView)findViewById(resourceId)).setText(text);
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_plot_edit_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_plot:
            	Intent filter = new Intent(this, FilterDisplay.class);
            	startActivityForResult(filter, 1);
            	break;
        }
        return true;
    }
}
